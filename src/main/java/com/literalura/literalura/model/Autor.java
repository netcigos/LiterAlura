package com.literalura.literalura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private int anoNacimiento;

    private int anoMuete;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros;


    public Autor(){}

    public Autor(RecordAutor recordAutor){

        this.nombre=recordAutor.nombre();
        this.anoNacimiento=recordAutor.anoNacimiento();
        this.anoMuete=recordAutor.anoMuerte();


    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    public int getAnoMuete() {
        return anoMuete;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    @Override
    public String toString() {
        return

                " Nombre='" + nombre + '\'' +
                ", Año Nacimiento=" + anoNacimiento +
                ", Año Muerte=" + anoMuete +
                 "\n"
                ;
    }





}
