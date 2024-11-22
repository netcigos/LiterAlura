package com.literalura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecordLibro(

        @JsonAlias("id") Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count")int descargas,
        @JsonAlias("authors") List<RecordAutor>autores

        ) {
}
