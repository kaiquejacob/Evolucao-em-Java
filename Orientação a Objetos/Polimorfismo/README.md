# Polimorfismo

## Referência do Tipo Pai, Objeto do Tipo Filho
Polimorfismo permite declarar uma variável com o tipo da superclasse (ou interface) e atribuir a ela um objeto de qualquer subclasse concreta. A variável só "enxerga" os métodos definidos no tipo declarado, mas a execução de fato roda a versão sobrescrita pela classe real do objeto.

```java
Produto produto = new Computador("Ryzen 9", 3000); // referência Produto, objeto Computador
produto.calcularImposto(); // executa a versão de Computador, não uma genérica de Produto
```

## Como Funciona (Dynamic Dispatch)
A decisão de qual implementação de método rodar acontece em **tempo de execução**, com base no tipo real do objeto na memória — não no tipo declarado da variável. Isso é o que permite escrever um único método que funciona para qualquer subtipo, sem precisar de `if/else` checando o tipo.

```java
public static void calcularImposto(Produto produto) {
    double imposto = produto.calcularImposto(); // decide em runtime qual versão chamar
}
```

## instanceof e Casting
Às vezes você precisa acessar um método que só existe na subclasse específica, não no tipo declarado. `instanceof` checa se o objeto é (ou herda de) determinado tipo antes de fazer o casting, evitando um `ClassCastException` em tempo de execução.

```java
if (produto instanceof Tomate) {
    Tomate tomate = (Tomate) produto; // casting seguro, já validado pelo instanceof
    System.out.println(tomate.getDataValidade());
}
```

## Programação Orientada a Interface
Uma boa prática decorrente de polimorfismo é depender de uma interface (ou classe abstrata) em vez de uma implementação concreta — assim é possível trocar a implementação (ex: trocar `RepositorioMemoria` por `RepositorioBancoDeDados`) sem alterar o código que usa o repositório.

```java
Repositorio repositorio = new RepositorioBancoDeDados(); // troca fácil pra RepositorioMemoria, sem mudar o resto
repositorio.salvar();
```
