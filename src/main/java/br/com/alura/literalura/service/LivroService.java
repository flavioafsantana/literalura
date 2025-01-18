package br.com.alura.literalura.service;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Idioma;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public Livro salvarLivro(DadosLivro dadosLivro) {
       Idioma idioma = dadosLivro.idioma().stream()
                .map(Idioma::fromString)
                .findFirst()
                .orElse(Idioma.OUTRO);

        Autor autor = autorRepository.findByName(dadosLivro.autores().get(0).getName())
                .orElse(new Autor(dadosLivro.autores().get(0).getName(), dadosLivro.autores().get(0).getBirthYear(), dadosLivro.autores().get(0).getDeathYear()));

        Livro livro = new Livro(dadosLivro, autor);
        livro.setTitulo(dadosLivro.titulo());
        livro.setAutor(autor);
        livro.setIdioma(idioma);
        livro.setDownloads(String.valueOf(dadosLivro.downloads()));


        return livroRepository.save(livro);
    }

    public List<Livro> buscarLivrosPorAutor(String nomeAutor) {
        Autor autor = autorRepository.findByName(nomeAutor)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        return autor.getLivros();
    }
}
