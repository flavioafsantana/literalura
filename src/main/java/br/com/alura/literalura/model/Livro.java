package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private String downloads;

    public Livro() {
    }

    public Livro(DadosLivro dadosLivro, Autor autor) {
        this.titulo = dadosLivro.titulo();
        this.autor = autor;  // Atribui o autor ao livro
        this.idioma = Idioma.fromString(String.valueOf(dadosLivro.idioma()));
        this.downloads = String.valueOf(dadosLivro.downloads());  // Converte downloads para String
    }

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        // Processa o idioma
        // Não há atribuição de autor aqui, então deve ser atribuído mais tarde
         // Valor padrão ou definido conforme os dados
        this.downloads = String.valueOf(dadosLivro.downloads());
    }


    // Getters e Setters
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return '\n' + "------- Livro -------" + '\n' +
                "Titulo: " + titulo + '\n' +
                "Autor: " + autor.getName() + '\n' +
                "Idioma: " + idioma + '\n' +
                "Downloads: " + downloads + '\n';
    }
}


