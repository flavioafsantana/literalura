package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)  // Nome da coluna no banco de dados
    private String name;

    @JsonProperty("birth_year")  // Mapeia corretamente o campo 'birth_year' do JSON para 'birthYear'
    @Column(name = "nascimento")
    private int birthYear;

    @JsonProperty("death_year")  // Mapeia corretamente o campo 'death_year' do JSON para 'deathYear'
    @Column(name = "falecimento")
    private int deathYear;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros;  // Relacionamento com a lista de livros

    // Construtores, getters e setters
    public Autor() {}

    public Autor(String name, int birthYear, int deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return  '\n' + "------- Autor -------" + '\n' +
                "Nome: " + name + '\n' +
                "Nascimento: " + birthYear + '\n' +
                "Falecimento: " + deathYear + "]";
    }
}

