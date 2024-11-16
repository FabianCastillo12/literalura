package com.aluracursos.literalura.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.LibroRepository;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public void guardarLibros(Libro libro) {
        this.libroRepository.save(libro);
    }

    public Optional<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    public List<Libro> buscarPorAutor(String nombre) {
        return libroRepository.findAllByAutoresNombre(nombre);
    }

    public List<Libro> buscarPorIdioma(String idioma) {
        return libroRepository.findAllByIdioma(idioma);
    }
}
