# Map

## O que é Map
`Map` guarda pares chave-valor, onde cada **chave é única** — inserir com uma chave já existente **sobrescreve** o valor anterior, não gera duplicata. Diferente de List e Set, um Map não implementa `Collection`; ele tem sua própria hierarquia.

```java
Map<String, String> map = new LinkedHashMap<>();
map.put("taklado", "teclado");
map.get("taklado"); // "teclado"
```

`putIfAbsent()` só insere se a chave ainda não existir — evita sobrescrever um valor já presente sem precisar checar `containsKey()` manualmente antes.

```java
map.putIfAbsent("vc2", "você2"); // só insere se "vc2" ainda não existir no map
```

## HashMap x LinkedHashMap x TreeMap (NavigableMap)
`HashMap` não garante ordem de iteração das chaves. `LinkedHashMap` preserva a ordem de inserção. `TreeMap` (implementação de `NavigableMap`) mantém as chaves sempre **ordenadas**, exigindo `Comparable` na chave ou um `Comparator` no construtor — igual ao TreeSet.

## Iterando um Map
Existem três formas de percorrer um Map, cada uma pra uma necessidade diferente: `keySet()` quando só interessam as chaves, `values()` quando só interessam os valores, `entrySet()` quando você precisa dos dois ao mesmo tempo (é a forma mais eficiente quando precisa de ambos, porque evita fazer um `get(chave)` extra dentro do loop).

```java
for (Map.Entry<String, String> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

## Chaves Precisam de equals()/hashCode() Corretos
Se a chave for um objeto próprio (não `String`/wrapper), a classe **precisa** sobrescrever `equals()` e `hashCode()` corretamente — senão o Map nunca vai considerar duas chaves "diferentes na memória, mas iguais nos dados" como a mesma chave, e `get()` com uma nova instância "igual" nunca vai encontrar o valor guardado.

```java
Map<Consumidor, Manga> consumidorManga = new HashMap<>();
consumidorManga.put(consumidor1, manga1);
// funciona porque Consumidor sobrescreve equals()/hashCode() baseado no id
```

## Valores Complexos
O valor de um Map pode ser qualquer tipo, inclusive outra coleção — útil pra modelar relações "um para muitos" (ex: um consumidor associado a vários mangás).

```java
Map<Consumidor, List<Manga>> consumidorMangaMap = new HashMap<>();
consumidorMangaMap.put(consumidor1, List.of(manga1, manga2, manga3));
```

## Métodos de Navegação do NavigableMap
Assim como o `NavigableSet`, o `NavigableMap` oferece `lowerKey`, `floorKey`, `higherKey`, `ceilingKey` pra encontrar a chave mais próxima de um valor de referência, e `headMap()` pra pegar um submapa com tudo antes (ou até) de determinada chave.

```java
map.ceilingKey("C"); // menor chave >= "C"
map.headMap("C", true); // tudo até "C", inclusive
```
