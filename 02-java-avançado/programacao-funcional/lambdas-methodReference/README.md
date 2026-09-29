# Lambdas & Method Reference

## O que é uma Expressão Lambda
Uma lambda é uma forma compacta de escrever a implementação de uma interface funcional (interface com um único método abstrato), sem precisar declarar uma classe (nem anônima) pra isso. A sintaxe é `(parâmetros) -> corpo` — o compilador já sabe, pelo contexto (o tipo do parâmetro que está recebendo a lambda), qual método está sendo implementado.

```java
strings.forEach(s -> System.out.println(s));
```

## Consumer: Recebe um Valor, não Retorna Nada
`Consumer<T>` é a interface funcional pra quando você quer **fazer algo** com um valor, sem produzir um resultado — o método abstrato dela é `void accept(T t)`.

```java
private static <T> void forEach(List<T> list, Consumer<T> consumer) {
    for (T e : list) {
        consumer.accept(e);
    }
}
```

```java
forEach(strings, s -> System.out.println(s));
forEach(integers, i -> System.out.println(i));
```

## Function: Recebe um Valor, Retorna Outro (Transformação)
`Function<T, R>` é a interface funcional pra quando você quer **transformar** um valor de um tipo `T` em outro do tipo `R` — o método abstrato é `R apply(T t)`.

```java
private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
    List<R> result = new ArrayList<>();
    for (T e : list) {
        R r = function.apply(e);
        result.add(r);
    }
    return result;
}
```

```java
List<Integer> integers = map(strings, s -> s.length());
List<String> maiusculas = map(strings, s -> s.toUpperCase());
```

---

## O que é Method Reference
Quando o corpo de uma lambda **só chama um método já existente**, sem fazer mais nada além disso, dá pra substituir a lambda inteira por uma referência direta a esse método, usando `::`. É puramente uma forma mais enxuta de escrever a mesma coisa — não muda o comportamento, só remove a redundância de "declarar um parâmetro só pra repassar ele pro método".

```java
Collections.sort(animeList, (a1, a2) -> AnimeComparators.compareByTitle(a1, a2)); // lambda
Collections.sort(animeList, AnimeComparators::compareByTitle);                     // method reference equivalente
```

Existem 4 categorias de method reference, dependendo de onde o método referenciado mora.

## 1. Referência a Método Estático
`Classe::metodoEstatico`. Usado quando o método que a lambda chamaria é `static`.

```java
Collections.sort(animeList, AnimeComparators::compareByEpisodes);
// equivalente a: (a1, a2) -> AnimeComparators.compareByEpisodes(a1, a2)
```

## 2. Referência a Método de Instância de um Objeto Específico
`objeto::metodo`. Usado quando o método pertence a uma instância **já existente** e conhecida no escopo — a referência "captura" esse objeto específico.

```java
AnimeComparators animeComparators = new AnimeComparators();
animeList.sort(animeComparators::compareByEpisodesNonStatic);
// equivalente a: (a1, a2) -> animeComparators.compareByEpisodesNonStatic(a1, a2)
```

## 3. Referência a Método de Instância de um Objeto Arbitrário de um Tipo
`Classe::metodo` (sem instância nenhuma antes do `::`). A diferença sutil pra categoria 1: aqui o método **não é estático**, e o primeiro parâmetro da interface funcional vira o objeto em que o método é chamado — os parâmetros seguintes viram os argumentos do método.

```java
list.sort(String::compareTo);
// equivalente a: (s1, s2) -> s1.compareTo(s2)
// o primeiro parâmetro (s1) vira quem CHAMA o método, o segundo (s2) vira o argumento

Function<String, Integer> numStringToInteger = Integer::parseInt;
// equivalente a: s -> Integer.parseInt(s) -- aqui parseInt é estático, mas o padrão de uso é o mesmo

BiPredicate<List<String>, String> checkName = List::contains;
// equivalente a: (lista, nome) -> lista.contains(nome)
```

## 4. Referência a Construtor
`Classe::new`. Usado quando a lambda só serve pra criar um objeto novo, repassando os parâmetros recebidos direto pro construtor.

```java
Supplier<AnimeComparators> newAnimeComparators = AnimeComparators::new;
AnimeComparators animeComparators = newAnimeComparators.get(); // chama o construtor sem argumentos

BiFunction<String, Integer, Anime> animeBiFunction = Anime::new;
Anime anime = animeBiFunction.apply("Super campeões", 36);
// equivalente a: (title, episodes) -> new Anime(title, episodes)
```

## Quando Usar Method Reference em vez de Lambda
Só faz sentido quando a lambda **não faz nada além de chamar um método existente** — se tem qualquer lógica extra (uma condição, uma transformação antes de chamar), a lambda continua sendo necessária, porque method reference não tem espaço pra lógica adicional, só o repasse direto de parâmetros.
