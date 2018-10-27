package com.example.mathr.burb01.Modelos;

/**
 * Created by mathr on 25/02/2018.
 */

public class Conquistas {

    private String Nome,Descricao,Imagem,Nivel;
    private float Progresso;

    private Conquistas(){}

    public Conquistas(String nome, String descricao, float progresso, String nivel, String imagem) {
        Nome = nome;
        Descricao = descricao;
        Progresso = progresso;
        Nivel = nivel;
        Imagem = imagem;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public float getProgresso() {
        return Progresso;
    }

    public void setProgresso(float progresso) {
        Progresso = progresso;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String nivel) {
        Nivel = nivel;
    }

    public String getImagem() {
        return Imagem;
    }

    public void setImagem(String imagem) {
        Imagem = imagem;
    }
}
