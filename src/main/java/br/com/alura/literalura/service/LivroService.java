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
import java.util.Objects;
import java.util.stream.Collectors;

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

        // Verifica se o idioma é válido (opcional)
        if (idioma == Idioma.OUTRO) {
            System.out.println("Aviso: Idioma desconhecido para o livro: " + dadosLivro.titulo());
        }
        System.out.println("Idiomas recebidos: " + dadosLivro.idioma());

        // Selecionar o primeiro idioma válido ou definir como OUTRO
        idioma = dadosLivro.idioma().stream()
                .map(Idioma::fromString)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(Idioma.OUTRO);

        // Log do idioma identificado
        System.out.println("Idioma identificado: " + idioma);



        // Verifica se o autor já existe no banco
        Autor autor = autorRepository.findByName(dadosLivro.autores().get(0).getName())
                .orElse(new Autor(dadosLivro.autores().get(0).getName(), dadosLivro.autores().get(0).getBirthYear(), dadosLivro.autores().get(0).getDeathYear()));

        // Cria o livro com o autor
        Livro livro = new Livro(dadosLivro, autor);
        livro.setTitulo(dadosLivro.titulo());
        livro.setAutor(autor);
        livro.setIdioma(idioma);
        livro.setDownloads(String.valueOf(dadosLivro.downloads()));

        // Salva o livro no banco
        return livroRepository.save(livro);
    }

    public List<Livro> buscarLivrosPorAutor(String nomeAutor) {
        Autor autor = autorRepository.findByName(nomeAutor)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        return autor.getLivros();  // Retorna os livros associados ao autor
    }
}
