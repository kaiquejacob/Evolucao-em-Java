# Métodos

## Declaração e Parâmetros
Um método é um bloco de código nomeado que executa uma ação e pode receber parâmetros de entrada. A assinatura é `modificador tipoDeRetorno nome(parâmetros)`. Os parâmetros são variáveis locais ao método, existem só durante a execução dele.

```java
public void multiplicaDoisNumeros(int num1, int num2) {
    System.out.println(num1 * num2);
}
```

Métodos podem ser sobrecarregados (mesmo nome, assinaturas diferentes — quantidade ou tipo de parâmetros) — isso é resolvido em tempo de compilação, com base no tipo dos argumentos passados na chamada.

## Retorno de Valores
Um método com retorno precisa declarar o tipo do que devolve (em vez de `void`) e usar `return` em todo caminho possível de execução — se existir um `if` sem `else` cobrindo todos os casos, o compilador acusa erro de "missing return statement".

```java
public double divideDoisNumeros(double num1, double num2) {
    if (num2 == 0) {
        return 0;
    }
    return num1 / num2;
}
```

`return` também pode ser usado sozinho (sem valor) em um método `void` só para interromper a execução mais cedo, funcionando como um `break` para o método inteiro.

## Parâmetros: Tipo Primitivo x Tipo Referência
Essa é a pegadinha mais importante do tópico. Java sempre passa parâmetros **por valor** — a diferença é o que esse "valor" representa em cada caso.

Para tipos primitivos, o valor copiado é o próprio dado. Alterar o parâmetro dentro do método não afeta a variável original do chamador:

```java
public void alteraDoisNumeros(int numero1, int numero2) {
    numero1 = 99; // só altera a cópia local
}
// depois da chamada, as variáveis originais continuam com seus valores de antes
```

Para tipos referência (objetos), o valor copiado é o **endereço de memória**. Isso significa que, embora você não consiga fazer a variável original apontar pra outro objeto, você consegue alterar o **estado interno** do objeto apontado, e essa mudança é visível fora do método:

```java
public void imprimi(EstudanteM estudante) {
    estudante.nome = "Luiz"; // altera o objeto original, porque a referência aponta pro mesmo lugar
}
```

## A Palavra-chave this
`this` referencia o próprio objeto em que o método está sendo executado. É usado principalmente para diferenciar um atributo de um parâmetro que tem o mesmo nome:

```java
public void setNome(String nome) {
    this.nome = nome; // this.nome é o atributo, nome é o parâmetro
}
```

## Varargs
Varargs (`tipo... nome`) permite passar uma quantidade variável de argumentos do mesmo tipo, sem precisar montar um array manualmente na chamada. Por baixo dos panos, dentro do método o parâmetro é tratado como um array comum.

```java
public void somaVarArgs(int... numeros) {
    int soma = 0;
    for (int num : numeros) {
        soma += num;
    }
    System.out.println(soma);
}
// chamada:
somaVarArgs(1, 3, 5, 7, 9);
```

Um método só pode ter **um** parâmetro varargs, e ele precisa ser o último da lista de parâmetros.
