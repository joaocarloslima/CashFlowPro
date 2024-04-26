package br.com.fiap.cashflowpro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.cashflowpro.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    Page<Movimentacao> findByCategoriaNome(String categoria, Pageable pageable);

    //JPQL
    @Query("SELECT m FROM Movimentacao m WHERE MONTH(m.data) = ?1")
    Page<Movimentacao> findByMes(Integer mes, Pageable pageable);
    
    @Query("SELECT m FROM Movimentacao m WHERE m.categoria.nome = ?1 AND MONTH(m.data) = ?2")
    Page<Movimentacao> findByCategoriaNomeAndMes(String categoria, Integer mes, Pageable pageable);

    @Query("SELECT m FROM Movimentacao m ORDER BY m.valor DESC LIMIT 1")
    Movimentacao getMaior();

    @Query("SELECT m FROM Movimentacao m ORDER BY m.data DESC LIMIT 1")
    Movimentacao getUltima();
    
}
