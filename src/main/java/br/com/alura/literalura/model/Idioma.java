package br.com.alura.literalura.model;
import java.util.Map;

public enum Idioma {
    PT("pt","Português"),
    EN("en","Inglês"),
    ES("es","Espanhol"),
    FR("fr","Francês"),
    DE("de","Alemão"),
    OUTRO("null","Outro");

    private String idiomaApi;
    private String idiomaTraduzido;

    Idioma(String idiomaApi, String idiomaTraduzido) {
        this.idiomaApi = idiomaApi;
        this.idiomaTraduzido = idiomaTraduzido;
    }
    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaApi.equalsIgnoreCase(text)) {
                return idioma;
            }

        }
        throw new IllegalArgumentException("Idioma não encontrado: " + text);
    }
    public static Idioma fromPortugues(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaApi.equalsIgnoreCase(text)) {
                return idioma;
            }

        }
        throw new IllegalArgumentException("Idioma não encontrado: " + text);
    }


}


