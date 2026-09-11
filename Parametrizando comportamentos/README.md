# Parametrizando Comportamentos

## O Problema: um Método pra Cada Filtro
O ponto de partida clássico: pra cada critério de filtro diferente (carro verde, carro vermelho, carro antes de determinado ano), você escreve um método inteiro, quase idêntico ao anterior, só mudando a condição do `if` lá dentro.

```java
private static List<Car> filterGreenCar(List<Car> cars) {
    List<Car> greenCar = new ArrayList<>();
    for (Car car : cars) {
        if (car.getColor().equals("green")) {
            greenCar.add(car);
        }
    }
    return greenCar;
}
// filterRedCar, filterByYearBefore... mesma estrutura, só a condição muda
```

O objetivo desse tópico é eliminar essa duplicação passando o **comportamento** (a condição de filtro) como parâmetro do método, em vez de fixá-lo dentro de cada método separado.

## Passo 1: Interface Funcional Própria
Uma interface funcional é uma interface com **exatamente um** método abstrato — isso é o que permite, mais adiante, substituir a implementação dela por uma lambda. A anotação `@FunctionalInterface` não é obrigatória, mas faz o compilador acusar erro se você acidentalmente adicionar um segundo método abstrato, quebrando o contrato.

```java
@FunctionalInterface
public interface CarPredicate {
    boolean test(Car car);
}
```

Com essa interface, o filtro vira um único método genérico, que recebe a lógica de comparação como argumento:

```java
private static List<Car> filter(List<Car> cars, CarPredicate carPredicate) {
    List<Car> filterCar = new ArrayList<>();
    for (Car car : cars) {
        if (carPredicate.test(car)) {
            filterCar.add(car);
        }
    }
    return filterCar;
}
```

## Passo 2: Trocando Classe Anônima por Lambda
Antes de existir lambda, a forma de implementar uma interface funcional na hora era com classe anônima — funcional, mas verboso pra algo tão simples quanto uma condição de uma linha.

```java
List<Car> greenCars = filter(cars, new CarPredicate() {
    @Override
    public boolean test(Car car) {
        return car.getColor().equals("green");
    }
});
```

Como `CarPredicate` só tem um método abstrato, o compilador consegue inferir tudo isso a partir de uma lambda muito mais enxuta:

```java
List<Car> greenCar = filter(cars, car -> car.getColor().equals("green"));
```

## Passo 3: Usando Predicate em vez de Interface Própria
Criar uma interface funcional pra cada caso de uso específico (`CarPredicate`) funciona, mas o Java já tem uma interface funcional genérica pronta pra esse padrão exato — testar uma condição sobre um objeto e devolver `boolean`: `Predicate<T>`, no pacote `java.util.function`. Não há necessidade de declarar sua própria interface quando o caso já é coberto por uma das interfaces funcionais padrão.

```java
private static List<Car> filter(List<Car> cars, Predicate<Car> carPredicate) {
    List<Car> filterCar = new ArrayList<>();
    for (Car car : cars) {
        if (carPredicate.test(car)) {
            filterCar.add(car);
        }
    }
    return filterCar;
}
```

## Passo 4: Generalizando com Generics
O último passo remove a última amarra: o método `filter` ainda só funciona com `Car`. Combinando `Predicate<T>` com um parâmetro de tipo genérico no próprio método, o mesmo `filter` passa a funcionar pra **qualquer** tipo de lista, não só carros.

```java
private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
    List<T> filteredList = new ArrayList<>();
    for (T e : list) {
        if (predicate.test(e)) {
            filteredList.add(e);
        }
    }
    return filteredList;
}
```

```java
filter(cars, car -> car.getColor().equals("green")); // funciona com Car
filter(nums, num -> num % 2 == 0);                    // e também com Integer, sem duplicar o método
```

Esse é o núcleo do que Streams (mais à frente no curso) usa por baixo dos panos: métodos genéricos que recebem comportamento como parâmetro, em vez de ter uma versão fixa pra cada caso.
