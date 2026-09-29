# Sobrescrita do toString()

## Comportamento Padrão
Toda classe em Java herda de `Object`, que já implementa um `toString()` padrão. Sem sobrescrever, imprimir um objeto mostra algo pouco útil: o nome completo da classe seguido de `@` e o hash code em hexadecimal (ex: `Anime@1b6d3586`).

## Por que Sobrescrever
Sobrescrever `toString()` (com `@Override`) permite definir o que aparece quando o objeto é impresso com `System.out.println(objeto)` ou concatenado com uma String — sem precisar chamar nenhum método explícito, o Java chama `toString()` automaticamente nesses dois casos.

```java
@Override
public String toString(){
    return "Anime: " + this.nome;
}
```

```java
Anime anime = new Anime("Naruto");
System.out.println(anime); // imprime "Anime: Naruto", chamando toString() por baixo dos panos
```

## Uso Prático
`toString()` é útil sobretudo para depuração (debug) e logs — dá pra ver o estado do objeto de forma legível sem precisar imprimir atributo por atributo manualmente. É comum incluir todos os atributos relevantes no formato `Classe{atributo1=valor1, atributo2=valor2}`, que é o padrão gerado automaticamente por IDEs como o IntelliJ.
