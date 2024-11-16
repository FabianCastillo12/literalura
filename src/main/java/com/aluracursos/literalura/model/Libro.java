package com.aluracursos.literalura.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "libro_autor", 
        joinColumns = @JoinColumn(name = "libro_id"), 
        inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private Set<Autor> autores = new HashSet<>();

    private String idioma;

    private int descargas;

    private String tipoMedio;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        if (datosLibro.titulo() == null || datosLibro.titulo().isEmpty()) {
            throw new IllegalArgumentException("El título del libro no puede estar vacío");
        }
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idiomas().isEmpty() ? "Desconocido" : datosLibro.idiomas().get(0);
        this.descargas = datosLibro.descargas();
        this.tipoMedio = datosLibro.tipoMedio();
        datosLibro.autores().forEach(autor -> this.autores.add(new Autor(autor)));
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }    

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTipoMedio() {
        return tipoMedio;
    }

    public void setTipoMedio(String tipoMedio) {
        this.tipoMedio = tipoMedio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Información del Libro ===\n")
          .append("Título: ").append(titulo).append("\n")
          .append("Descargas: ").append(descargas).append("\n")
          .append("Tipo de medio: ").append(tipoMedio).append("\n")
          .append("Autores: ").append(autores.stream()
                .map(Autor::getNombre)
                .reduce("", (a, b) -> a + " - " + b))
          .append("\n")
          .append("=============================");
        return sb.toString();
    }

}
