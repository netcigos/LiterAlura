package com.literalura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record RecordAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") int anoNacimiento,
        @JsonAlias("death_year") int anoMuerte
) {

}
