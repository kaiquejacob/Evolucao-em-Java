# Concorrência

## AtomicInteger: Alternativa ao synchronized
`AtomicInteger` (e as demais classes do pacote `java.util.concurrent.atomic`) oferece operações **atômicas** sobre um valor — `incrementAndGet()`, por exemplo, executa ler+incrementar+escrever como uma única operação indivisível, sem precisar de `synchronized` nem de lock explícito. Pra contadores simples compartilhados entre threads, é mais leve que sincronizar manualmente.

```java
private AtomicInteger atomicInteger = new AtomicInteger();
atomicInteger.incrementAndGet(); // atômico, seguro entre threads sem lock explícito
```

## Lock e ReentrantLock
`Lock` é uma alternativa mais flexível ao `synchronized`: exige `lock()` e `unlock()` explícitos (por isso sempre dentro de um `try/finally`, garantindo que o lock é liberado mesmo se uma exceção ocorrer), mas em troca oferece recursos que `synchronized` não tem — como checar se a thread atual já segura o lock (`isHeldByCurrentThread()`) ou quantas threads estão esperando (`getQueueLength()`).

```java
lock.lock();
try {
    // seção crítica
} finally {
    lock.unlock(); // sempre no finally, senão um erro no meio trava o lock pra sempre
}
```

"Reentrante" significa que a mesma thread pode adquirir o mesmo lock várias vezes (em chamadas aninhadas) sem travar a si mesma — precisa só soltar o lock o mesmo número de vezes que pegou.

## ReentrantReadWriteLock: Separando Leitura de Escrita
Quando um recurso é **lido com muito mais frequência do que escrito**, usar um lock único para tudo é desperdício — várias threads poderiam ler ao mesmo tempo sem conflito nenhum, só a escrita realmente precisa de exclusividade. `ReentrantReadWriteLock` separa os dois: `readLock()` permite múltiplas threads lendo simultaneamente, `writeLock()` exige exclusividade total (nem outra escrita, nem leitura, podem acontecer ao mesmo tempo).

```java
rwl.writeLock().lock();
try {
    map.put(key, value); // exclusivo, ninguém mais lê ou escreve nesse momento
} finally {
    rwl.writeLock().unlock();
}

rwl.readLock().lock();
try {
    return map.keySet(); // várias threads podem estar aqui ao mesmo tempo
} finally {
    rwl.readLock().unlock();
}
```

## CopyOnWriteArrayList
Uma lista pensada para cenários com **muita leitura e pouquíssima escrita**. Qualquer modificação (`add`, `remove`) cria internamente uma **cópia completa** do array subjacente — por isso escritas são caras, mas em compensação, iterar sobre a lista nunca lança `ConcurrentModificationException`, mesmo que outra thread modifique a lista durante a iteração: o iterator trabalha sobre uma cópia congelada no momento em que foi criado, não vê alterações posteriores.

```java
List<Integer> list = new CopyOnWriteArrayList<>();
Iterator<Integer> iterator = list.iterator(); // "fotografia" da lista nesse instante
// outra thread pode adicionar/remover da lista original sem quebrar esse iterator
```

## BlockingQueue: Fila que Bloqueia
`BlockingQueue` é uma fila pensada especificamente pro padrão produtor-consumidor: `put()` **bloqueia** a thread produtora se a fila estiver cheia (esperando até haver espaço), e `take()` **bloqueia** a thread consumidora se a fila estiver vazia (esperando até haver algo pra pegar). É essencialmente o padrão `wait`/`notify` (visto em Threads) já embutido e pronto pra usar, sem precisar implementar a sincronização manualmente.

```java
BlockingQueue<String> bq = new ArrayBlockingQueue<>(1); // capacidade 1
bq.put("William"); // ok, fila tinha espaço
bq.put("Suane");   // BLOQUEIA aqui até alguém dar take() e abrir espaço
```

## TransferQueue
Uma extensão de `BlockingQueue` com um recurso a mais: `transfer()` só retorna depois que um **consumidor** de fato recebeu o item (não só o guardou na fila) — útil quando o produtor precisa da garantia de que o item foi realmente consumido, não só enfileirado. `tryTransfer()` é a versão que não bloqueia indefinidamente, retornando `false` se não houver consumidor esperando.

