package br.com.fiap.cashflowpro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.fiap.cashflowpro.validation.TipoMovimentacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Movimentacao {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{movimentacao.descricao.notblank}")
    @Size(min = 3, max = 255)
    private String descricao;

    private LocalDate data;

    @Positive
    private BigDecimal valor;

    @TipoMovimentacao
    private String tipo; // RECEITA | DESPESA

}
