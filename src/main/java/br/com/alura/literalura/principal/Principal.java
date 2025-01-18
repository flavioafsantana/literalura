package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Idioma;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import br.com.alura.literalura.service.RespostaApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "https://gutendex.com/books?search=";
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final LivroRepository repositorio;
    private Optional<Autor> buscaAutor;
    private AutorRepository repositorioAutor;
    private Idioma idioma;

    @Autowired
    public Principal(LivroRepository repositorio, AutorRepository repositorioAutor) {
        this.repositorio = repositorio;
        this.repositorioAutor = repositorioAutor;
    }

    public void exibeMenu() {
        int opcao;
        do {
            System.out.println("""
                --------- Bem vindo ao Literalura! ---------
                1 - Buscar livro por título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros em um determinado idioma
                0 - Sair
                Digite a opção desejada: 
            """);
            opcao = leitura.nextInt();
            leitura.nextLine(); // Consumir quebra de linha
            switch (opcao) {
                case 1 -> buscarLivroWeb();
                case 2 -> listarLivrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosEmUmDeterminadoAno();
                case 5 -> listarLivrosEmUmDeterminadoIdioma();
                case 0 -> System.out.println("Até logo!");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void buscarLivroWeb() {
        System.out.print("Digite o título do livro: ");
        String tituloLivro = leitura.nextLine();
        String json = consumo.obterDados(ENDERECO + tituloLivro.replace(" ", "+"));
        System.out.println(ENDERECO + tituloLivro.replace(" ", "+"));
        System.out.println("JSON retornado: " + json);
        try {
            var resposta = conversor.obterDados(json, RespostaApi.class);

            if (resposta.getResults() == null || resposta.getResults().isEmpty()) {
                System.out.println("Nenhum livro encontrado com esse título.");
                return;
            }

            resposta.getResults().forEach(dadosLivro -> {
                Livro livro = new Livro(dadosLivro);

                if (dadosLivro.autores() != null && !dadosLivro.autores().isEmpty()) {
                    Autor autor = dadosLivro.autores().get(0);
                    Optional<Autor> autorExistente = repositorioAutor.findByName(autor.getName());

                    if (autorExistente.isPresent()) {
                        livro.setAutor(autorExistente.get());
                    } else {

                        repositorioAutor.save(autor);
                        livro.setAutor(autor);
                    }
                }

                repositorio.save(livro);
                System.out.println("Livro salvo: " + livro);
            });

        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }
    }


    private void listarLivrosRegistrados() {
        List<Livro> livros = repositorio.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado no sistema.");
        } else {
            livros.stream()
                    .sorted((l1, l2) -> l1.getTitulo().compareToIgnoreCase(l2.getTitulo()))
                    .forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        System.out.println("Digite o nome do autor: ");
        var nomeAutor = leitura.nextLine();
        List<Livro> livrosEncontrados = repositorio.findByAutor_NameContainingIgnoreCase(nomeAutor);
        System.out.println("Livros do autor: " + nomeAutor);
        livrosEncontrados.forEach(System.out::println);
    }

    private void listarAutoresVivosEmUmDeterminadoAno() {
        System.out.print("Digite o ano desejado: ");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autores = repositorioAutor.findAll();

        autores.stream()
                .filter(autor -> autor.getBirthYear() <= ano && (autor.getDeathYear() == 0 || autor.getDeathYear() > ano))
                .forEach(System.out::println);

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado para o ano especificado.");
        }
    }


    private void listarLivrosEmUmDeterminadoIdioma() {
        System.out.print("Digite o idioma desejado (ex: 'pt'): ");
        String idioma = leitura.nextLine();

        List<Livro> livros = repositorio.findAll();
        livros.stream()
                .filter(livro -> livro.getIdioma().toString().equalsIgnoreCase(idioma))
                .forEach(System.out::println);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma especificado.");
        }
    }
}

