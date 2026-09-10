# Classes Internas

## Classe Interna (Inner Class)
Uma classe interna é declarada **dentro** de outra classe, sem `static`. A diferença fundamental: ela sempre precisa de uma instância da classe externa pra existir — não faz sentido uma `Inner` sem um `OuterClassesTest01` ao redor, porque a classe interna tem acesso direto aos atributos (inclusive `private`) do objeto externo que a criou.

```java
public class OuterClassesTest01 {
    private String name = "Monkey D. Luffy";

    class Inner {
        public void printOuterClassAttribute() {
            System.out.println(name); // acessa o atributo da classe externa direto
        }
    }
}
```

Pra criar uma instância da classe interna de fora, é necessário passar pela instância externa primeiro:

```java
OuterClassesTest01 outerClass = new OuterClassesTest01();
Inner inner = outerClass.new Inner(); // sintaxe: instânciaExterna.new Interna()
```

Dentro da classe interna, `OuterClassesTest01.this` referencia especificamente o objeto da classe externa (diferente de `this`, que referencia o próprio objeto interno) — útil quando os dois têm um método ou atributo de mesmo nome e seria ambíguo usar só `this`.

## Classe Local
Uma classe local é declarada **dentro de um método**, e só existe (só pode ser instanciada) dentro daquele método — é o escopo mais restrito possível pra uma classe em Java. Ela consegue acessar variáveis locais do método que a envolve, desde que sejam efetivamente `final` (não reatribuídas depois de inicializadas).

```java
void print(final String param) {
    final String lastName = "Izuku";

    class LocalClass {
        public void printLocal() {
            System.out.println(param);              // acessa parâmetro do método
            System.out.println(name + " " + lastName); // acessa atributo da externa + variável local
        }
    }
    new LocalClass().printLocal();
}
```

Usada quando uma classe auxiliar só faz sentido dentro do contexto de um único método específico, sem necessidade de reutilização em nenhum outro lugar.

## Classe Anônima
Uma classe anônima é uma classe **sem nome**, declarada e instanciada no mesmo lugar — geralmente usada para fornecer uma implementação única de uma classe ou interface, sem precisar criar um arquivo `.java` separado só pra isso.

```java
Animal animal = new Animal() {
    @Override
    public void walk() {
        System.out.println("Walking in the shadows"); // sobrescreve na hora, sem nomear a subclasse
    }
};
animal.walk();
```

É especialmente comum quando a implementação só vai ser usada **uma vez**, naquele ponto específico do código — criar uma classe nomeada separada seria excesso de estrutura pra algo que não vai ser reaproveitado.

## Classe Aninhada Estática (Static Nested Class)
Diferente da classe interna comum, uma classe aninhada `static` **não precisa** de uma instância da classe externa pra existir — ela se comporta quase como uma classe independente, só que "guardada" dentro do namespace de outra classe por organização. Não tem acesso direto a atributos de instância da externa (só a membros `static` dela), a não ser que receba uma referência explícita.

```java
public class OuterClassesTest03 {
    private String name = "William";

    static class Nested {
        private String lastName = "Suane";

        void print() {
            System.out.println(new OuterClassesTest03().name + " " + lastName); // precisa criar uma instância pra acessar name
        }
    }
}
```

Instanciar não exige a classe externa:

```java
OuterClassesTest03.Nested nested = new OuterClassesTest03.Nested(); // sem precisar de "outer.new"
```

## Quando Usar Cada Uma
Aninhada estática quando a classe interna não precisa de nada do objeto externo (é essencialmente independente, só organizada ali por contexto). Interna comum quando ela realmente precisa acessar o estado do objeto externo o tempo todo. Local quando o uso é restrito a um único método. Anônima quando é uma implementação descartável, usada uma única vez, geralmente de uma interface ou classe abstrata.
