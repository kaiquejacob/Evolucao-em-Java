# Tratamento de Exceções

## Hierarquia: Throwable, Error e Exception
Toda exceção em Java deriva de `Throwable`, que se divide em dois ramos: `Error` (problemas graves da JVM, tipo `StackOverflowError` ou falta de memória — não são feitos para serem tratados no código da aplicação) e `Exception` (problemas que o próprio programa pode e deve tratar). `RuntimeException` é uma subclasse de `Exception` que representa erros de **programação** (bug), como acessar um índice inválido de array ou dividir por zero.

```java
int[] nums = {1, 2};
System.out.println(nums[2]); // ArrayIndexOutOfBoundsException, um RuntimeException
```

## Checked x Unchecked
Essa é a distinção mais importante do tópico. **Unchecked** são `RuntimeException` e suas subclasses — o compilador não obriga a tratar nem declarar, porque geralmente representam bugs que deveriam ser corrigidos no código, não capturados em runtime. **Checked** são todas as outras subclasses de `Exception` — o compilador **obriga** a capturar (`try/catch`) ou declarar que o método pode lançar (`throws`), porque representam condições externas previsíveis (arquivo que pode não existir, conexão que pode falhar).

```java
public void salvar() throws LoginInvalidoException, FileNotFoundException {
    // o compilador exige o throws aqui, porque são checked
}
```

## Lançando Exceções (throw e throws)
`throw` lança uma instância de exceção no ponto exato do código. `throws` na assinatura do método apenas avisa que aquele método **pode** lançar aquela exceção, repassando a responsabilidade de tratar pra quem chamou.

```java
private static int divisao(int a, int b) {
    if (b == 0) {
        throw new IllegalArgumentException("Argumento ilegal, não pode ser 0");
    }
    return a / b;
}
```

## Bloco finally
O bloco `finally` executa **sempre**, tenha ocorrido exceção ou não, tenha havido `return` no meio do `try` ou não. É o lugar certo pra liberar recursos (fechar arquivo, conexão, etc.), porque garante que aquilo roda independente do que aconteceu no try/catch.

```java
try {
    System.out.println("Abrindo arquivo");
    throw new RuntimeException();
} finally {
    System.out.println("Fechando recurso liberado pelo SO"); // roda mesmo com exceção não tratada
}
```

## Capturando Múltiplas Exceções
Um mesmo `try` pode ter vários `catch`, mas eles são avaliados **na ordem em que aparecem** — por isso uma exceção mais genérica (`RuntimeException`) precisa vir depois das mais específicas, senão o catch genérico "rouba" todos os casos e o específico nunca é alcançado. É possível também capturar mais de um tipo no mesmo `catch` usando `|` (multi-catch), quando o tratamento é idêntico para os dois tipos.

```java
try {
    throw new RuntimeException();
} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
    // trata os dois tipos igual
} catch (RuntimeException e) {
    // qualquer outro RuntimeException cai aqui, tem que vir depois
}
```

## Try-with-Resources
`try (recurso1; recurso2) { }` fecha automaticamente todos os recursos declarados dentro dos parênteses ao final do bloco, na ordem inversa da declaração — sem precisar de um `finally` manual chamando `.close()`. Só funciona com classes que implementam `Closeable` ou `AutoCloseable`.

```java
try (Leitor1 leitor1 = new Leitor1(); Leitor2 leitor2 = new Leitor2()) {
    // usa os leitores aqui
} catch (IOException e) {
    // leitor2.close() e depois leitor1.close() já rodaram automaticamente
}
```

## Exceções Customizadas
Criar uma exceção própria é simplesmente estender `Exception` (checked) ou `RuntimeException` (unchecked), geralmente só repassando a mensagem pro construtor da superclasse. A escolha entre estender uma ou outra define se quem for usar sua exceção será **obrigado** a tratá-la ou não.

```java
public class LoginInvalidoException extends Exception {
    public LoginInvalidoException(String message) {
        super(message);
    }
}
```

## Sobrescrita de Métodos e Exceções Checked
Quando uma subclasse sobrescreve um método que declara `throws` de uma exceção checked, ela não pode declarar exceções checked **novas** ou **mais amplas** do que a superclasse já declarava — só pode manter, restringir, ou não declarar nenhuma. Essa regra existe pra garantir que quem programa contra o tipo da superclasse não seja pego de surpresa por uma exceção que o código dele não estava preparado para tratar.
