package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutores(@JsonProperty("name") String name,
                           @JsonProperty ("birth_year") Integer birthYear,
                           @JsonProperty("death_year") Integer deathYear) {
}
