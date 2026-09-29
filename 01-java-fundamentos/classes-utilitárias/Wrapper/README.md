# Wrapper Classes

## Por que Existem
Cada tipo primitivo tem uma classe "wrapper" correspondente: `byte`→`Byte`, `short`→`Short`, `int`→`Integer`, `long`→`Long`, `float`→`Float`, `double`→`Double`, `char`→`Character`, `boolean`→`Boolean`. Elas existem porque tipos primitivos não são objetos — não têm métodos, não podem ser `null`, e estruturas do Java que trabalham só com objetos (como coleções, que ainda vão aparecer mais à frente no curso) não aceitam primitivos diretamente. O wrapper "embrulha" o valor primitivo numa casca de objeto.

```java
int intP = 1;        // primitivo
Integer intW = 1;    // wrapper do mesmo valor
```

## Autoboxing e Unboxing
Autoboxing é a conversão automática de primitivo para wrapper, e unboxing é o caminho inverso — o compilador faz isso implicitamente, sem precisar de cast manual.

```java
Integer intW = 1;   // autoboxing: int -> Integer, automático
int i = intW;        // unboxing: Integer -> int, automático
```

A pegadinha prática do unboxing: se o wrapper estiver `null` e o código tentar fazer unboxing dele, dá `NullPointerException` em tempo de execução, porque não existe um "int nulo" pra receber o valor.

## Conversão entre String e Número
Os wrappers oferecem métodos estáticos pra converter texto em valor primitivo e vice-versa — é o caminho padrão pra transformar uma entrada de usuário (que sempre chega como texto) em número utilizável.

```java
int numero = Integer.parseInt("42");
boolean verdadeiro = Boolean.parseBoolean("True");
```

## Métodos Utilitários da Character
A classe `Character` concentra vários métodos estáticos úteis pra checar e transformar um único caractere, evitando comparações manuais com faixas de código ASCII/Unicode.

```java
Character.isDigit('9');          // true
Character.isLetterOrDigit('!');  // false
Character.toUpperCase('a');      // 'A'
```
