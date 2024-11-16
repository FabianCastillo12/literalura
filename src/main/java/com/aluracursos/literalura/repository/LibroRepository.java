package com.aluracursos.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aluracursos.literalura.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    
    Optional<Libro> findByTitulo(String titulo);

    List<Libro> findAllByAutoresNombre(String nombre);

    List<Libro> findAllByIdioma(String idioma);
    
}
