# JDBC — CRUD em Camadas

Essa pasta reaplica o JDBC visto em `jdbc/`, mas numa estrutura mais próxima do que se vê em projeto real: separação em camadas e uso exclusivo de `PreparedStatement` (sem `Statement` cru, sem risco de SQL Injection). É a evolução direta da pasta anterior, não um assunto novo.

## Separação em Camadas: Repository e Service
`Repository` concentra o SQL e a comunicação direta com o banco (`Connection`, `PreparedStatement`, `ResultSet`). `Service` fica entre o repositório e quem usa (`Test01`, um controller, etc.), validando regras de negócio antes de delegar pro repositório — por exemplo, garantir que um `id` é válido antes de tentar deletar.

```java
public static void delete(Integer id) {
    requireValidId(id);            // regra de negócio, não é responsabilidade do repository
    ProducerRepository.delete(id);
}
```

Essa separação existe pra manter o SQL isolado numa camada só: se o banco mudar (de MySQL pra outro), só o `Repository` muda, o `Service` continua igual.

## PreparedStatement como Padrão
Diferente da pasta `jdbc/`, aqui **todo** acesso ao banco passa por `PreparedStatement`, mesmo pra `DELETE` e `SELECT` simples — elimina de vez a possibilidade de SQL Injection em qualquer ponto do CRUD.

```java
private static PreparedStatement createPrepareStatementDelete(Connection conn, Integer id) throws SQLException {
    String sql = "DELETE FROM anime_store.producer WHERE (id = ?);";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setInt(1, id);
    return ps;
}
```

## Optional no Retorno de Busca por Id
`findById` retorna `Optional<Producer>` em vez de `Producer` (que poderia ser `null`) ou de lançar exceção — deixa explícito, só pela assinatura, que o produtor pode não existir, e força quem chama a tratar os dois casos.

```java
public static Optional<Producer> findById(Integer id) {
    if (!rs.next()) return Optional.empty();
    return Optional.of(Producer.builder().id(rs.getInt("id")).name(rs.getString("name")).build());
}
```

## Lombok: Reduzindo Boilerplate
`@Value` gera automaticamente getters, `equals()`, `hashCode()` e `toString()` pra uma classe imutável (todos os campos ficam `private final`). `@Builder` gera um Builder completo (visto em Padrões de Projeto) sem precisar escrever a classe interna na mão. Junto, eliminam praticamente todo o código repetitivo de uma classe de dados simples.

```java
@Value
@Builder
public class Producer {
    Integer id;
    String name;
}
```

```java
Producer producer = Producer.builder().name("Studio Deen").build(); // Builder gerado pelo Lombok
```
