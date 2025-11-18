package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Carrinho {
    private final Map<Produto, Integer> itens;
    private static final int CASAS_DECIMAIS = 2;

    public Carrinho() {
        this.itens = new HashMap<>();
    }
    public void adicionar(Produto produto, int quantidade) throws QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("A quantidade deve ser maior que zero.");
        }
        Objects.requireNonNull(produto, "O produto não pode ser nulo.");
        itens.merge(produto, quantidade, Integer::sum);
    }
    public BigDecimal getSubtotal() {
        // Itera sobre o mapa para calcular o total
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Map.Entry<Produto, Integer> entry : itens.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();
            BigDecimal valorItem = produto.getPrecoUnitario()
                    .multiply(new BigDecimal(quantidade));

            subtotal = subtotal.add(valorItem);
        }
        return subtotal.setScale(CASAS_DECIMAIS, RoundingMode.HALF_UP);
    }
    public int getTotalItens() {
        // Soma as quantidades de todos os valores no mapa
        return itens.values().stream().mapToInt(Integer::intValue).sum();
    }
    public BigDecimal getTotalCom(PoliticaDesconto politica) {
        Objects.requireNonNull(politica, "A política de desconto não pode ser nula.");
        BigDecimal subtotal = getSubtotal();
        BigDecimal totalComDesconto = politica.aplicar(subtotal);
        if (totalComDesconto.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO.setScale(CASAS_DECIMAIS, RoundingMode.HALF_UP);
        }

        return totalComDesconto.setScale(CASAS_DECIMAIS, RoundingMode.HALF_UP);
    }
}
