package br.com.alura.literalura;

import br.com.alura.literalura.model.DadosAutores;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.principal.Principal;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository; // Injetar o AutorRepository

	@Override
	public void run(String... args) throws Exception {
		// Passando o AutorRepository ao criar o Principal
		Principal principal = new Principal(livroRepository, autorRepository);
		principal.exibeMenu();
	}
}
