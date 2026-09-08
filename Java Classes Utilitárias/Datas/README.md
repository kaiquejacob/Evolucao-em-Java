# Datas e Horários

## A API Legada: Date e Calendar
`Date` e `Calendar` são as classes originais do Java para lidar com datas, mas têm problemas conhecidos: `Date` é mutável (perigoso quando compartilhado entre partes do código), os meses em `Calendar` são indexados a partir de **0** (Janeiro = 0, não 1 — fonte clássica de bugs), e a API como um todo é considerada confusa e mal projetada. Elas foram mantidas só por compatibilidade com código antigo; código novo deve usar `java.time`.

## A API Moderna: java.time
Introduzida para resolver os problemas da API legada, com classes imutáveis e nomes mais diretos: `LocalDate` (só a data, sem hora), `LocalTime` (só a hora, sem data), `LocalDateTime` (os dois juntos), todos sem fuso horário.

```java
LocalDate hoje = LocalDate.now();
LocalDate especifica = LocalDate.of(2026, 9, 7); // ano, mês, dia — mês já é 9, sem index 0
```

## Instant
`Instant` representa um ponto exato no tempo, independente de fuso horário — é o "timestamp" da máquina, medido a partir da *epoch* (1º de janeiro de 1970 UTC). Usado quando o que importa é o instante absoluto, não como ele aparece no calendário de uma região.

```java
Instant agora = Instant.now();
```

## Duration x Period
Ambos representam uma quantidade de tempo, mas em escalas diferentes. `Duration` mede tempo em unidades pequenas e exatas (horas, minutos, segundos, nanossegundos) — bom para medir intervalos técnicos. `Period` mede em unidades de calendário (anos, meses, dias) — bom para diferenças "humanas", tipo idade ou tempo de contrato.

```java
Duration duracao = Duration.ofMinutes(90);
Period periodo = Period.of(1, 2, 15); // 1 ano, 2 meses, 15 dias
```

## ChronoUnit e TemporalAdjusters
`ChronoUnit` calcula a diferença entre duas datas numa unidade específica (dias, meses, anos), sem precisar montar manualmente um `Period`/`Duration`. `TemporalAdjusters` fornece ajustes prontos pra datas, como "primeiro dia do mês" ou "próxima segunda-feira", sem ter que calcular isso na mão.

```java
long dias = ChronoUnit.DAYS.between(dataInicial, dataFinal);
LocalDate proximaSegunda = data.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
```

## ZonedDateTime e Fuso Horário
`ZonedDateTime` acrescenta informação de fuso horário a um `LocalDateTime`, necessário quando o sistema lida com usuários em regiões diferentes — a mesma hora local significa instantes absolutos diferentes dependendo do fuso.

```java
ZonedDateTime zoned = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
```
