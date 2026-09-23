# JDBC — Fundamentos

## Connection e DriverManager
`DriverManager.getConnection(url, usuario, senha)` abre uma conexão real com o banco. A URL JDBC segue o formato `jdbc:<driver>://<host>:<porta>/<database>` — é o ponto de partida de qualquer operação, e por isso quase todo método do repositório abre a conexão dentro de um try-with-resources, garantindo que ela feche mesmo se der erro.

```java
public static Connection getConnection() throws SQLException {
    String url = "jdbc:mysql://localhost:3307/anime_store";
    return DriverManager.getConnection(url, "root", "root");
}
```

## Statement: o Jeito Cru (e Arriscado)
`Statement` executa SQL como uma String pura, geralmente montada com concatenação ou `.formatted(...)`. Funciona, mas é vulnerável a **SQL Injection**: se `producer.getName()` vier de uma entrada de usuário contendo aspas ou `;`, dá pra alterar a query inteira. Além disso, cada execução recompila o SQL do zero no banco.

```java
String sql = "INSERT INTO anime_store.producer (name) VALUES ('%s')".formatted(producer.getName());
stmt.executeUpdate(sql); // se producer.getName() vier de input externo, é injetável
```

## PreparedStatement: Parâmetros Seguros
`PreparedStatement` separa o SQL (com `?` no lugar dos valores) dos parâmetros de fato, que são passados por métodos tipados (`setString`, `setInt`) — o driver cuida do escaping, eliminando o risco de injection. Também permite ao banco **pré-compilar** a query uma vez e reexecutar só trocando os parâmetros, mais eficiente em loops.

```java
String sql = "UPDATE anime_store.producer SET name = ? WHERE (id = ?);";
PreparedStatement ps = conn.prepareStatement(sql);
ps.setString(1, producer.getName());
ps.setInt(2, producer.getId());
```

## ResultSet: Percorrendo o Resultado de um SELECT
Um `SELECT` retorna um `ResultSet` — um cursor que começa **antes** da primeira linha. `next()` avança uma posição e retorna `false` quando não há mais linhas, por isso o padrão é sempre `while (rs.next())`. Os valores de cada coluna são lidos por nome ou índice, com o getter do tipo certo (`getInt`, `getString`).

```java
while (rs.next()) {
    Producer producer = Producer.builder()
            .id(rs.getInt("id"))
            .name(rs.getString("name"))
            .build();
}
```

## ResultSetMetaData e DatabaseMetaData
`ResultSetMetaData` descreve a **estrutura do resultado** de uma query específica (quantas colunas, nome, tipo, tamanho) — útil pra código genérico que não sabe de antemão o formato da tabela. `DatabaseMetaData` descreve capacidades do **banco/driver** em si, como quais tipos de `ResultSet` são suportados.

```java
ResultSetMetaData rsMetaData = rs.getMetaData();
for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
    rsMetaData.getColumnName(i);
    rsMetaData.getColumnTypeName(i);
}
```

## ResultSet Scrollable e Atualizável
Por padrão, um `ResultSet` só anda pra frente (`TYPE_FORWARD_ONLY`) e é somente leitura. Passando `TYPE_SCROLL_INSENSITIVE` + `CONCUR_UPDATABLE` na criação do `Statement`, o `ResultSet` ganha navegação livre (`first()`, `last()`, `absolute(n)`, `previous()`) e a capacidade de alterar dados **direto nele**, sem escrever um `UPDATE` separado — `updateRow()`, `insertRow()` (via `moveToInsertRow()`), `deleteRow()` refletem a mudança direto na tabela.

```java
Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
rs.updateString("name", rs.getString("name").toUpperCase());
rs.updateRow(); // grava a mudança no banco
```

`INSENSITIVE` significa que o `ResultSet` não reflete mudanças feitas por **outras** conexões depois que ele foi carregado — é uma foto do momento da query, não uma view ao vivo.

## CallableStatement: Chamando Stored Procedures
`CallableStatement` executa uma stored procedure já existente no banco (`CALL nome_procedure(?)`), em vez de rodar SQL direto — a lógica da consulta fica centralizada no banco, e a aplicação só passa os parâmetros.

```java
CallableStatement cs = conn.prepareCall("CALL `anime_store`.`sp_get_producer_by_name`(?);");
cs.setString(1, "%" + name + "%");
```

## Transação: commit e rollback
Por padrão, cada `executeUpdate()` já é automaticamente confirmado no banco (`autoCommit = true`). Desligando isso (`setAutoCommit(false)`), várias operações passam a fazer parte de uma **única transação**: só ficam definitivas com `commit()`, e podem ser completamente desfeitas com `rollback()` se algo der errado no meio — essencial quando várias operações precisam ter tudo-ou-nada como garantia (ex: salvar 3 produtores, e se um falhar, desfazer os outros dois também).

```java
conn.setAutoCommit(false);
try {
    // várias operações...
    conn.commit();
} catch (SQLException e) {
    conn.rollback(); // desfaz tudo que não foi commitado ainda
}
```

## RowSet: JdbcRowSet e CachedRowSet
`RowSet` é uma alternativa de mais alto nível ao `ResultSet` tradicional. `JdbcRowSet` mantém a conexão **aberta** o tempo todo (conectado) — mudanças nele refletem no banco quase como um `ResultSet` atualizável, mas com API mais simples de configurar (`setUrl`, `setUsername`, `setCommand`). `CachedRowSet` é **desconectado**: carrega os dados pra memória, fecha a conexão, permite trabalhar offline, e só reabre a conexão quando chama `acceptChanges()` pra sincronizar as alterações de volta.

```java
try (CachedRowSet crs = ConnectionFactory.getCachedRowSet();
     Connection connection = ConnectionFactory.getConnection()) {
    crs.setCommand("SELECT * FROM producer WHERE (id = ?);");
    crs.setInt(1, producer.getId());
    crs.execute(connection);
    crs.next();
    crs.updateString("name", producer.getName());
    crs.updateRow();
    crs.acceptChanges(); // só aqui reconecta e grava de fato no banco
}
```
