# Expressões Regulares (Regex)

## Pattern e Matcher
Uma expressão regular descreve um padrão de texto. `Pattern` compila essa expressão, e `Matcher` aplica o padrão compilado sobre uma String específica pra buscar, validar ou extrair trechos que casam com o padrão.

```java
Pattern pattern = Pattern.compile("[0-9]+");
Matcher matcher = pattern.matcher("Tenho 25 anos");
if (matcher.find()) {
    System.out.println(matcher.group()); // "25"
}
```

## Meta Caracteres e Ranges
Meta caracteres dão significado especial a símbolos dentro do padrão: `.` (qualquer caractere), `\d` (dígito), `\w` (letra/dígito/underline), `\s` (espaço em branco). Ranges (`[a-z]`, `[A-Z]`, `[0-9]`) definem um conjunto de caracteres aceitos numa posição.

```java
"[a-zA-Z]+".matches("Kaique"); // true, só letras
```

## Quantificadores
Quantificadores controlam **quantas vezes** o elemento anterior pode se repetir: `*` (zero ou mais), `+` (uma ou mais), `?` (zero ou uma), `{n}` (exatamente n vezes), `{n,m}` (entre n e m vezes).

```java
"\\d{3}-\\d{4}".matches("123-4567"); // true, formato exato de telefone
```

## Anchors
Anchors não casam com um caractere, mas com uma **posição** no texto: `^` marca o início da String (ou linha), `$` marca o fim. Usados pra garantir que o padrão bate com a String inteira, não só um pedaço dela no meio.

```java
"^[A-Z][a-z]+$".matches("Kaique"); // início com maiúscula, resto minúsculas, até o fim
```

## Scanner com Delimitadores Customizados
Além de ler tokens padrão (separados por espaço), `Scanner` aceita um delimitador customizado via `useDelimiter()`, útil pra ler entradas separadas por vírgula, ponto-e-vírgula, ou qualquer outro padrão regex.

```java
Scanner scanner = new Scanner("maçã,banana,uva");
scanner.useDelimiter(",");
while (scanner.hasNext()) {
    System.out.println(scanner.next());
}
```
