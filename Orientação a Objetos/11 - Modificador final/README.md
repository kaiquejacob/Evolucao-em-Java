# Modificador final

## final em Variáveis e Atributos
Uma variável ou atributo `final` só pode receber valor **uma vez** — depois disso, tentar reatribuir gera erro de compilação. Para tipos primitivos isso significa um valor verdadeiramente constante.

```java
public static final double VELOCIDADE_LIMITE = 250;
```

Para tipos referência, `final` trava a **referência**, não o conteúdo do objeto — um `final Comprador comprador = new Comprador()` não pode passar a apontar para outro objeto, mas os atributos internos desse `Comprador` continuam alteráveis normalmente.

## final em Métodos
Um método `final` não pode ser sobrescrito por nenhuma subclasse. Usado quando a implementação de um comportamento precisa ser garantida como a mesma em toda a hierarquia, sem risco de uma subclasse mudar esse comportamento.

```java
public final void imprime(){
    System.out.println(this.nome);
}
```

## final em Classes
Uma classe `final` não pode ser estendida (`extends`) por nenhuma outra — a hierarquia de herança para ali. `String` é o exemplo mais conhecido de classe final no próprio Java: ninguém pode criar uma subclasse de `String`.
