package br.com.fiap.cashflowpro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cashflowpro.model.Categoria;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    Logger log = LoggerFactory.getLogger(getClass());

    List<Categoria> repository = new ArrayList<>();

    @GetMapping
    public List<Categoria> index() {
        return repository;
    }

    @PostMapping
    // @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria) {
        log.info("cadastrando categoria: {}", categoria);
        repository.add(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> get(@PathVariable Long id) {
        log.info("buscando categoria com id {}", id);

        var categoria = getCategoriaById(id);

        if (categoria.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categoria.get());
    }

    private Optional<Categoria> getCategoriaById(Long id) {
        var categoria = repository
                .stream()
                .filter(c -> c.id().equals(id))
                .findFirst();
        return categoria;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("apagando categoria {}", id);

        var categoria = getCategoriaById(id);

        if (categoria.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.remove(categoria.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Categoria> update(
        @PathVariable Long id,
        @RequestBody Categoria categoria
    ){
        log.info("atualizando categoria com id {} para {}", id, categoria);

        var categoriaEncontrada = getCategoriaById(id);

        if (categoriaEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var categoriaAtualizada = new Categoria(id, categoria.nome(), categoria.icone());
        repository.remove(categoriaEncontrada.get());
        repository.add(categoriaAtualizada);

        return ResponseEntity.ok(categoriaAtualizada);
    }

}
