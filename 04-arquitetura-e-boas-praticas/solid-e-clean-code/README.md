# Princípios SOLID e Clean Code

## Single Responsibility Principle (SRP)
"Uma classe deve ter um, e apenas um, motivo para mudar."

O princípio da Responsabilidade Única prega a alta coesão. Uma classe não deve acumular regras de negócio, acesso a dados e formatadores de saída no mesmo lugar. Se uma alteração no cálculo de estoque e uma mudança no banco exigem alterar a mesma classe, o SRP foi violado.

```java
// VIOLAÇÃO: A regra de cálculo e a persistência estão na mesma classe
public class BasicBasketService {
    public void processarCesta(BasicBasket basket) {
        // calcula regra
        // grava direto no banco
    }
}

// CORRETO: Separação de responsabilidades em camadas distintas
public class BasicBasketService { 
    private final BasicBasketDAO basketDAO; // Apenas coordena a regra de negócio
}
public class BasicBasketDAO { /* Apenas acesso a dados */ }

```

## Open/Closed Principle (OCP)

"Entidades de software devem estar abertas para extensão, mas fechadas para modificação."

O sistema deve permitir a inclusão de novos comportamentos sem a necessidade de modificar o código-fonte já testado. Isso é garantido por meio do uso de **interfaces** e polimorfismo.

```java
public interface MoneyDAO {
    BigDecimal buscarSaldo();
}

// Novos provedores ou origens de saldo são estendidos criando novas implementações,
// sem alterar as regras existentes do MoneyService.
public class DatabaseMoneyDAO implements MoneyDAO { ... }
public class ApiMoneyDAO implements MoneyDAO { ... }

```

## Liskov Substitution Principle (LSP)

"Subtipos devem ser substituíveis por seus tipos base sem alterar a corretude do programa."

Se a classe `B` herda de `A`, o sistema deve conseguir utilizar instâncias de `B` no lugar de `A` sem que ocorram falhas ou comportamentos inesperados (como lançar `UnsupportedOperationException` em métodos herdados).

A pegadinha clássica é forçar herança entre classes apenas para reaproveitar código, quando o correto seria aplicar composição.

## Interface Segregation Principle (ISP)

"Clientes não devem ser forçados a depender de interfaces que não utilizam."

É preferível criar interfaces pequenas e focadas a criar interfaces extensas com múltiplos métodos não correlacionados. Classes concretas não devem ser obrigadas a implementar métodos vazios apenas para cumprir contratos genéricos.

```java
// VIOLAÇÃO: Interface genérica forçando métodos desnecessários
public interface Processador {
    void processarCesta();
    void processarMoeda();
}

// CORRETO: Interfaces segregadas por contexto de uso
public interface BasicBasketProcessor { void processarCesta(); }
public interface MoneyProcessor { void processarMoeda(); }

```

## Dependency Inversion Principle (DIP)

"Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações."

Classes de serviço (`BasicBasketService`, `MoneyService`) não devem instanciar diretamente suas dependências de infraestrutura (como `BasicBasketDAO` concreto). A injeção de dependências via construtor desacopla a regra e habilita a substituição das dependências por mocks em testes unitários.

```java
public class MoneyService {
    private final MoneyDAO moneyDAO; // Depende da interface (abstração)

    public MoneyService(MoneyDAO moneyDAO) {
        this.moneyDAO = moneyDAO;
    }
}

```

## Práticas de Clean Code

* **Nomes Reveladores de Intenção:** Variáveis e métodos devem expressar o que fazem sem necessidade de comentários redundantes (`BasicBasketService` em vez de `BasketManagerImpl`).
* **Funções Pequenas e Coesas:** Métodos devem realizar apenas uma tarefa e manter um nível único de abstração.
* **Tratamento Adequado de Exceções:** Evite capturar `Exception` genérica e nunca deixe blocos `catch` vazios sem o devido tratamento ou log.

## Testes Automatizados no Módulo

Os testes automatizados deste projeto estão localizados no diretório padrão `src/test/java/br/com/dio/`.

O cumprimento do DIP possibilita que as regras do `MoneyService` e `BasicBasketService` sejam testadas de forma isolada e rápida, sem depender de conexões com banco de dados real.

```java
// Localização: src/test/java/br/com/dio/service/MoneyServiceTest.java
package [br.com/dio/service](https://br.com/dio/service);

import br.com.dio.dao.MoneyDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoneyServiceTest {

    @Mock
    private MoneyDAO moneyDAO;

    @InjectMocks
    private MoneyService moneyService;

    @Test
    void deveRetornarSaldoCorretoDoProvedor() {
        when(moneyDAO.buscarSaldo()).thenReturn(new BigDecimal("100.00"));

        BigDecimal saldo = moneyService.obterSaldo();

        assertEquals(new BigDecimal("100.00"), saldo);
    }
}

```
