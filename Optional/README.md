# Optional

## O Problema que Optional Resolve
Um método que pode não encontrar um resultado (uma busca por id, por exemplo) tradicionalmente retorna `null` pra sinalizar "não encontrado". O problema é que `null` é invisível na assinatura do método — quem chama só descobre que precisa tratar esse caso lendo a documentação ou, na pior das hipóteses, tomando um `NullPointerException` em produção. `Optional<T>` torna a ausência de valor **explícita no tipo de retorno**: o método deixa claro, só pela assinatura, que o resultado pode não existir.

```java
public static Optional<Manga> findById(Integer id) {
    return findBy(m -> m.getId().equals(id));
}
```

## Criando um Optional
`Optional.of(valor)` cria um Optional com um valor garantidamente não-nulo (lança exceção se você passar `null`). `Optional.ofNullable(valor)` aceita `null` sem lançar exceção, criando um Optional vazio nesse caso. `Optional.empty()` cria diretamente um Optional vazio, sem nem ter um valor candidato.

```java
Optional<String> o1 = Optional.of("Aha unhu o DevDojo é foda"); // valor garantido, non-null
Optional<String> o2 = Optional.ofNullable(null);                 // vira Optional vazio, sem lançar erro
Optional<String> o3 = Optional.empty();                          // vazio direto
```

## Lendo o Valor com Segurança
Em vez de checar `!= null` manualmente, Optional oferece métodos que já embutem esse tratamento. `orElse(valorPadrao)` retorna o valor se existir, ou um padrão se estiver vazio — mas repare que o valor padrão é **sempre avaliado**, mesmo quando não é usado, então não deve ser algo custoso de calcular.

```java
String nome = nameOptional.orElse("EMPTY");
```

`ifPresent(consumer)` só executa a lambda se o valor existir — substitui o clássico `if (valor != null) { ... }`.

```java
nameOptional.ifPresent(s -> System.out.println(s.toUpperCase()));
```

## orElseThrow e orElseGet
`orElseThrow(...)` lança uma exceção customizada se o Optional estiver vazio, em vez de retornar um valor padrão silenciosamente — usado quando a ausência do valor é, de fato, um erro que não deveria ser ignorado.

```java
Manga mangaById = MangaRepository.findById(2).orElseThrow(IllegalArgumentException::new);
```

`orElseGet(supplier)` é parecido com `orElse`, mas recebe uma **função** (`Supplier`) que só é executada se o Optional estiver realmente vazio — ao contrário do `orElse`, que sempre avalia o argumento na hora. Isso importa quando criar o valor alternativo tem custo (nesse caso, criar um novo objeto `Manga`).

```java
Manga newManga = MangaRepository.findByTitle("Drifters")
    .orElseGet(() -> new Manga(3, "Drifters", 20)); // só cria o Manga novo se realmente não achar
```

## orElse x orElseGet: Quando Usar Cada Um
Regra prática: se o valor padrão já existe pronto (uma constante, uma String fixa), `orElse` é suficiente e mais direto. Se o valor padrão precisa ser **construído** (criar um objeto novo, fazer uma chamada, calcular algo), `orElseGet` evita esse custo sendo pago em toda chamada — só paga quando o Optional está de fato vazio.
