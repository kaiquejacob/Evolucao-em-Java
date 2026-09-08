# Interfaces

## O que é uma Interface
Uma interface define um **contrato**: um conjunto de métodos que qualquer classe que a implemente é obrigada a fornecer. Diferente de classe abstrata, uma classe pode implementar **várias** interfaces ao mesmo tempo (Java não permite herança múltipla de classes, mas permite de interfaces).

```java
public interface DataLoader {
    public abstract void carregar();
}
```

```java
public class FileLoader implements DataLoader, DataRemover {
    @Override
    public void carregar() { ... }
    @Override
    public void remove() { ... }
}
```

## Métodos default
Um método `default` numa interface já vem com implementação pronta, e a classe que implementa a interface pode usar essa implementação sem escrever nada, ou sobrescrever se precisar de um comportamento diferente. Foi introduzido pra permitir adicionar novos métodos a interfaces já existentes sem quebrar todas as classes que já a implementavam.

```java
public interface DataLoader {
    default void checkPermission(){
        System.out.println("Fazendo checagem de permissões");
    }
}
```

## Métodos static em Interface
Uma interface também pode ter métodos `static`, chamados diretamente pelo nome da interface (`DataLoader.retriveMaxDataSize()`), sem precisar de uma instância. Servem como métodos utilitários relacionados ao contrato da interface.

```java
public static void retriveMaxDataSize(){
    System.out.println("Dentro do retriveMaxDataSize no DataLoader");
}
```
