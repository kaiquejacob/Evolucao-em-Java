# Encapsulamento (private, get, set)

## O Problema que o Encapsulamento Resolve
Quando os atributos de uma classe são `public`, qualquer código externo pode ler e alterar o estado do objeto livremente, sem nenhuma validação — nada impede, por exemplo, atribuir uma idade negativa. Encapsulamento é o princípio de esconder o estado interno do objeto e controlar como ele é acessado e alterado, expondo só o que é necessário através de métodos.

## O Modificador private
`private` restringe o acesso ao atributo (ou método) apenas à própria classe onde ele foi declarado — nem subclasses, nem outras classes do mesmo pacote conseguem acessar diretamente.

```java
public class Pessoa {
    private String nome;
    private int idade;
}
```

Fora da classe `Pessoa`, tentar fazer `pessoa.nome = "Sandro"` gera erro de compilação. O único jeito de interagir com esses atributos de fora é através de métodos públicos que a própria classe expõe.

## Getters e Setters
Getters retornam o valor de um atributo, setters atribuem um novo valor. Pela convenção do Java, o nome segue `getNomeDoAtributo()` / `setNomeDoAtributo(valor)` (para `boolean`, o getter costuma ser `isNomeDoAtributo()`).

```java
public String getNome() {
    return this.nome;
}

public void setNome(String nome) {
    this.nome = nome;
}
```

## Validação dentro do Setter
A vantagem real de usar setter em vez de atributo público é poder validar o valor antes de aceitar a mudança — é o lugar certo para garantir que o objeto nunca fique em um estado inválido.

```java
public void setIdade(int idade) {
    if (idade < 0) {
        System.out.println("Idade inválida");
        return;
    }
    this.idade = idade;
}
```

Se essa validação estivesse só no `main` (ou em qualquer lugar que use o objeto), toda parte do código que cria ou altera uma `Pessoa` precisaria repetir essa checagem — e bastaria esquecer uma vez para o objeto acabar num estado inconsistente. Colocando no setter, a regra vale sempre, independente de quem chama.
