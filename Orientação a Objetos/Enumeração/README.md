# Enumeração (enum)

## Enum Básico
Um `enum` define um conjunto fixo e conhecido de constantes. É mais seguro que usar `int` ou `String` soltos pra representar categorias, porque o compilador impede valores fora da lista definida.

```java
public enum StatusPedido {
    ABERTO, PREPARANDO, PRONTO, ENTREGUE
}
```

## Enum com Construtor e Atributos
Um enum pode ter construtor e atributos próprios, tornando cada constante capaz de carregar dados além do próprio nome — cada valor do enum é, na prática, uma instância única e pré-criada dessa classe.

```java
public enum TipoCliente {
    PESSOA_FISICA(1, "Pessoa Física"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");

    public final int valor;
    public final String nomeRelatorio;

    TipoCliente(int valor, String nomeRelatorio) {
        this.valor = valor;
        this.nomeRelatorio = nomeRelatorio;
    }
}
```

## Enum com Método Abstrato (corpo por constante)
Um enum pode declarar um método abstrato e cada constante fornece sua própria implementação, entre chaves `{ }` logo depois do nome da constante. É uma forma elegante de substituir um `switch` grande por comportamento embutido em cada valor.

```java
public enum TipoPagamento {
    DEBITO {
        @Override
        public double calcularDesconto(double valor) {
            return valor * 0.1;
        }
    },
    CREDITO {
        @Override
        public double calcularDesconto(double valor) {
            return valor * 0.05;
        }
    };

    public abstract double calcularDesconto(double valor);
}
```
