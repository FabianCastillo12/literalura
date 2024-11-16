package com.aluracursos.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aluracursos.literalura.model.Autor;
 
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // recuperar todos los autores
    List<Autor> findAll();

    // recuperar autor por nombre
    Optional<Autor> findByNombre(String nome);

    // recuperar autor por libro
    List<Autor> findAllByLibrosTitulo(String titulo);

    // recuperar autores vivos en un año
    @Query("SELECT a FROM Autor a WHERE a.nacimiento < :year AND (a.muerte > :year OR a.muerte IS NULL)")
    List<Autor> findByNacimientoLessThanAndMuerteGreaterThanOrMuerteIsNull(@Param("year") int año);

}
