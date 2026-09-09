# Queue

## O que é Queue
`Queue` representa uma fila — a ideia clássica é FIFO (primeiro que entra, primeiro que sai). `poll()` remove e retorna o elemento da frente da fila (ou `null` se vazia, sem lançar exceção); `add()` insere no fim.

```java
Queue<String> fila = new PriorityQueue<>();
fila.add("C");
fila.add("A");
fila.add("B");

while (!fila.isEmpty()) {
    System.out.println(fila.poll());
}
```

## A Pegadinha do PriorityQueue
`PriorityQueue` é a implementação mais comum de `Queue`, mas ela **não é FIFO** — os elementos saem em ordem de **prioridade** (por padrão, a ordem natural via `Comparable`), não na ordem em que foram inseridos. No exemplo acima, mesmo inserindo "C", "A", "B" nessa ordem, o `poll()` retorna "A", "B", "C" — porque `String` já tem ordem natural alfabética, e a PriorityQueue reorganiza por baixo dos panos.

## Prioridade Customizada com Comparator
Assim como List e Set, dá pra passar um `Comparator` no construtor da `PriorityQueue` pra definir a prioridade por outro critério que não a ordem natural. `.reversed()` inverte a ordem de qualquer Comparator existente, útil quando você quer "prioridade máxima primeiro" em vez de "mínima primeiro" (comportamento padrão de uma fila de prioridade baseada em min-heap).

```java
Queue<Manga> mangas = new PriorityQueue<>(new MangaPrecoComparator().reversed());
// sem o reversed(), sairia do mais barato pro mais caro
// com reversed(), sai do mais caro pro mais barato
```

## Quando Usar Queue
Faz sentido quando a ordem de **processamento** importa mais que a ordem de **inserção** — fila de tarefas por prioridade, escalonamento, processamento de eventos onde alguns são mais urgentes que outros. Se o que você precisa é realmente ordem de chegada pura (FIFO estrito, sem prioridade), `LinkedList` também implementa `Queue` e respeita ordem de inserção, ao contrário da `PriorityQueue`.
