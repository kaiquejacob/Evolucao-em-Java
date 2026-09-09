# List

## O que é List
`List` é uma coleção **ordenada** (mantém a ordem de inserção) que **permite duplicados** e permite acesso direto por índice — as duas características que a diferenciam de `Set`. `ArrayList` é a implementação mais comum, apoiada internamente num array que cresce automaticamente.

```java
List<String> nomes = new ArrayList<>();
nomes.add("William");
nomes.add("DevDojo");
nomes.get(0);       // acesso direto por índice, coisa que Set não tem
nomes.size();
```

## Ordenação: Collections.sort() e Comparable
`Collections.sort(lista)` ordena uma lista usando a **ordem natural** dos elementos. Pra tipos como `String` e wrappers numéricos isso já vem pronto — pra classes próprias (como `Manga`), a classe precisa implementar `Comparable<T>` e definir o método `compareTo()`, que diz como comparar dois objetos dela.

```java
public class Manga implements Comparable<Manga> {
    @Override
    public int compareTo(Manga outroManga) {
        return this.nome.compareTo(outroManga.getNome()); // ordem natural: por nome
    }
}
```

## Ordenação Customizada: Comparator
Quando você precisa de uma ordem **diferente** da natural (ou a classe não implementa `Comparable`), usa-se um `Comparator` externo — uma classe separada que define sua própria regra de comparação, passada como argumento pro sort.

```java
class MangaByIdComparator implements Comparator<Manga> {
    @Override
    public int compare(Manga manga1, Manga manga2) {
        return manga1.getId().compareTo(manga2.getId());
    }
}
```

```java
mangas.sort(mangaByIdComparator); // ordena por id, mesmo o compareTo() natural sendo por nome
```

## Busca Binária (Collections.binarySearch)
Busca binária é **muito mais rápida** que percorrer a lista item por item, mas só funciona corretamente se a lista **já estiver ordenada** — e ordenada com o **mesmo critério** usado na busca. Se você ordenou por `MangaByIdComparator`, a busca precisa usar o mesmo comparator, senão o resultado é indefinido.

```java
mangas.sort(mangaByIdComparator);
Collections.binarySearch(mangas, mangaToSearch, mangaByIdComparator); // mesmo comparator da ordenação
```

Se o elemento não existe na lista, o retorno é um número negativo que codifica onde ele **deveria** ser inserido (não é simplesmente `-1`).

## Conversão entre List e Array
`toArray()` converte uma List pra array. `Arrays.asList()` faz o caminho inverso, mas com uma pegadinha importante: a lista retornada é uma **view de tamanho fixo** sobre o array original — dá pra alterar elementos existentes (`set()`), mas **não** dá pra adicionar ou remover (`add()`/`remove()` lançam exceção). Pra ter uma lista de verdade, mutável, é preciso envolver o resultado num `new ArrayList<>(...)`.

```java
List<Integer> arrayToList = Arrays.asList(numerosArray); // view fixa sobre o array
arrayToList.set(0, 12); // ok, altera o array original também

List<Integer> listaMutavel = new ArrayList<>(Arrays.asList(numerosArray));
listaMutavel.add(15); // ok, essa é uma lista de verdade
```

## Iterator e Remoção Segura
Remover um elemento de uma lista **enquanto** percorre ela com foreach lança `ConcurrentModificationException` — o foreach usa um Iterator por baixo dos panos, e modificar a lista fora desse iterator quebra o controle interno dele. O jeito seguro é usar o `Iterator` diretamente e chamar `iterator.remove()`.

```java
Iterator<Manga> it = mangas.iterator();
while (it.hasNext()) {
    if (it.next().getQuantidade() == 0) {
        it.remove(); // remoção segura durante a iteração
    }
}
```

Desde versões mais recentes do Java, `removeIf()` faz a mesma coisa de forma mais direta, sem precisar lidar com Iterator manualmente:

```java
mangas.removeIf(manga -> manga.getQuantidade() == 0);
```
