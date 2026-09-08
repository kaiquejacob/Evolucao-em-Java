# Construtores, Get e Set

## O que é um Construtor
Um construtor é um método especial chamado automaticamente quando um objeto é criado com `new`. Ele tem o mesmo nome da classe, não tem tipo de retorno (nem `void`) e serve para inicializar o estado do objeto no momento em que ele nasce.

```java
public class Anime {
    private String nome;

    public Anime(String nome) {
        this.nome = nome;
    }
}
```

## Construtor Padrão (Default)
Se uma classe não declara nenhum construtor, o Java gera automaticamente um construtor sem parâmetros e sem corpo (o "construtor padrão"). No momento em que você escreve **qualquer** construtor na classe, esse construtor padrão implícito deixa de existir — se ainda quiser um construtor vazio, precisa declará-lo explicitamente.

```java
public Anime() {
    System.out.println("Dentro do construtor sem argumentos");
}
```

## Sobrecarga de Construtores
Assim como métodos, construtores podem ser sobrecarregados: várias versões com assinaturas diferentes, dando opções de como criar o objeto dependendo de quais dados estão disponíveis no momento.

```java
public Anime(String nome, String tipo, int episodios, String genero) { ... }
public Anime(String nome, String tipo, int episodios, String genero, String estudio) { ... }
```

## Encadeamento de Construtores com this(...)
`this(...)` chama outro construtor da mesma classe, evitando duplicar código de inicialização entre construtores sobrecarregados. Precisa ser a **primeira linha** do construtor, e só pode existir uma chamada `this(...)` por construtor.

```java
public Anime(String nome, String tipo, int episodios, String genero, String estudio) {
    this(nome, tipo, episodios, genero); // chama o construtor de 4 parâmetros primeiro
    this.estudio = estudio;
}
```

Isso cria uma cadeia: o construtor mais completo delega pro mais simples, que pode delegar pro vazio, e assim por diante — centralizando a lógica de inicialização comum em um único lugar.

## Get e Set
Depois de o objeto construído, getters e setters continuam sendo o canal de leitura/escrita dos atributos (ver pasta de Encapsulamento para a motivação completa). A diferença chave em relação ao construtor: o construtor define o estado **inicial**, get/set gerenciam o estado **durante a vida** do objeto.

```java
public String getNome() {
    return this.nome;
}

public void setNome(String nome) {
    this.nome = nome;
}
```
