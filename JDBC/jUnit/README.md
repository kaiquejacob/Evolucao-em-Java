# Testes Unitários com JUnit

Apesar do nome da pasta (herdado da leva de aulas do curso), esse conteúdo não é sobre banco de dados — é sobre testes automatizados com JUnit 5, junto com dois recursos de linguagem que aparecem nos exemplos: `record` e pattern matching para `instanceof`.

## O que é um Teste Unitário
Um teste unitário verifica se uma unidade isolada de código (geralmente um método) se comporta como esperado, de forma automática e repetível — em vez de testar manualmente rodando o `main` e olhando o console, o teste roda sozinho e falha com uma mensagem clara se o resultado não bater com o esperado.

```java
@Test
void isAdult_ReturnFalse_WhenAgeIsLowerThan18() {
    Assertions.assertFalse(personService.isAdult(notAdult));
}
```

O nome do método de teste segue um padrão descritivo (`o_que_TestaResultado_QuandoCondicao`) — o próprio nome já documenta o que está sendo verificado, sem precisar ler o corpo do método.

## @BeforeEach
Um método anotado com `@BeforeEach` roda **antes de cada teste** da classe, usado pra preparar objetos que vários testes vão reutilizar — evita duplicar a mesma criação de objeto em todo método de teste.

```java
@BeforeEach
public void setUp() {
    adult = new Person(18);
    notAdult = new Person(15);
    personService = new PersonService();
}
```

## Assertions
A classe `Assertions` concentra os métodos de verificação: `assertEquals(esperado, real)` compara dois valores, `assertTrue`/`assertFalse` checam um booleano, `assertThrows(Classe.class, () -> ...)` verifica se um bloco de código lança a exceção esperada.

```java
Assertions.assertThrows(IllegalArgumentException.class,
        () -> personService.isAdult(null), "Person can't be null");
```

## @DisplayName
`@DisplayName` dá um nome legível em linguagem natural ao teste, exibido nos relatórios de execução — útil quando o nome do método (por convenção técnica) não é tão claro de ler quanto uma frase completa.

```java
@Test
@DisplayName("A person should be adult when age is greater or equal than 18")
void isAdult_ReturnTrue_WhenAgeIsGreaterOrEqualsThan18() { ... }
```

## Record: Classes de Dados Imutáveis
`record` é um tipo especial de classe (desde o Java 16) pensado pra carregar dados de forma imutável, sem precisar escrever construtor, getters, `equals()`, `hashCode()` e `toString()` manualmente — o compilador gera tudo isso a partir da lista de componentes declarada.

```java
public record Manga(String name, int episodes) {
    public Manga {
        Objects.requireNonNull(name); // validação no "construtor compacto" do record
    }
}
```

```java
manga1.name();      // getter gerado automaticamente (sem "get" no nome)
manga1.equals(manga2); // equals gerado com base em todos os componentes
Manga.class.isRecord(); // true — dá pra checar em runtime se uma classe é record
```

## Pattern Matching para instanceof
Desde o Java 16, `instanceof` pode declarar a variável do cast **na própria condição**, eliminando o cast manual separado que era necessário antes.

```java
// Antes:
if (employeeDeveloper instanceof Developer) {
    Developer developer = (Developer) employeeDeveloper; // cast manual
}

// Com pattern matching:
if (employeeDeveloper instanceof Developer developer) {
    developer.getMainLanguage(); // "developer" já nasce com o tipo certo, sem cast
}
```
