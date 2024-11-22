package com.literalura.literalura.repository;

import com.literalura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {

    Optional<Autor> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    @Query("SELECT a FROM Autor a WHERE a.anoNacimiento <= :ano AND a.anoMuete > :ano")
    List<Autor> obtenerAutoresPorAñoNacimiento(int ano);


}
