package com.example.mathr.burb01.Modelos;

/**
 * Created by mathr on 05/02/2018.
 */

public class Guias {

    private String Nome,Imagem,Autor,Descricao;
    private float Avaliacao;
    public Guias(){}

    public Guias(String nome, String imagem, String autor, String descricao, Float avaliacao) {
        Nome = nome;
        Imagem = imagem;
        Autor = autor;
        Descricao = descricao;
        Avaliacao = avaliacao;
    }

    public Float getAvaliacao() {
        return Avaliacao;
    }

    public void setAvaliacao(Float avaliacao) {
        Avaliacao = avaliacao;
    }
    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getImagem() {
        return Imagem;
    }

    public void setImagem(String imagem) {
        Imagem = imagem;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getDescricao(){return Descricao;}

    public void setDescricao (String descricao){Descricao = descricao;}
}
