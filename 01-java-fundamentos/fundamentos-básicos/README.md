# Fundamentos da Linguagem

## Tipos Primitivos
Java tem 8 tipos primitivos: `byte` (8 bits), `short` (16 bits), `int` (32 bits), `long` (64 bits), `float` (32 bits, ponto flutuante), `double` (64 bits, ponto flutuante), `char` (16 bits, um caractere UTF-16) e `boolean` (`true`/`false`). Cada um tem um valor padrão quando declarado como atributo de classe e não inicializado (0 para os numéricos, `false` para boolean, `'\u0000'` para char) — variáveis locais dentro de método **não** recebem valor padrão, precisam ser inicializadas antes de usar, senão o compilador acusa erro.

A pegadinha mais comum é sobre literais: por padrão, todo número inteiro literal é `int` e todo número decimal literal é `double`. Se você quer atribuir a um `long` ou `float`, precisa do sufixo `L` ou `F`, senão pode dar erro de compilação (por perda de precisão) dependendo do valor.

```java
long numeroGrande = 3000000000L;   // sem o L, estoura o limite do int e não compila
float salario = 2000.50F;          // sem o F, é interpretado como double
```

`char` guarda um único caractere entre aspas simples, mas por baixo dos panos é um número (código UTF-16) — por isso dá pra fazer aritmética com char:

```java
char letra = 'A';
char proxima = (char) (letra + 1);   // 'B'
```

## Operadores
Os aritméticos (`+ - * / %`) funcionam como esperado, com uma pegadinha: divisão entre dois `int` trunca o resultado, ela nunca gera casa decimal.

```java
int a = 5;
int b = 2;
System.out.println(a / b);          // 2, não 2.5
System.out.println((double) a / b); // 2.5, com cast
```

Os relacionais (`> < >= <= == !=`) comparam valores e retornam `boolean`. Cuidado com `==` em objetos (incluindo `String`): ele compara referência de memória, não conteúdo — para comparar conteúdo de String, o correto é `.equals()`.

Os lógicos `&&` (AND) e `||` (OR) são **curto-circuito**: param de avaliar assim que o resultado já está decidido. Isso não é só otimização, é usado ativamente para evitar erros:

```java
if (lista != null && lista.size() > 0) { ... }
// se lista for null, a segunda condição nunca é avaliada, evitando NullPointerException
```

## Estruturas Condicionais
`if/else` avalia uma condição booleana. `switch` é uma alternativa mais legível quando você compara **uma mesma variável** contra vários valores possíveis — aceita `byte`, `short`, `int`, `char`, `String` e `enum`, mas não aceita `long` nem `boolean`.

A pegadinha clássica é o **fall-through**: sem `break`, a execução continua caindo pelos próximos cases até achar um break ou acabar o switch. Às vezes isso é usado de propósito, pra agrupar cases com o mesmo resultado:

```java
switch (dia) {
    case 1:
    case 7:
        System.out.println("Fim de semana"); // dia 1 OU dia 7 caem aqui
        break;
    default:
        System.out.println("Dia útil");
}
```

O operador ternário (`condição ? valorSeVerdadeiro : valorSeFalso`) é um `if/else` compacto que **retorna um valor**, então só serve quando os dois lados produzem algo pra atribuir ou imprimir:

```java
String status = idade >= 18 ? "Maior de idade" : "Menor de idade";
```

## Estruturas de Repetição
`for` é indicado quando você já sabe (ou controla) o número de iterações via um contador. `while` é indicado quando a condição de parada depende de algo que só se resolve durante a execução do loop. `do-while` executa o bloco **pelo menos uma vez** antes de checar a condição — a diferença prática em relação ao `while` comum, que pode nunca executar se a condição já começar falsa.

```java
int tentativas = 0;
do {
    tentativas++;
    // tenta algo aqui
} while (tentativas < 3);
```

`break` interrompe o loop inteiro imediatamente. `continue` pula só a iteração atual e volta a checar a condição — não sai do loop:

```java
for (int i = 0; i <= 10; i++) {
    if (i % 2 != 0) continue;   // pula os ímpares
    System.out.println(i);      // imprime só os pares
}
```

## Arrays
Um array tem tamanho **fixo**, definido na criação — depois de criado, não dá pra aumentar ou diminuir (diferente de uma lista). É indexado a partir de 0, e o último índice válido é `length - 1`. Elementos não inicializados recebem o valor padrão do tipo.

```java
int[] idades = new int[3];   // {0, 0, 0}
idades[0] = 21;
```

`array.length` é um **atributo**, não um método — diferente de `String.length()`, que é método com parênteses. Isso confunde no começo.

Arrays multidimensionais em Java são, na prática, arrays de arrays. Por causa disso, dá pra criar arrays "irregulares" (jagged arrays), onde cada linha tem um tamanho diferente:

```java
int[][] jagged = new int[3][];
jagged[0] = new int[2];
jagged[1] = new int[]{1, 2, 3};
jagged[2] = new int[]{1, 2, 3, 4, 5, 6};
```

O foreach (`for (tipo variavel : array)`) é uma forma simplificada de percorrer um array quando você não precisa do índice:

```java
for (int num : idades) {
    System.out.println(num);
}
```
