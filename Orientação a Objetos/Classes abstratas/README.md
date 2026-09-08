# Classes Abstratas

## O que é uma Classe Abstrata
Uma classe `abstract` não pode ser instanciada diretamente com `new` — ela existe pra ser estendida. Serve para modelar um conceito genérico que só faz sentido através de suas implementações concretas (ex: não existe um "Funcionário genérico" sem saber se é Gerente ou Desenvolvedor).

```java
public abstract class Funcionario extends Pessoa {
    protected double salario;

    public abstract void calculaBonus(); // sem corpo
}
```

## Métodos Abstratos
Um método abstrato não tem implementação na classe abstrata — só a assinatura. Toda subclasse **concreta** (não abstrata) é obrigada a implementar todos os métodos abstratos herdados, senão o compilador acusa erro.

```java
public class Gerente extends Funcionario {
    @Override
    public void calculaBonus() {
        this.salario = this.salario + this.salario * 0.2;
    }
}
```

## Pegadinha: Chamar Método Abstrato no Construtor
Se o construtor da superclasse abstrata chama um método abstrato, quem executa de fato é a implementação da **subclasse**, mesmo estando dentro do construtor da super — porque a dispatch de método em Java é sempre polimórfica, baseada no tipo real do objeto, não no tipo da referência em que o código está escrito. Isso funciona, mas é arriscado se o método abstrato usar algum atributo que a subclasse ainda não inicializou nesse ponto.

```java
public Funcionario(String nome, double salario) {
    this.nome = nome;
    this.salario = salario;
    calculaBonus(); // já roda a versão de Gerente ou Desenvolvedor aqui
}
```
