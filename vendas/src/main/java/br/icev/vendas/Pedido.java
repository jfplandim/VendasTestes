package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;

import java.math.BigDecimal;
import java.util.Map;

public class Pedido {
    private final BigDecimal totalPago;
    private final String codigoAutorizacao;
   private final Status status;
   private final Map<String,Integer> itensPorCodigo;

   public enum Status{
       PAGO,

   }

    public Pedido(Map<String, Integer> itensPorCodigo, BigDecimal totalPago, String codigoAutorizacao, Status status) {
        this.itensPorCodigo=itensPorCodigo;
        this.totalPago=totalPago;
        this.codigoAutorizacao=codigoAutorizacao;
        this.status=status;
    }

    public BigDecimal getTotalPago() { return totalPago; }
    public String getCodigoAutorizacao() { return codigoAutorizacao; }
    public Status getStatus() { return status; }
    public int getQuantidadeItem(String codigo) {return itensPorCodigo.getOrDefault(codigo,0);}




}
