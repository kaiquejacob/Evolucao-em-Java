# Classes e Objetos

## O que é uma Classe e um Objeto
Uma classe é o molde que define quais atributos e comportamentos um tipo de objeto vai ter — ela não ocupa espaço em memória por si só, é só a definição. Um objeto é uma instância concreta criada a partir desse molde, com seu próprio espaço na memória (heap) e seus próprios valores para os atributos. Dois objetos da mesma classe existem de forma completamente independente.

```java
public class Carro {
    public String nome;
    public String modelo;
    public int ano;
}
```

Aqui `Carro` é a classe. Ela não tem nenhum carro "dentro" dela — é só a estrutura que qualquer carro vai seguir.

## Criando Objetos e Acessando Atributos
O operador `new` aloca memória pro objeto e chama o construtor da classe. Depois de criado, os atributos são acessados com `.` (ponto):

```java
Carro carro01 = new Carro();
carro01.nome = "Fusca";
carro01.modelo = "Sport";
carro01.ano = 1969;
```

Atributos não inicializados recebem o valor padrão do tipo (`null` para String, `0` para int, etc.) — por isso, se você criar um `Carro` e não atribuir `ano`, ele vale `0`, não dá erro.

Nesse estágio do curso os atributos ainda são `public`, o que significa que qualquer código externo pode ler e alterar o estado do objeto livremente, sem nenhuma validação. Isso é proposital: serve pra depois justificar por que encapsulamento (`private` + get/set) é necessário — sem ele, nada impede um `carro.ano = -500`.

## Referência de Objetos
Essa é a parte que mais gera confusão pra quem vem de tipos primitivos. Uma variável de tipo objeto **não guarda o objeto**, ela guarda um endereço de memória que aponta pra onde o objeto realmente está. Atribuir uma variável de objeto a outra copia esse endereço — não cria uma cópia do objeto:

```java
Carro carro01 = new Carro();
carro01.nome = "Fusca";

Carro carro02 = carro01;   // carro02 aponta pro MESMO objeto que carro01
carro02.nome = "Gol";

System.out.println(carro01.nome); // imprime "Gol" — os dois mudaram, porque são a mesma referência
```

Isso é fundamentalmente diferente de um tipo primitivo, onde `int b = a` copia o **valor**, e mudar `b` depois não afeta `a`. Com objetos, só existe uma forma de ter duas variáveis realmente independentes: criar dois objetos separados com `new`.
