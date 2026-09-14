# Streams

## O que é uma Stream
Uma Stream é uma sequência de elementos que suporta operações encadeadas de processamento, sem alterar a fonte de dados original (uma List, um array, etc.) e sem guardar os elementos internamente — ela processa sob demanda. O pipeline de uma Stream tem três partes: a **fonte** (`.stream()`), uma cadeia de **operações intermediárias** (retornam outra Stream, permitindo encadear) e uma **operação terminal** (dispara o processamento e produz um resultado — lista, número, `boolean`, etc.).

```java
List<String> titles = lightNovels.stream()          // fonte
        .sorted(Comparator.comparing(LightNovel::getTitle)) // intermediária
        .filter(ln -> ln.getPrice() <= 4)            // intermediária
        .limit(3)                                     // intermediária
        .map(LightNovel::getTitle)                    // intermediária
        .collect(Collectors.toList());                // terminal
```

Streams são **lazy** (preguiçosas): nenhuma operação intermediária executa de fato até a operação terminal ser chamada — é isso que permite otimizações como parar de processar assim que `limit(3)` é atingido, sem precisar passar pelo resto da lista.

## Filtrar, Ordenar, Limitar, Transformar
`filter(predicate)` mantém só os elementos que satisfazem uma condição. `sorted(comparator)` ordena. `limit(n)` corta a Stream nos primeiros `n` elementos. `map(function)` transforma cada elemento num outro tipo. `distinct()` remove duplicados (usando `equals()`).

```java
long count = lightNovels.stream()
        .distinct()
        .filter(ln -> ln.getPrice() <= 4)
        .count();
```

## flatMap: Achatando Streams Aninhadas
Quando cada elemento da Stream é, ele mesmo, uma coleção (uma `List<List<String>>`, por exemplo), `map` produziria uma `Stream<List<String>>` — ainda aninhada. `flatMap` "achata" isso numa única Stream de elementos, combinando todas as sub-listas numa só sequência.

```java
devdojo.stream()
        .flatMap(Collection::stream)   // Stream<List<String>> -> Stream<String>
        .forEach(System.out::println);
```

## Verificações: anyMatch, allMatch, noneMatch
Operações terminais que retornam `boolean`, checando uma condição contra todos os elementos: `anyMatch` (pelo menos um satisfaz), `allMatch` (todos satisfazem), `noneMatch` (nenhum satisfaz). Todas têm **curto-circuito** — param assim que o resultado já está decidido, sem processar a Stream inteira.

```java
lightNovels.stream().anyMatch(ln -> ln.getPrice() > 9);
lightNovels.stream().allMatch(ln -> ln.getPrice() > 0);
```

## Buscando um Elemento: findFirst, findAny, max, min
`findFirst()` retorna o primeiro elemento que passa pelo filtro (respeitando ordem, se houver). `findAny()` retorna qualquer um que satisfaça (mais eficiente em Streams paralelas, onde "primeiro" não tem muito sentido). `max`/`min` retornam o maior/menor segundo um `Comparator`. Todos retornam `Optional`, porque a Stream pode não ter nenhum elemento que satisfaça.

```java
lightNovels.stream()
        .filter(ln -> ln.getPrice() > 3)
        .max(Comparator.comparing(LightNovel::getPrice))
        .ifPresent(System.out::println);
```

## reduce: Combinando Todos os Elementos num Só Valor
`reduce` combina os elementos da Stream numa única saída, aplicando repetidamente uma operação binária (soma, multiplicação, máximo, etc.). A versão com valor inicial (`reduce(identidade, operador)`) garante um retorno mesmo com Stream vazia; a versão sem identidade retorna `Optional`.

```java
integers.stream().reduce((x, y) -> x + y).ifPresent(System.out::println);
integers.stream().reduce(0, Integer::sum); // com valor inicial, retorna direto (sem Optional)
```

## Streams Primitivas: IntStream, LongStream, DoubleStream
Streams de objetos (`Stream<Integer>`) pagam o custo de autoboxing a cada elemento. `IntStream`, `LongStream` e `DoubleStream` trabalham com o tipo primitivo diretamente, evitando esse custo — importante em cálculos numéricos grandes. `mapToDouble`/`mapToInt`/`mapToLong` convertem uma Stream de objetos pra uma primitiva.

```java
double sum = lightNovels.stream()
        .mapToDouble(LightNovel::getPrice)
        .filter(price -> price > 3)
        .sum();

IntStream.rangeClosed(1, 50).filter(n -> n % 2 == 0).forEach(System.out::print);
```

