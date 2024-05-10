package br.com.fiap.cashflowpro.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cashflowpro.model.Movimentacao;
import br.com.fiap.cashflowpro.repository.MovimentacaoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("movimentacao")
@Slf4j
public class MovimentacaoController {

    record TotalPorCategoria (String categoria, BigDecimal valor){}
    record TotalPorMes(String mes, BigDecimal receita, BigDecimal despesa) {}

    @Autowired
    MovimentacaoRepository repository;

    @GetMapping
    public Page<Movimentacao> index(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Integer mes,
            @PageableDefault(size = 5, sort = "data", direction = Direction.DESC) Pageable pageable
        ){

        if(categoria != null && mes != null){
            return repository.findByCategoriaNomeAndMes(categoria, mes, pageable);
        }

        if (categoria != null){
            return repository.findByCategoriaNome(categoria, pageable);
        }

        if(mes != null){
            return repository.findByMes(mes, pageable);
        }

        log.info(categoria);
        return repository.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Movimentacao create(@RequestBody @Valid Movimentacao movimentacao){
        return repository.save(movimentacao);
    }

    @GetMapping("menor")
    public Movimentacao menor( @PageableDefault(size = 1, sort = "valor") Pageable pageable){
        var page = repository.findAll(pageable);
        return page.getContent().get(0);
    }

    @GetMapping("maior")
    public Movimentacao maior() {
        return repository.getMaior();
    }

    @GetMapping("ultima")
    public Movimentacao ultima(){
        return repository.getUltima();
    }

    @GetMapping("totais-por-categoria")
    public List<TotalPorCategoria> getTotalPorCategoria(){
        var movimentacoes = repository.findAll();

        Map<String, BigDecimal> collect = movimentacoes.stream()
            .collect( Collectors.groupingBy( 
                m -> m.getCategoria().getNome(),
                Collectors.reducing(BigDecimal.ZERO, Movimentacao::getValor, BigDecimal::add)
            ));

        return collect
            .entrySet()
            .stream()
            .map( e -> new TotalPorCategoria(e.getKey(), e.getValue()))
            .toList();
       
    }

    @GetMapping("totais-por-mes")
    public List<TotalPorMes> getTotaisPorMes(){
        var movimentacao = repository.findAll();

        Map<String, BigDecimal> totaisReceitas = movimentacao.stream()
            .filter( m -> m.getTipo().equals("RECEITA"))
            .collect(
                Collectors.groupingBy(
                    m -> m.getData().getMonth().toString(),
                    Collectors.reducing(BigDecimal.ZERO, Movimentacao::getValor, BigDecimal::add)
                )
            );

        Map<String, BigDecimal> totaisDespesas = movimentacao.stream()
            .filter( m -> m.getTipo().equals("DESPESA"))
            .collect(
                Collectors.groupingBy(
                    m -> m.getData().getMonth().toString(),
                    Collectors.reducing(BigDecimal.ZERO, Movimentacao::getValor, BigDecimal::add)
                )
            );

        return totaisDespesas
            .keySet()
            .stream()
            .map(mes -> new TotalPorMes(
                mes,
                totaisReceitas.getOrDefault(mes, BigDecimal.ZERO),
                totaisDespesas.getOrDefault(mes, BigDecimal.ZERO)
            ))
            .toList();
    }    

    
}
