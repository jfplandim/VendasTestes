package br.icev.vendas;

import java.math.BigDecimal;
import java.util.Objects;

public class Produto {
    private final String codigo;
    private final String nome;
    private final BigDecimal precoUnitario;

    public Produto(String codigo, String nome, BigDecimal precoUnitario) {
        //verificação do nulo
        Objects.requireNonNull(precoUnitario, "O preço unitário não pode ser nulo.");
        //verificação do preço negativo
        if (precoUnitario.signum() <= 0) {
            throw new IllegalArgumentException("O preço unitário deve ser maior que zero.");
        }
        this.codigo=codigo;
        this.nome=nome;
        this.precoUnitario=precoUnitario;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Produto produto = (Produto) o;
        return Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
