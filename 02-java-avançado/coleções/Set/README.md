# Set

## O que é Set
`Set` é uma coleção que **não permite elementos duplicados** e, ao contrário de `List`, não oferece acesso por índice. A definição de "duplicado" depende inteiramente do `equals()`/`hashCode()` da classe do elemento — sem eles bem implementados, um Set pode aceitar objetos "iguais" como se fossem diferentes.

```java
Set<Manga> mangas = new LinkedHashSet<>();
mangas.add(new Manga(2L, "Dragon ball Z", 2.99, 0));
mangas.add(new Manga(2L, "Dragon ball Z", 2.99, 0)); // mesmo id e nome
// só entra um dos dois, porque Manga tem equals()/hashCode() sobrescritos
```

## HashSet x LinkedHashSet
`HashSet` não garante nenhuma ordem específica de iteração — a ordem depende da função hash interna, e pode até mudar entre execuções. `LinkedHashSet` mantém a **ordem de inserção**, útil quando você precisa da garantia de unicidade do Set mas ainda quer prever em que ordem os elementos aparecem ao iterar.

## NavigableSet e TreeSet
`TreeSet` (implementação de `NavigableSet`) mantém os elementos **sempre ordenados**, exigindo que a classe implemente `Comparable` ou que um `Comparator` seja passado no construtor — sem um dos dois, `add()` lança exceção em tempo de execução.

```java
NavigableSet<SmartPhone> set = new TreeSet<>(new SmartPhoneMarcaComparator());
```

## Métodos de Navegação
`NavigableSet` oferece métodos pra encontrar o elemento mais próximo de um valor de referência, sem precisar procurar manualmente: `lower` (estritamente menor), `floor` (menor ou igual), `higher` (estritamente maior), `ceiling` (maior ou igual). `pollFirst()`/`pollLast()` removem e retornam o primeiro/último elemento.

```java
mangas.lower(yuyu);    // o item imediatamente antes de yuyu, pela ordenação
mangas.floor(yuyu);    // igual ou antes
mangas.higher(yuyu);   // depois
mangas.ceiling(yuyu);  // igual ou depois
mangas.pollFirst();    // remove e retorna o primeiro elemento
```
