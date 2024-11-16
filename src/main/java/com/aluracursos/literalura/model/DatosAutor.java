package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(
    @JsonAlias("name") String nombre,
    @JsonAlias("birth_year") int nacimiento,
    @JsonAlias("death_year") int muerte
) {

}
