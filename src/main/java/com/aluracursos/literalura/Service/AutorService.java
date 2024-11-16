package com.aluracursos.literalura.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.repository.AutorRepository;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    public Optional<Autor> buscarAutorPorNombre(String nombre) {
        return autorRepository.findByNombre(nombre);
    }

    public List<Autor> buscarAutorPorLibro(String titulo) {
        return autorRepository.findAllByLibrosTitulo(titulo);
    }

    public List<Autor> findAutoresVivosEnAño(int año) {
        return autorRepository.findByNacimientoLessThanAndMuerteGreaterThanOrMuerteIsNull(año);
    }

    public void guardarAutor(Autor autor) {
        autorRepository.save(autor);
    }
    
}

