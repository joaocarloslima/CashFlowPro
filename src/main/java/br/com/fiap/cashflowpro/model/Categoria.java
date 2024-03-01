package br.com.fiap.cashflowpro.model;

import java.util.Random;

public record Categoria(Long id, String nome, String icone) {

    public Categoria(Long id, String nome, String icone){
        var key = (id != null) ? id : Math.abs( new Random().nextLong() );
        this.id = key;  
        this.nome = nome;  
        this.icone = icone;  
    }
 
}
