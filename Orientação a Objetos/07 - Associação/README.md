# Associação entre Objetos

## O que é Associação
Associação é quando uma classe tem uma referência para outra classe como parte do seu relacionamento, sem que isso seja herança (`extends`). É a relação "tem um" (has-a), em vez de "é um" (is-a) da herança. Fica normalmente representada como um atributo do tipo da outra classe, ou um array desse tipo.

```java
public class Time {
    private Jogador[] jogadores; // Time "tem" jogadores
}
```

## Associação Unidirecional (um-para-muitos e muitos-para-um)
Unidirecional significa que só um lado da relação conhece o outro. Um-para-muitos: um `Time` conhece vários `Jogador` (array), mas o `Jogador` não sabe a qual time pertence. Muitos-para-um é o inverso: vários `Jogador` conhecem o mesmo `Professor`/`Time`, mas o lado "um" não tem essa lista.

```java
public class Escola {
    private Professor[] professores; // Escola conhece Professores (1 -> N)
}
// Professor não tem nenhuma referência de volta pra Escola
```

## Associação Bidirecional
Bidirecional é quando os dois lados se conhecem: o `Jogador` tem uma referência pro `Time`, e o `Time` tem uma lista de `Jogador`. É mais poderosa (dá pra navegar dos dois lados), mas também mais fácil de gerar inconsistência — se você atualiza um lado e esquece de atualizar o outro, os objetos ficam "desincronizados" logicamente.

```java
Jogador jogador = new Jogador("Cafu");
Time time = new Time("Brasil");

jogador.setTime(time);           // jogador -> time
time.setJogadores(new Jogador[]{jogador}); // time -> jogador
```