`range` exclui o limite superior, `rangeClosed` inclui — a mesma distinção de sempre entre intervalos abertos e fechados.

## Streams Infinitas: iterate e generate
`Stream.iterate(seed, função)` gera uma sequência infinita aplicando a função repetidamente sobre o valor anterior — precisa de `limit()` pra não rodar pra sempre. `Stream.generate(supplier)` gera valores sem depender do anterior (ex: números aleatórios).

```java
Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]}) // sequência de Fibonacci
        .limit(10)
        .forEach(a -> System.out.println(Arrays.toString(a)));

Stream.generate(() -> random.nextInt(1, 10000)).limit(90).forEach(System.out::println);
```

## Lendo Arquivos como Stream
`Files.lines(path)` retorna uma Stream onde cada elemento é uma linha do arquivo — processa o arquivo linha a linha sem carregar tudo na memória de uma vez, útil pra arquivos grandes. Precisa de try-with-resources porque mantém um recurso de I/O aberto.

```java
try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
    lines.filter(l -> l.contains("Java")).forEach(System.out::println);
}
```

## Collectors: Estatísticas Prontas
Em vez de usar `reduce` manualmente, `Collectors` oferece coletores prontos pra operações comuns: `counting()`, `summingDouble()`, `averagingDouble()`, `maxBy()`, `joining(separador)` (concatena Strings). `summarizingDouble()` calcula tudo de uma vez (min, max, média, soma, contagem) num único objeto `DoubleSummaryStatistics`.

```java
String titles = lightNovels.stream().map(LightNovel::getTitle).collect(Collectors.joining(", "));
DoubleSummaryStatistics stats = lightNovels.stream().collect(Collectors.summarizingDouble(LightNovel::getPrice));
```

## Collectors.groupingBy: Agrupando por uma Característica
`groupingBy(classificador)` substitui o padrão manual de "criar um Map, percorrer a lista, decidir em qual grupo cada item entra" por uma única chamada — agrupa os elementos num `Map`, usando o resultado da função classificadora como chave.

```java
Map<Category, List<LightNovel>> collect = lightNovels.stream()
        .collect(Collectors.groupingBy(LightNovel::getCategory));
```

`groupingBy` aceita um segundo coletor como downstream, permitindo agrupar e já aplicar outra operação em cada grupo (contar, pegar o máximo, agrupar de novo por outro critério — agrupamento multi-nível):

```java
Map<Category, Long> countByCategory = lightNovels.stream()
        .collect(Collectors.groupingBy(LightNovel::getCategory, Collectors.counting()));

Map<Category, Map<Promotion, List<LightNovel>>> nested = lightNovels.stream()
        .collect(Collectors.groupingBy(LightNovel::getCategory,
                Collectors.groupingBy(ln -> ln.getPrice() < 6 ? Promotion.UNDER_PROMOTION : Promotion.NORMAL_PRICE)));
```

## Collectors.toMap
`toMap(chave, valor)` constrói um `Map` diretamente a partir da Stream. Se duas entradas produzirem a mesma chave, é obrigatório fornecer um terceiro argumento (função de merge), senão lança exceção em runtime — diferente do `groupingBy`, que já agrupa duplicatas de chave numa lista automaticamente.

```java
Map<Category, LightNovel> maisCaroPorCategoria = lightNovels.stream()
        .collect(Collectors.toMap(LightNovel::getCategory, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(LightNovel::getPrice))));
```

## Streams Paralelas
`.parallel()` divide o processamento da Stream entre múltiplas threads, usando os núcleos disponíveis do processador — pode acelerar operações custosas em volumes grandes de dados. Mas não é gratuito: tem overhead de coordenação entre threads, e só compensa quando o volume de dados e o custo por elemento são grandes o suficiente pra justificar esse overhead. Pra somas simples em poucos milhões de elementos, um `for` comum ou `LongStream` sequencial pode ser tão rápido (ou mais) que a versão paralela, por causa desse custo extra de gerenciar threads.

```java
long result = LongStream.rangeClosed(1L, num).parallel().reduce(0L, Long::sum);
```

A lição prática de medir os 5 métodos lado a lado (for comum, Stream.iterate sequencial, Stream.iterate paralelo, LongStream sequencial, LongStream paralelo): Stream genérica com `iterate` é a mais lenta de todas (overhead de boxing + geração sequencial), e paralelizar só compensa quando combinado com uma Stream primitiva (`LongStream`) que já é rápida por natureza — paralelizar uma Stream já lenta não resolve o problema de raiz.
