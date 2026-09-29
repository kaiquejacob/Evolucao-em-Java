# Generics

## O Problema que Generics Resolve
Antes de generics, uma coleção sem tipo genérico guardava `Object`, o que exigia casting manual toda vez que você lia um elemento, e não dava nenhuma garantia em tempo de compilação sobre o que realmente estava dentro dela — um `List` podia misturar `String` e `Integer` sem o compilador reclamar, e o erro só aparecia em runtime no momento do cast. Generics permite parametrizar uma classe ou método com um tipo, movendo essa checagem pra tempo de **compilação**.

```java
List<String> lista = new ArrayList<>();
lista.add("Midoriya");

for (String s : lista) { ... }   // sem cast, o compilador já sabe que é String
```

## Classe Genérica
Uma classe genérica usa um parâmetro de tipo (convenção: `T`, `E`, `K`, `V`) no lugar de um tipo fixo, permitindo que a mesma classe funcione pra qualquer tipo de objeto sem duplicar código. É a evolução direta de ter uma classe separada (`CarroRentavelService`, `BarcoRentavelService`) pra cada tipo alugável, quando a lógica interna é idêntica.

```java
public class RentalService<T> {
    private List<T> objetosDisponiveis;

    public RentalService(List<T> objetosDisponiveis) {
        this.objetosDisponiveis = objetosDisponiveis;
    }

    public T buscarObjetoDisponivel() {
        return objetosDisponiveis.remove(0);
    }
}
```

```java
RentalService<Carro> rentalServiceCarro = new RentalService<>(carrosDisponiveis);
RentalService<Barco> rentalServiceBarco = new RentalService<>(barcosDisponiveis);
// mesma classe, reaproveitada pra Carro e Barco, sem duplicar RentalService
```

## Método Genérico
Um método também pode ter seu próprio parâmetro de tipo, independente da classe ao redor ter ou não generics — o `<T>` antes do tipo de retorno declara esse parâmetro, escopado só àquele método.

```java
private static <T> List<T> criarArrayComUmObjeto(T t) {
    return List.of(t);
}
```

```java
List<Barco> barcoList = criarArrayComUmObjeto(new Barco("Canoa Marota"));
// o mesmo método funciona pra qualquer tipo, o T é inferido pelo argumento passado
```

## Bounded Types (extends em Generics)
É possível restringir quais tipos um parâmetro genérico aceita, usando `extends` — nesse contexto, `extends` também vale para interfaces, não só classes. Isso permite que o método genérico use métodos daquele tipo (como `compareTo` de `Comparable`) sem precisar de cast.

```java
private static <T extends Comparable<T>> List<T> criarArrayComUmObjeto(T t) {
    return List.of(t);
}
// só aceita tipos que implementam Comparable<T>
```

## Wildcards: ? extends e ? super
Wildcards (`?`) são usados quando você não precisa nomear o tipo genérico exato, só descrever uma relação com ele — comum em parâmetros de método que só **leem** ou só **escrevem** numa lista.

`? extends Animal` aceita uma lista de `Animal` ou de qualquer subtipo, mas só permite **ler** dela com segurança (o compilador não sabe se é uma `List<Cachorro>` ou `List<Gato>`, então não deixa adicionar nada, só remover/ler como `Animal`):

```java
private static void printConsulta(List<? extends Animal> animals) {
    for (Animal animal : animals) {
        animal.consulta(); // ok, ler é seguro
    }
}
```

```java
printConsulta(cachorros); // List<Cachorro>
printConsulta(gatos);     // List<Gato>
// o mesmo método aceita as duas, graças ao wildcard
```

`? super Animal` é o inverso: aceita uma lista de `Animal` ou de qualquer **supertipo**, e permite **escrever** objetos `Animal` (ou subtipos) nela, mas não garante o tipo exato ao ler de volta.

```java
private static void printConsultaAnimal(List<? super Animal> animals) {
    animals.add(new Cachorro()); // ok, escrever é seguro
    animals.add(new Gato());
}
```

A regra mnemônica de mercado pra isso é **PECS** (*Producer Extends, Consumer Super*): use `extends` quando a lista só produz dados pra você (você só lê), use `super` quando a lista só consome o que você fornece (você só escreve).

## Type Erasure
Em tempo de execução, a JVM **não sabe** qual era o parâmetro de tipo genérico usado — essa informação existe só em tempo de compilação e é "apagada" depois (*type erasure*), por questão de compatibilidade com código Java anterior aos generics (que não existiam antes do Java 5). É por isso que não dá pra fazer `new T()` dentro de uma classe genérica, nem checar `instanceof List<String>` — na prática, na memória, existe só `List`, sem o `<String>`.
