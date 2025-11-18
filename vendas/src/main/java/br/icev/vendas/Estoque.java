package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import br.icev.vendas.excecoes.SemEstoqueException;

import java.util.HashMap;
import java.util.Map;

public class Estoque {

    private Map<String, Integer> quantidades;

    public Estoque(){
        this.quantidades = new HashMap<>();
    }

    public void adicionarEstoque(String codigo, int quantidade) throws QuantidadeInvalidaException {
        if(quantidade <= 0){
            throw new QuantidadeInvalidaException("A quantidade não pode ser nula ou negativa.");
        }
        //chama a chave e olha a quantidade que ja existe para incrementar em seguida
        int atual = quantidades.getOrDefault(codigo, 0);
        quantidades.put(codigo, atual + quantidade);
    }

    public int getDisponivel(String codigo) {
        return quantidades.getOrDefault(codigo, 0);
    }

    public void reservar(String codigo, int quantidade) throws QuantidadeInvalidaException, SemEstoqueException {
        if(quantidade <= 0){
            throw new QuantidadeInvalidaException("A quantidade não pode ser nula ou negativa.");
        }
        int atual = quantidades.getOrDefault(codigo, 0);
        if (atual < quantidade){
            throw new SemEstoqueException("Sem estoque para " + codigo);
        }
        quantidades.put(codigo, atual - quantidade);
    }

}
