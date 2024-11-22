package com.literalura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    private Long id;

    private String titulo;
    private String idioma;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    private int descargas;

    public Libro(){};

    public Libro(RecordLibro recordLibro){

        this.id=recordLibro.id();
        this.titulo= recordLibro.titulo();
        this.descargas= recordLibro.descargas();
        this.idioma=recordLibro.idiomas().get(0);
        this.autor=new Autor(recordLibro.autores().get(0));



    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public int getDescargas() {
        return descargas;
    }

    @Override
    public String toString() {
        return    "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idioma='" + idioma + '\'' +
                ", autor=" + autor +
                ", descargas=" + descargas +"\n"
                ;
    }
}


