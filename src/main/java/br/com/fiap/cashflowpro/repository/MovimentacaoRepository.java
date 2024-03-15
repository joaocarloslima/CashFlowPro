package br.com.fiap.cashflowpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cashflowpro.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    
}
