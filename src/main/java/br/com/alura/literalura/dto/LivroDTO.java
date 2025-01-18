package br.com.alura.literalura.dto;

public record LivroDTO(Long id,
                       String titulo,
                       String autor,
                       String idioma,
                       Integer downloads) {
}