## Executors e ExecutorService: Pool de Threads
Criar uma `Thread` nova pra cada tarefa é custoso e não escala. `ExecutorService` mantém um **pool de threads reutilizáveis**, reaproveitando as mesmas threads pra várias tarefas ao longo do tempo. `Executors` oferece fábricas prontas: `newFixedThreadPool(n)` (número fixo de threads), `newCachedThreadPool()` (cria threads sob demanda e reaproveita as ociosas), `newSingleThreadExecutor()` (uma única thread, tarefas em fila).

```java
ExecutorService executorService = Executors.newCachedThreadPool();
executorService.execute(new Printer(1));
executorService.execute(new Printer(2));
executorService.shutdown(); // importante: sem isso, o programa nunca finaliza sozinho
```

`shutdown()` não interrompe tarefas em andamento, só impede que **novas** tarefas sejam aceitas — é essencial chamar, senão as threads do pool ficam vivas indefinidamente, mesmo com o `main` "terminado".

## ScheduledExecutorService
Variante do `ExecutorService` pra tarefas que precisam rodar **com atraso** ou **repetidamente**. `schedule()` roda uma vez após um delay. `scheduleAtFixedRate()` repete numa taxa fixa (não espera a tarefa anterior terminar pra contar o próximo intervalo). `scheduleWithFixedDelay()` repete com um intervalo fixo **depois** que a tarefa anterior termina — a diferença importa quando a duração da tarefa é imprevisível.

```java
executor.scheduleWithFixedDelay(r, 1, 5, TimeUnit.SECONDS); // espera 1s, depois roda a cada 5s (após terminar a anterior)
```

## Callable e Future
`Runnable` não retorna valor nem declara exceção checked. `Callable<V>` resolve as duas limitações: o método `call()` **retorna** um valor e pode lançar `Exception`. Submeter um `Callable` a um `ExecutorService` retorna um `Future<V>` — uma "promessa" do resultado, que ainda não existe no momento em que é criada.

```java
Future<String> future = executorService.submit(randomNumberCallable);
String resultado = future.get(); // bloqueia até o resultado ficar pronto
```

`future.get(timeout, unidade)` evita esperar indefinidamente, lançando `TimeoutException` se o resultado demorar demais.

## CompletableFuture: Assíncrono sem Bloquear Cedo Demais
O problema do `Future` comum: `get()` **bloqueia** a thread até o resultado ficar pronto, então rodar 4 tarefas com `Future` e chamar `get()` em sequência não é muito diferente de rodá-las sequencialmente, na prática — cada `get()` trava esperando aquela tarefa específica. `CompletableFuture` resolve isso permitindo **encadear** o que fazer com o resultado assim que ele chegar, sem bloquear a thread principal enquanto isso.

```java
CompletableFuture<Double> preco = CompletableFuture.supplyAsync(() -> storeService.getPriceSync("Store 1"));
// a thread principal segue livre aqui, não bloqueou nada ainda

preco.join(); // só bloqueia quando você realmente precisa do valor
```

Rodar várias buscas assim, todas iniciadas antes de qualquer `join()`, faz elas executarem **de fato em paralelo** — diferente de chamar `get()` de um `Future` logo depois de criá-lo, que serializa a espera mesmo se as tarefas rodassem em paralelo por baixo.

## Encadeando CompletableFutures: thenApply, thenCompose, thenAccept
`thenApply(function)` transforma o resultado quando ele chegar (equivalente ao `map` de Streams). `thenCompose(function)` é usado quando a função encadeada **também** retorna um `CompletableFuture` — evita acabar com um `CompletableFuture<CompletableFuture<T>>` aninhado (equivalente ao `flatMap` de Streams). `thenAccept(consumer)` consome o resultado sem produzir um novo valor (equivalente a um `Consumer`, não retorna nada).

```java
CompletableFuture.supplyAsync(() -> service.getPriceSync(store))
    .thenApply(Quote::newQuote)                                            // transforma String -> Quote
    .thenCompose(quote -> CompletableFuture.supplyAsync(() -> service.applyDiscount(quote))) // encadeia outro CompletableFuture
    .thenAccept(resultado -> System.out.println(resultado));               // só consome o resultado final
```

## allOf e anyOf
Quando você tem uma lista de `CompletableFuture` e precisa esperar por eles coletivamente: `CompletableFuture.allOf(...)` espera **todos** terminarem. `CompletableFuture.anyOf(...)` espera **o primeiro** que terminar, ignorando os demais — útil quando várias fontes buscam a mesma informação e só a resposta mais rápida importa.

```java
CompletableFuture<Object> primeiroQueTerminar = CompletableFuture.anyOf(completableFutures);
primeiroQueTerminar.join(); // destrava assim que QUALQUER um dos futures terminar
```
