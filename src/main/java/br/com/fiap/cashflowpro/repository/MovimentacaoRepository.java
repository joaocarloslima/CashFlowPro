package br.com.fiap.cashflowpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.cashflowpro.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByCategoriaNome(String categoria);

    //JPQL
    @Query("SELECT m FROM Movimentacao m WHERE MONTH(m.data) = ?1")
    List<Movimentacao> findByMes(Integer mes);
    
    @Query("SELECT m FROM Movimentacao m WHERE m.categoria.nome = ?1 AND MONTH(m.data) = ?2")
    List<Movimentacao> findByCategoriaNomeAndMes(String categoria, Integer mes);
    
}
