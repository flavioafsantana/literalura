package br.com.alura.literalura.model;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum Idioma {
    PT("pt"),
    EN("en"),
    ES("es"),
    FR("fr"),
    DE("de"),
    OUTRO("null");

    private String idiomaApi;

    Idioma(String idiomaApi) {
        this.idiomaApi = idiomaApi;
    }

    @JsonCreator
    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaApi.equalsIgnoreCase(text)) {
                return idioma;
            }

        }
        return OUTRO;
    }



}


