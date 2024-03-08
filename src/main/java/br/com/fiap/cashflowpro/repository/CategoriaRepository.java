package br.com.fiap.cashflowpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cashflowpro.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

   
}
