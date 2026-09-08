# Entrada de Dados (Scanner)

## Lendo Diferentes Tipos
`Scanner` lê a entrada do usuário no console. Cada tipo primitivo tem seu método (`nextInt()`, `nextDouble()`, `nextLine()` para String completa, `next()` para uma única palavra).

```java
Scanner scanner = new Scanner(System.in);
System.out.println("Digite sua idade: ");
int idade = scanner.nextInt();
```

## A Pegadinha do Buffer com nextLine()
Esse é o erro mais comum de quem está aprendendo Scanner. Métodos como `nextInt()` e `nextDouble()` **não consomem** o caractere de quebra de linha (`\n`) que fica no buffer depois que o usuário aperta Enter. Se a próxima leitura for um `nextLine()`, ele vai capturar essa quebra de linha "sobrando" e retornar uma string vazia, em vez de esperar o usuário digitar algo novo.

```java
int idade = scanner.nextInt();
scanner.nextLine();              // consome o \n deixado no buffer
String nome = scanner.nextLine(); // agora lê corretamente
```

A solução padrão é sempre intercalar um `scanner.nextLine()` "limpando o buffer" entre uma leitura numérica e uma leitura de linha completa.

## Lendo um Único Caractere
Não existe um método `nextChar()`. Para ler um único caractere, o padrão é ler uma palavra com `next()` e pegar a primeira posição com `charAt(0)`:

```java
char sexo = scanner.next().charAt(0);
```
