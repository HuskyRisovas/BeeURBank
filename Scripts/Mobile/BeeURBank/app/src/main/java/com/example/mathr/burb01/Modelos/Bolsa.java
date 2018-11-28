package com.example.mathr.burb01.Modelos;

public class Bolsa {
    private String nomeDaCompanhia, simboloDaCompanhia,preco;
    private double variacao;

    public Bolsa(String nomeDaCompanhia, String simboloDaCompanhia, double variacao, String preco) {
        this.nomeDaCompanhia = nomeDaCompanhia;
        this.simboloDaCompanhia = simboloDaCompanhia;
        this.variacao = variacao;
        this.preco = preco;
    }

    public String getNomeDaCompanhia() {
        return nomeDaCompanhia;
    }

    public void setNomeDaCompanhia(String nomeDaCompanhia) {
        this.nomeDaCompanhia = nomeDaCompanhia;
    }

    public String getSimboloDaCompanhia() {
        return simboloDaCompanhia;
    }

    public void setSimboloDaCompanhia(String simboloDaCompanhia) {
        this.simboloDaCompanhia = simboloDaCompanhia;
    }

    public double getVariacao() {
        return variacao;
    }

    public void setVariacao(double variacao) {
        this.variacao = variacao;
    }


    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
