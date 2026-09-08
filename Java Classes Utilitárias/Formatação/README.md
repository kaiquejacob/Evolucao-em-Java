# Formatação e Internacionalização

## Locale
`Locale` representa uma região/idioma (ex: `pt-BR`, `en-US`) e é usado para adaptar automaticamente formatação de números, moedas e datas às convenções daquele lugar — sem `Locale`, o Java assume o padrão da JVM, que pode não ser o que o usuário espera.

```java
Locale brasil = new Locale("pt", "BR");
```

## Formatação de Números e Moeda
`NumberFormat` formata números de acordo com as convenções de um `Locale` — o mesmo valor aparece com separadores diferentes dependendo da região (`1.234,56` no Brasil x `1,234.56` nos EUA), e o formato de moeda já inclui o símbolo correto automaticamente.

```java
NumberFormat moeda = NumberFormat.getCurrencyInstance(brasil);
moeda.format(1500.50); // "R$ 1.500,50"
```

## Formatação de Datas: Legada x Moderna
`SimpleDateFormat` e `DateFormat` formatam objetos `Date` da API legada — mas `SimpleDateFormat` não é *thread-safe*, o que é uma armadilha comum em aplicações com múltiplas threads. `DateTimeFormatter` é o equivalente moderno, usado com as classes de `java.time` (`LocalDate`, `LocalDateTime`), imutável e seguro para uso concorrente.

```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
String formatada = LocalDate.now().format(formatter);
```

O padrão de letras (`dd`, `MM`, `yyyy`, `HH`, `mm`, `ss`) é o mesmo entre as duas APIs — a diferença está em qual classe de data cada formatter aceita.
