package br.com.fiap.cashflowpro.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.cashflowpro.model.Categoria;
import br.com.fiap.cashflowpro.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("categoria")
@Slf4j
public class CategoriaController {

    @Autowired // CDI - Injeção de Dependência
    CategoriaRepository repository;

    @GetMapping
    public List<Categoria> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Categoria create(@RequestBody Categoria categoria) {
        log.info("cadastrando categoria: {}", categoria);
        return repository.save(categoria);
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> get(@PathVariable Long id) {
        log.info("buscando categoria com id {}", id);

        return repository
                    .findById(id)
                    .map(ResponseEntity::ok) //reference method
                    .orElse(ResponseEntity.notFound().build());

        // var categoria = repository.findById(id);

        // if (categoria.isEmpty()) {
        //     return ResponseEntity.notFound().build();
        // }

        // return ResponseEntity.ok(categoria.get());
    }

    // private Optional<Categoria> getCategoriaById(Long id) {
    //     var categoria = repository
    //             .stream()
    //             .filter(c -> c.id().equals(id))
    //             .findFirst();
    //     return categoria;
    // }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("apagando categoria {}", id);
        verificarSeExisteCategoria(id);
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    public Categoria update(@PathVariable Long id, @RequestBody Categoria categoria){
        log.info("atualizando categoria com id {} para {}", id, categoria);

        verificarSeExisteCategoria(id);
        
        categoria.setId(id);
        return repository.save(categoria);

    }

    private void verificarSeExisteCategoria(Long id) {
        repository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                                    NOT_FOUND, 
                                    "id da categoria não encontrado"
                                ));
    }

}
