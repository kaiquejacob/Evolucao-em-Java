# Lambdas

## O que é uma Expressão Lambda
Uma lambda é uma forma compacta de escrever a implementação de uma interface funcional (interface com um único método abstrato), sem precisar declarar uma classe (nem anônima) pra isso. A sintaxe é `(parâmetros) -> corpo` — o compilador já sabe, pelo contexto (o tipo do parâmetro que está recebendo a lambda), qual método está sendo implementado.

```java
strings.forEach(s -> System.out.println(s));
```

Aqui não existe nenhuma declaração de classe ou `@Override` visível — a lambda `s -> System.out.println(s)` é a implementação direta do método abstrato da interface funcional esperada naquele ponto.

## Consumer: Recebe um Valor, não Retorna Nada
`Consumer<T>` é a interface funcional pra quando você quer **fazer algo** com um valor, sem produzir um resultado — o método abstrato dela é `void accept(T t)`. Exemplo típico: percorrer uma lista imprimindo cada elemento.

```java
private static <T> void forEach(List<T> list, Consumer<T> consumer) {
    for (T e : list) {
        consumer.accept(e);
    }
}
```

```java
forEach(strings, s -> System.out.println(s));
forEach(integers, i -> System.out.println(i));
// o mesmo forEach genérico funciona pra qualquer tipo de lista
```

## Function: Recebe um Valor, Retorna Outro (Transformação)
`Function<T, R>` é a interface funcional pra quando você quer **transformar** um valor de um tipo `T` em outro do tipo `R` — o método abstrato é `R apply(T t)`. É a base de qualquer operação de mapeamento (transformar cada elemento de uma lista em outra coisa).

```java
private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
    List<R> result = new ArrayList<>();
    for (T e : list) {
        R r = function.apply(e);
        result.add(r);
    }
    return result;
}
```

```java
List<Integer> integers = map(strings, s -> s.length());     // String -> Integer
List<String> maiusculas = map(strings, s -> s.toUpperCase()); // String -> String
```

Repare que o mesmo método `map` serve pra transformar `String` em `Integer` (tamanho da palavra) ou `String` em `String` (maiúsculas) — o tipo de retorno da lambda que passa é que decide qual é o `R` naquela chamada específica.

## Tipagem Inferida
Numa lambda, normalmente não é preciso declarar o tipo do parâmetro — o compilador infere a partir do contexto (do tipo genérico esperado pela interface funcional). Declarar o tipo explicitamente é opcional e raramente necessário:

```java
List<Integer> integers = map(strings, (String s) -> s.length()); // tipo explícito, redundante aqui
List<Integer> integers2 = map(strings, s -> s.length());          // mesma coisa, mais comum na prática
```
