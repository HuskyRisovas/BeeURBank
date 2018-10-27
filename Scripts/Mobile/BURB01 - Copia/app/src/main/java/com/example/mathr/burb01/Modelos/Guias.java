package com.example.mathr.burb01.Modelos;

/**
 * Created by mathr on 05/02/2018.
 */

public class Guias {

    private String Nome,Imagem,Autor,Descricao;

    public Guias(){}

    public Guias(String nome, String imagem, String autor, String descricao) {
        Nome = nome;
        Imagem = imagem;
        Autor = autor;
        Descricao = descricao;
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
