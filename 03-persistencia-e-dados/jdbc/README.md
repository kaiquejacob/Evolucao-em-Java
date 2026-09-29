# Persistência de Dados com JDBC

## Conexão e Drivers
O **JDBC (Java Database Connectivity)** é a API padrão do Java para comunicação direta com bancos de dados relacionais. A conexão é estabelecida via `DriverManager` ou `DataSource`, fornecendo a URL do banco (ex: `jdbc:mysql://localhost:3306/db`), usuário e senha.

A boa prática exige isolar a criação de conexões em uma classe utilitária (`ConnectionFactory`). Como o estabelecimento de conexões de rede é uma operação cara, em produção utiliza-se um **Connection Pool** (como HikariCP) para reaproveitar conexões abertas em vez de abrir e fechar uma a cada query.

```java
public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/anime_store";
        String username = "root";
        String password = "rootpassword";
        return DriverManager.getConnection(url, username, password);
    }
}

```

## Statement vs PreparedStatement

O `Statement` executa consultas concatenando a instrução SQL diretamente como String. Ele **nunca** deve ser usado para concatenar parâmetros informados pelo usuário, pois gera a vulnerabilidade de **SQL Injection**.

O `PreparedStatement` pré-compila a instrução SQL no banco de dados e substitui os parâmetros representados pelo caractere `?`. Ele é mais seguro, previne SQL Injection e possui melhor performance para execuções repetitivas da mesma query.

```java
// VULNERÁVEL A SQL INJECTION (Não use):
// String sql = "SELECT * FROM producer WHERE name = '" + name + "'";

// FORMA CORRETA E SEGURA:
String sql = "SELECT * FROM producer WHERE name = ?";
try (Connection conn = ConnectionFactory.getConnection();
     PreparedStatement ps = conn.prepareStatement(sql)) {
    ps.setString(1, name);
    try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            // processa resultados
        }
    }
}

```

## Manipulação de Resultados com ResultSet

O `ResultSet` representa a tabela de dados retornada pelo banco, mantendo um cursor apontado antes da primeira linha.

A pegadinha principal é que **os índices das colunas no JDBC começam em 1**, e não em 0. O método `rs.next()` avança o cursor para a próxima linha e retorna `false` quando não houver mais registros.

```java
while (rs.next()) {
    int id = rs.getInt("id");             // busca pelo nome da coluna
    String name = rs.getString(2);        // busca pelo índice (1-based)
}

```

## Gestão de Recursos (Gerenciamento de Memória)

Recursos do JDBC (`Connection`, `PreparedStatement`, `ResultSet`) consomem conexões de rede e descritores do sistema operacional. O não fechamento explícito desses objetos resulta em **vazamento de conexões (connection leaks)**, podendo travar a aplicação e esgotar os recursos do banco.

Utilize sempre a instrução **`try-with-resources`** para garantir o fechamento automático de todos os objetos que implementam `AutoCloseable`, executado na ordem inversa da criação.

## Transações (ACID)

Por padrão, o JDBC opera em modo **Auto-Commit** (`conn.getAutoCommit() == true`), onde cada instrução SQL executed é efetivada imediatamente no banco.

Para garantir atomicidade em operações compostas (onde várias tabelas precisam ser alteradas juntas), desativa-se o auto-commit no início do bloco de código e chama-se o `commit()` ao final ou `rollback()` no bloco `catch`:

```java
try (Connection conn = ConnectionFactory.getConnection()) {
    conn.setAutoCommit(false); // Inicia a transação manual

    try (PreparedStatement ps1 = conn.prepareStatement(sql1);
         PreparedStatement ps2 = conn.prepareStatement(sql2)) {
        ps1.executeUpdate();
        ps2.executeUpdate();
        
        conn.commit(); // Efetiva as duas operações
    } catch (SQLException e) {
        conn.rollback(); // Reverte alterações caso ocorra erro
        throw e;
    }
}

```

## Testes Automatizados no Módulo JDBC

Os testes deste projeto de persistência estão estruturados dentro do caminho padrão do Maven/Gradle: `src/test/java/jUnit/`.

Em camadas de acesso a dados com JDBC puro, os testes cobrem duas vertentes:

1. **Testes Unitários de Regra de Negócio (Services):** Validam regras isoladas sem depender da conexão real com o banco de dados, utilizando **Mockito** para simular as respostas dos Repositories ou Services.
2. **Testes de Integração com Banco:** Testam se as queries SQL e os mapeamentos do `ResultSet` funcionam corretamente contra um banco de testes isolado (como um container Docker ou banco em memória H2).

```java
// Localização: src/test/java/jUnit/PersonServiceTest.java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonService personService;

    @Test
    void deveValidarSePessoaEHMaiorDeIdade() {
        Person person = new Person(18);
        
        when(personService.isAdult(person)).thenReturn(true);
        
        assertTrue(personService.isAdult(person));
        verify(personService, times(1)).isAdult(person);
    }
}

```
