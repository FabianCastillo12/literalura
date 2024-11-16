package com.aluracursos.literalura.model;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") int descargas,
        @JsonAlias("media_type") String tipoMedio) {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Información del Libro ===\n")
          .append("Título: ").append(titulo).append("\n")
          .append("Descargas: ").append(descargas).append("\n")
          .append("Tipo de medio: ").append(tipoMedio).append("\n")
          .append("Autores: ").append(autores.stream()
                .map(DatosAutor::nombre)
                .collect(Collectors.joining(" - ")))
          .append("\n")
          .append("=============================");
        return sb.toString();
    }
}
