# LiterAlura 📚

## Descrição

LiterAlura é uma aplicação Java Spring Boot para catálogo de livros que utiliza a API Gutendex para buscar informações sobre livros de domínio público. O projeto permite buscar, armazenar e consultar dados de livros e autores em um banco de dados local.

## Funcionalidades

- 🔍 **Busca de Livros**: Pesquise livros por título na API Gutendex
- 📖 **Catálogo Local**: Armazene livros no banco de dados local
- 👥 **Gestão de Autores**: Cadastro automático e consulta de autores
- 🗓️ **Filtro por Período**: Liste autores vivos em um ano específico
- 🌍 **Filtro por Idioma**: Consulte livros em idiomas específicos
- 📊 **Listagem Organizada**: Visualize livros e autores ordenados alfabeticamente

## Menu Principal

```
--------- Bem vindo ao Literalura! ---------
1 - Buscar livro por título
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um determinado ano
5 - Listar livros em um determinado idioma
0 - Sair
```

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL/H2** - Banco de dados
- **Jackson** - Processamento JSON
- **Gutendex API** - Fonte de dados de livros
- **Maven** - Gerenciamento de dependências

## Estrutura do Projeto

```
src/
├── br/com/alura/literalura/
│   ├── model/
│   │   ├── Autor.java
│   │   ├── DadosLivro.java
│   │   ├── Livro.java
│   │   └── Idioma.java
│   ├── repository/
│   │   ├── AutorRepository.java
│   │   └── LivroRepository.java
│   ├── service/
│   │   ├── ConsumoApi.java
│   │   ├── ConverteDados.java
│   │   └── RespostaApi.java
│   └── principal/
│       └── Principal.java
```

## Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Banco de dados (PostgreSQL recomendado ou H2 para desenvolvimento)
- Conexão com internet para acessar a API Gutendx

## Configuração

### 1. Clone o repositório
```bash
git clone <url-do-repositorio>
cd literalura
```

### 2. Configure o banco de dados
No arquivo `application.properties`:

```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### 3. Execute a aplicação
```bash
mvn spring-boot:run
```

## Como Usar

### 1. Buscar Livro por Título
- Selecione a opção **1**
- Digite o título do livro desejado
- O sistema buscará na API Gutendx e salvará no banco local

### 2. Listar Livros Registrados
- Selecione a opção **2**
- Visualize todos os livros salvos ordenados alfabeticamente

### 3. Listar Autores Registrados
- Selecione a opção **3**
- Digite o nome do autor para buscar
- Visualize todos os livros do autor especificado

### 4. Autores Vivos em Determinado Ano
- Selecione a opção **4**
- Digite o ano desejado
- Visualize autores que estavam vivos naquele ano

### 5. Livros por Idioma
- Selecione a opção **5**
- Digite o código do idioma (ex: 'pt', 'en', 'es')
- Visualize livros no idioma especificado

## Exemplo de Uso

```
--------- Bem vindo ao Literalura! ---------
1 - Buscar livro por título
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um determinado ano
5 - Listar livros em um determinado idioma
0 - Sair
Digite a opção desejada: 1

Digite o título do livro: Dom Casmurro
Livro salvo: Livro{titulo='Dom Casmurro', autor='Machado de Assis', idioma='pt', downloads=1542}
```

## Estrutura de Dados

### Entidade Livro
- **ID**: Identificador único
- **Título**: Nome do livro
- **Autor**: Relacionamento com a entidade Autor
- **Idioma**: Enum representando o idioma
- **Downloads**: Número de downloads (da API)

### Entidade Autor
- **ID**: Identificador único
- **Nome**: Nome do autor
- **Ano de Nascimento**: Ano que nasceu
- **Ano de Morte**: Ano que morreu (0 se ainda vivo)
- **Livros**: Lista de livros do autor

## API Utilizada

**Gutendx API**: https://gutendx.com/
- API gratuita com mais de 60.000 livros de domínio público
- Formato JSON
- Filtros por título, autor, idioma, etc.

### Exemplo de resposta da API:
```json
{
  "results": [
    {
      "title": "Dom Casmurro",
      "authors": [
        {
          "name": "Machado de Assis",
          "birth_year": 1839,
          "death_year": 1908
        }
      ],
      "languages": ["pt"],
      "download_count": 1542
    }
  ]
}
```

## Funcionalidades dos Repositórios

### LivroRepository
```java
// Busca por nome do autor (case insensitive)
List<Livro> findByAutor_NameContainingIgnoreCase(String nomeAutor);
```

### AutorRepository
```java
// Busca autor por nome exato
Optional<Autor> findByName(String name);
```

## Tratamento de Erros

O projeto inclui tratamento para:
- ✅ Livros não encontrados na API
- ✅ Erros de conversão JSON
- ✅ Duplicação de autores no banco
- ✅ Entrada inválida do usuário
- ✅ Problemas de conectividade

## Possíveis Melhorias

- [ ] Interface web (Spring MVC/Thymeleaf)
- [ ] API REST para exposição dos dados
- [ ] Paginação de resultados
- [ ] Cache de consultas à API externa
- [ ] Busca avançada com múltiplos filtros
- [ ] Sistema de favoritos
- [ ] Exportação de dados (CSV, PDF)
- [ ] Validação de entrada mais robusta
- [ ] Logs estruturados
- [ ] Testes unitários e de integração

## Dependências Principais

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
    </dependency>
</dependencies>
```

## Comandos Úteis

```bash
# Executar aplicação
mvn spring-boot:run

# Executar testes
mvn test

# Gerar JAR
mvn clean package

# Executar JAR gerado
java -jar target/literalura-0.0.1-SNAPSHOT.jar
```

## Solução de Problemas

### Problema: Erro de conexão com banco
**Solução**: Verifique as configurações no `application.properties`

### Problema: API não responde
**Solução**: Verifique conexão com internet e status da API Gutendx

### Problema: Livro não encontrado
**Solução**: Tente variações do título ou busque em inglês

## Contribuição

1. Fork o projeto
2. Crie uma branch (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas alterações (`git commit -am 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request

## Licença

Este projeto foi desenvolvido para fins educacionais como parte do curso da Alura.

## Créditos

- **API Gutendx**: https://gutendx.com/
- **Dados dos livros**: Project Gutenberg
- **Framework**: Spring Boot

---

**Nota**: Este projeto utiliza livros de domínio público disponibilizados pelo Project Gutenberg através da API Gutendx.
