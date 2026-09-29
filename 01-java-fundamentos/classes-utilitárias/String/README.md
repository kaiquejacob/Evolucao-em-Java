# String

## Imutabilidade e String Pool
`String` em Java é **imutável** — depois de criada, o conteúdo de uma String nunca muda. Métodos como `concat()` ou `replace()` não alteram a String original, eles retornam uma **nova** String com o resultado. Além disso, literais de String ficam guardados num espaço especial de memória chamado *string pool*: duas variáveis com o mesmo literal apontam pro mesmo objeto na pool, em vez de criar duas cópias.

```java
String nome = "William";
nome.concat(" Suane");      // não altera nome, o retorno é descartado aqui
System.out.println(nome);   // ainda imprime "William"

nome = nome.concat(" Suane"); // agora sim, nome aponta pra uma NOVA String
```

## == x .equals() em String
`new String("William")` força a criação de um objeto novo fora da pool, mesmo que o conteúdo seja igual a um literal já existente. Por isso `==` (que compara referência de memória) pode dar `false` mesmo quando o conteúdo é idêntico — o certo pra comparar conteúdo de String é sempre `.equals()`.

```java
String nome2 = "William";
String nome3 = new String("William");
System.out.println(nome2 == nome3);        // false, objetos diferentes na memória
System.out.println(nome2.equals(nome3));   // true, mesmo conteúdo
```

## Performance: String x StringBuilder x StringBuffer
Como String é imutável, cada concatenação (`+=`) dentro de um loop cria uma String nova e descarta a anterior — isso fica extremamente caro em loops grandes. `StringBuilder` resolve isso mantendo um buffer mutável internamente, evitando recriar o objeto a cada concatenação.

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 30000; i++) {
    sb.append(i); // modifica o mesmo objeto, não cria um novo a cada volta
}
```

`StringBuffer` faz a mesma coisa que `StringBuilder`, mas é *thread-safe* (métodos sincronizados) — por isso é um pouco mais lento. Em código single-thread (a maioria dos casos), `StringBuilder` é a escolha padrão.

## Métodos Comuns de String
Alguns dos métodos mais usados no dia a dia: `charAt(i)` pega um caractere pela posição, `length()` retorna o tamanho, `substring(inicio, fim)` extrai um trecho, `trim()` remove espaços em branco das pontas, `toUpperCase()`/`toLowerCase()` mudam a caixa.

```java
String texto = "  Sandro  ";
texto.trim();              // "Sandro"
texto.substring(2, 8);     // extrai um trecho pelo índice
```
