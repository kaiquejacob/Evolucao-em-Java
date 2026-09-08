# Blocos de Inicialização

## Bloco de Inicialização de Instância
Um bloco `{ }` solto dentro da classe (sem nome, sem `static`) é executado **toda vez que um objeto é criado**, depois que os atributos recebem seus valores padrão e antes do construtor rodar. Serve para inicializar atributos com uma lógica mais elaborada do que uma simples atribuição direta.

```java
public class Serie {
    private int[] episodios;

    {
        System.out.println("Dentro do bloco de inicialização");
        episodios = new int[100];
    }

    public Serie(String nome) {
        this.nome = nome; // roda DEPOIS do bloco acima
    }
}
```

## Bloco de Inicialização Estático
Um bloco `static { }` roda **uma única vez**, quando a JVM carrega a classe pela primeira vez — não a cada objeto criado, só na primeira vez que a classe é referenciada. É o lugar certo para inicializar atributos `static` que dependem de alguma lógica.

```java
public class Desenho {
    private static int[] episodios;

    static {
        System.out.println("Dentro do bloco de inicialização estático");
        episodios = new int[100];
    }
}
```

Se você criar `new Desenho()` três vezes seguidas, a mensagem do bloco estático aparece **uma única vez** (na primeira criação), enquanto um bloco de instância normal apareceria três vezes, uma por objeto.

## Ordem de Execução
Quando a classe é carregada e um objeto é criado pela primeira vez, a ordem é: bloco(s) estático(s) da classe (uma vez só) → atributos de instância recebem valor padrão → bloco(s) de inicialização de instância, na ordem em que aparecem no código → construtor. Múltiplos blocos do mesmo tipo executam na ordem em que estão escritos, de cima para baixo.
