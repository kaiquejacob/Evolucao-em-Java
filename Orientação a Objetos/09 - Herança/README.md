# Herança

## extends e super
Herança permite que uma classe (subclasse) reutilize atributos e métodos de outra (superclasse), usando `extends`. `super(...)` chama o construtor da superclasse e precisa ser a primeira linha do construtor da subclasse — se você não escrever `super(...)` explicitamente, o Java tenta chamar o construtor **sem argumentos** da superclasse automaticamente, e dá erro de compilação se ele não existir.

```java
public class Funcionario extends Pessoa {
    public Funcionario(String nome) {
        super(nome); // chama o construtor de Pessoa
    }
}
```

## O Modificador protected
`protected` é um meio-termo entre `private` e `public`: o membro fica acessível dentro do mesmo pacote **e** para subclasses em outros pacotes. É o que permite, por exemplo, que `Funcionario` acesse `this.nome` mesmo que `nome` esteja declarado como `protected` na superclasse `Pessoa`, e não `private`.

```java
public class Pessoa {
    protected String nome; // subclasses conseguem acessar diretamente
}
```

## Ordem de Inicialização
Ao criar um objeto de uma subclasse, a ordem é sempre de "fora pra dentro" na hierarquia: primeiro os blocos estáticos da superclasse (uma vez), depois os blocos estáticos da subclasse (uma vez), então para cada objeto: atributos da superclasse recebem valor padrão → blocos de inicialização da superclasse → construtor da superclasse → atributos da subclasse recebem valor padrão → blocos de inicialização da subclasse → construtor da subclasse. Ou seja, a superclasse está sempre totalmente construída antes da subclasse começar a se construir.
