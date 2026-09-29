# Padrões de Projeto (Design Patterns - GoF)

## Padrões Criacionais

### Singleton (`singleton/`)
Garante que uma classe tenha apenas uma única instância durante todo o ciclo de vida da aplicação e fornece um ponto global de acesso a ela.

* **Lazy Initialization:** Instância criada apenas no primeiro acesso.
* **Eager Initialization:** Instância criada estaticamente no carregamento da classe pela JVM.
* **Lazy Holder:** Solução thread-safe performática sem necessidade de sincronização explícita (`synchronized`).
* **Enum Singleton:** Abordagem imune a ataques de reflexão e problemas de serialização.

```java
public class SingletonLazyHolder {
    private static class InstanceHolder {
        public static final SingletonLazyHolder INSTANCE = new SingletonLazyHolder();
    }
    private SingletonLazyHolder() {}
    public static SingletonLazyHolder getInstance() {
        return InstanceHolder.INSTANCE;
    }
}

```

### Builder (`builder/`)

Separa a construção de um objeto complexo da sua representação. Permite criar instâncias passo a passo sem poluir a classe com múltiplos construtores sobrecarregados (*telescoping constructors*).

### Factory Method (`factory/`)

Define uma interface ou classe abstrata para criar um objeto, delegando às subclasses ou métodos utilitários a decisão de qual classe concreta instanciar.

---

## Padrões Comportamentais

### Strategy (`strategy/`)

Define uma família de algoritmos, encapsula cada um deles e os torna intercambiáveis em tempo de execução. Permite variar a regra de negócio sem alterar o cliente que a utiliza (princípio Open/Closed).

```java
public interface Comportamento {
    void mover();
}

public class ComportamentoNormal implements Comportamento {
    public void mover() { System.out.println("Movendo-se normalmente..."); }
}

public class Robo {
    private Comportamento comportamento;

    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    public void mover() {
        comportamento.mover();
    }
}

```

---

## Padrões Estruturais

### Facade (`facade/`)

Oferece uma interface unificada e simplificada para um conjunto de interfaces complexas em um subsistema. Reduz o acoplamento entre o cliente e os detalhes internos de múltiplas APIs (como serviços de CRM e consulta de CEP).

```java
public class Facade {
    public void migrarCliente(String nome, String cep) {
        String cidade = CepApi.getInstancia().recuperarCidade(cep);
        String estado = CepApi.getInstancia().recuperarEstado(cep);
        
        CrmService.gravarCliente(nome, cep, cidade, estado);
    }
}

```

---

## Objetos de Transferência

### Data Transfer Object (`dto/`)

Utilizado para agrupar e transportar dados entre diferentes camadas do sistema (como entre banco de dados, visão ou serviços de relatórios), reduzindo a quantidade de chamadas e protegendo a entidade de domínio.

---

## Testes Automatizados no Módulo

Os testes automatizados dos padrões implementados ficam situados no caminho `src/test/java/` (ou em classes com o sufixo `Test` dentro das subpastas do módulo).

A cobertura de testes para Design Patterns foca em:

1. **Validação de Referência do Singleton:** Confirmação de que chamadas consecutivas retornam o mesmo objeto na memória (`assertSame`).
2. **Troca Dinâmica do Strategy:** Verificação de que mudar o comportamento do contexto altera a execução esperada.
3. **Comportamento do Builder e Factory:** Validação de que a construção de objetos complexos e a criação via fábrica mantêm os atributos corretos.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DesignPatternsTest {

    @Test
    void deveGarantirInstanciaUnicaDoSingleton() {
        SingletonLazyHolder s1 = SingletonLazyHolder.getInstance();
        SingletonLazyHolder s2 = SingletonLazyHolder.getInstance();

        assertSame(s1, s2);
    }

    @Test
    void deveAlternarComportamentoDoRoboViaStrategy() {
        Robo robo = new Robo();
        Comportamento normal = new ComportamentoNormal();
        Comportamento agressivo = new ComportamentoAgressivo();

        robo.setComportamento(normal);
        assertDoesNotThrow(robo::mover);

        robo.setComportamento(agressivo);
        assertDoesNotThrow(robo::mover);
    }
}

```
