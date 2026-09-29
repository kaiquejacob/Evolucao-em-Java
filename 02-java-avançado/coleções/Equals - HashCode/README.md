# equals e hashCode

## Por que Sobrescrever equals()
Sem sobrescrever, o `equals()` herdado de `Object` compara **referência de memória** — igual ao `==`. Dois objetos com os mesmos dados, mas criados separadamente, são considerados diferentes.

```java
SmartPhone s1 = new SmartPhone("1ABC1", "iPhone");
SmartPhone s2 = new SmartPhone("1ABC1", "iPhone");
s1.equals(s2); // false com o equals padrão, mesmo com os mesmos dados
```

Sobrescrever `equals()` redefine o que significa "igual" pra aquela classe — geralmente comparando os atributos que identificam o objeto de forma única (no `SmartPhone`, o `serialNumber`; no `Manga`, `id` + `nome`).

```java
@Override
public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (this.getClass() != obj.getClass()) return false;
    SmartPhone smartPhone = (SmartPhone) obj;
    return serialNumber != null && serialNumber.equals(smartPhone.serialNumber);
}
```

## O Contrato do equals()
Um `equals()` bem implementado precisa respeitar 5 regras, e quebrar qualquer uma delas causa bugs difíceis de rastrear em coleções (List, Set, Map dependem desse contrato pra funcionar corretamente):

- **Reflexivo**: `x.equals(x)` sempre `true`.
- **Simétrico**: se `x.equals(y)` é `true`, `y.equals(x)` também precisa ser.
- **Transitivo**: se `x.equals(y)` e `y.equals(z)` são `true`, `x.equals(z)` também precisa ser.
- **Consistente**: chamadas repetidas retornam sempre o mesmo resultado, desde que os atributos comparados não mudem.
- **Nulidade**: `x.equals(null)` sempre `false`.

## Por que hashCode Anda Junto com equals()
A regra de ouro: **se dois objetos são iguais pelo equals(), o hashCode() deles precisa ser igual também.** O inverso não precisa ser verdade (dois objetos diferentes podem ter o mesmo hashCode — isso se chama colisão, e é normal). Estruturas como `HashSet` e `HashMap` usam o hashCode pra decidir em qual "balde" interno guardar o objeto antes mesmo de comparar com `equals()` — se você sobrescreve só o `equals()` e esquece o `hashCode()`, dois objetos "iguais" podem parar em baldes diferentes e a coleção vai tratá-los como itens distintos.

```java
@Override
public int hashCode() {
    return this.serialNumber.hashCode();
}
```

## Objects.equals e Objects.hash
A classe utilitária `Objects` evita ter que escrever verificação de `null` manualmente em cada comparação, e monta um hashCode combinando vários atributos de uma vez.

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Manga manga = (Manga) o;
    return Objects.equals(id, manga.id) && Objects.equals(nome, manga.nome);
}

@Override
public int hashCode() {
    return Objects.hash(id, nome); // combina os dois atributos num único hash
}
```
