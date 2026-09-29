# Threads

## O que é uma Thread
Uma Thread é uma linha de execução independente dentro de um programa — permite que partes do código rodem **concorrentemente**, sem uma esperar a outra terminar. Um programa Java sempre tem pelo menos uma thread (a `main`), e pode criar outras pra rodar tarefas em paralelo (ou intercaladamente, dependendo do número de núcleos disponíveis).

## Criando Threads: extends Thread x implements Runnable
Existem duas formas de definir o que uma thread vai executar. Estender `Thread` e sobrescrever `run()` funciona, mas gasta a única herança que a classe tem (Java não permite herança múltipla) e mistura "ser uma thread" com "ter uma tarefa" na mesma classe. Implementar `Runnable` separa essas responsabilidades: a classe só define **o que** fazer, e uma instância de `Thread` é criada por fora, recebendo esse `Runnable` — por isso `Runnable` é a abordagem preferida na prática.

```java
class ThreadExampleRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
```

```java
Thread t1 = new Thread(new ThreadExampleRunnable('A'), "T1A"); // nome customizado, opcional
t1.start(); // NUNCA chame run() diretamente
```

## start() x run()
Esse é o erro mais comum pra quem começa com threads. Chamar `t1.start()` de fato cria uma nova linha de execução na JVM e, quando o sistema operacional agenda, executa `run()` **nela**. Chamar `t1.run()` diretamente só executa aquele código normalmente, **na thread atual** (geralmente a `main`) — sem criar concorrência nenhuma, como se fosse uma chamada de método comum.

## Prioridade e sleep()
`setPriority()` dá uma "dica" ao escalonador do sistema operacional sobre qual thread deveria receber mais tempo de CPU — não é uma garantia, o SO decide por conta própria como distribuir o tempo de fato. `Thread.sleep(ms)` pausa a execução daquela thread por um tempo, permitindo que outras rodem enquanto isso — lança `InterruptedException` (checked), por isso sempre aparece dentro de um try-catch.

```java
t4.setPriority(Thread.MAX_PRIORITY); // dica de prioridade máxima, não garantia
Thread.sleep(2000); // pausa a thread atual por 2 segundos
```

## join(): Esperando uma Thread Terminar
`t1.join()` faz a thread que chamou `join()` (geralmente a `main`) **esperar** `t1` terminar completamente antes de continuar. Sem isso, não existe garantia de ordem entre o que diferentes threads imprimem.

```java
t1.start();
t1.join();   // main espera t1 terminar
t2.start();  // só começa depois
```

## Condição de Corrida (Race Condition)
Acontece quando duas ou mais threads acessam e alteram o **mesmo dado compartilhado** ao mesmo tempo, sem coordenação, produzindo um resultado incorreto ou imprevisível — porque uma operação como `saldo -= valor` não é atômica (é ler, calcular, escrever em passos separados), e uma thread pode ler o saldo antes da outra terminar de escrever o novo valor.

```java
private void withdrawal(int amount) {
    if (account.getBalance() >= amount) {  // duas threads podem passar aqui "ao mesmo tempo"
        account.withdrawal(amount);         // e as duas sacarem, mesmo sem saldo suficiente pras duas
    }
}
```

## synchronized: Resolvendo a Condição de Corrida
`synchronized` garante que só **uma thread por vez** execute aquele bloco (ou método) para um mesmo objeto de referência (o "lock") — qualquer outra thread que tente entrar precisa esperar a atual sair. É a ferramenta padrão pra proteger seções críticas de código que mexem em estado compartilhado.

```java
synchronized (account) {
    if (account.getBalance() >= amount) {
        account.withdrawal(amount);
    }
}
```

Um método inteiro também pode ser `synchronized`, o que usa o próprio objeto (`this`) como lock automaticamente:

```java
public synchronized void add(String name) {
    names.add(name);
}
```

## Classes Thread-Safe
Uma classe é considerada thread-safe quando garante comportamento correto mesmo acessada por múltiplas threads simultaneamente — geralmente porque todos os pontos de acesso ao estado interno estão protegidos com `synchronized`.

```java
class ThreadSafeNames {
    private final List<String> names = new ArrayList<>();

    public synchronized void add(String name) { names.add(name); }
    public synchronized void removeFirst() { if (!names.isEmpty()) names.remove(0); }
}
```

## Deadlock
Deadlock acontece quando duas threads ficam **esperando uma pela outra indefinidamente**, cada uma segurando um lock que a outra precisa pra continuar — nenhuma consegue avançar. O padrão clássico: thread 1 pega o lock A e espera o lock B; thread 2 pega o lock B e espera o lock A.

```java
// Thread 1: pega lock1, depois tenta pegar lock2
synchronized (lock1) {
    synchronized (lock2) { ... }
}
// Thread 2: pega lock2, depois tenta pegar lock1 -- ordem invertida = risco de deadlock
synchronized (lock2) {
    synchronized (lock1) { ... }
}
```

A forma mais direta de evitar deadlock é garantir que todas as threads adquiram múltiplos locks **sempre na mesma ordem**.

## wait, notify e notifyAll (Produtor-Consumidor)
`wait()` faz a thread atual liberar o lock e entrar em espera até ser notificada — usado dentro de um bloco `synchronized`, tipicamente num `while` que recheca a condição (porque a thread pode acordar sem que a condição realmente tenha mudado, o chamado *spurious wakeup*). `notifyAll()` acorda todas as threads que estão esperando naquele mesmo objeto, deixando elas competirem pra recheck a condição.

```java
public String retrieveEmail() throws InterruptedException {
    synchronized (this.emails) {
        while (this.emails.size() == 0) {
            if (!open) return null;
            this.emails.wait();       // libera o lock e espera
        }
        return this.emails.poll();
    }
}

public void addMemberEmail(String email) {
    synchronized (this.emails) {
        this.emails.add(email);
        this.emails.notifyAll();      // acorda quem estava esperando
    }
}
```

Esse é o padrão clássico produtor-consumidor: uma thread produz dados (adiciona e-mails) e notifica; outra(s) consome(m) (envia e-mails) e espera(m) quando não há nada pra processar — evitando que a thread consumidora fique num loop verificando repetidamente ("busy-waiting"), o que desperdiçaria CPU à toa.
