package com.example.mathr.burb01.Modelos;

/**
 * Created by mathr on 01/02/2018.
 */

public class Usuarios {

    private String Nome,Peso,Altura,Situacao,Renda,Sexo;

    private int Saldo;

    public Usuarios(){}

    public Usuarios(String nome, String peso, String altura, String situacao, String renda, String sexo, int saldo) {
        Nome = nome;
        Peso = peso;
        Altura = altura;
        Situacao = situacao;
        Renda = renda;
        Sexo = sexo;
        Saldo = saldo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }

    public String getAltura() {
        return Altura;
    }

    public void setAltura(String altura) {
        Altura = altura;
    }

    public String getSituacao() {
        return Situacao;
    }

    public void setSituacao(String situacao) {
        Situacao = situacao;
    }

    public String getRenda() {
        return Renda;
    }

    public void setRenda(String renda) {
        Renda = renda;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public int getSaldo() {
        return Saldo;
    }

    public void setSaldo(int saldo) {
        Saldo = saldo;
    }
}
