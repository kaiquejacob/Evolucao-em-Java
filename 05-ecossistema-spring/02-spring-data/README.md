# Spring Data e Persistência Poliglota - Marketplace

## Arquitetura de Persistência Poliglota
Este módulo demonstra o uso do ecossistema **Spring Data** para conectar uma única aplicação a múltiplos mecanismos de armazenamento, selecionando a tecnologia ideal para cada contexto de negócio:

* **PostgreSQL (Spring Data JPA):** Utilizado para dados estruturados e transacionais que exigem consistência forte (ACID), como entidades de clientes (`Customer`), eventos (`Event`) e pedidos.
* **MongoDB (Spring Data MongoDB):** Utilizado no subdomínio de catálogo para armazenar documentos com esquema flexível (`EventMetadata`), permitindo variação de atributos dinâmicos sem alterar a estrutura de tabelas relacionais.
* **Redis (Spring Data Redis):** Aplicado no contexto de venda de ingressos (`ticketing/`) para gerenciar trava temporária de assentos (`RedisSeatLockRepository`) com alto nível de concorrência e tempo de expiração automático (TTL).

## Desacoplamento do Domínio via Adapters
Para manter a Clean Architecture, as entidades de domínio e os casos de uso não interagem com anotações de persistência (`@Entity` ou `@Document`). 

A infraestrutura mapeia as entidades de domínio para entidades de persistência dedicadas e implementa as interfaces do repositório de domínio:

```java
// Contrato no Domínio (Livre de tecnologia)
public interface EventRepository {
    Optional<Event> findById(EventId id);
    void save(Event event);
}

// Implementação na Infraestrutura (Spring Data JPA Adapter)
@Repository
public class JpaEventRepository implements EventRepository {

    private final EventEntityRepository entityRepository;

    public JpaEventRepository(EventEntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Override
    public Optional<Event> findById(EventId id) {
        return entityRepository.findById(id.value()).map(EventEntity::toDomain);
    }

    @Override
    public void save(Event event) {
        entityRepository.save(EventEntity.from(event));
    }
}

```

## Projeções (Spring Data Projections)

Para otimizar consultas de leitura na camada de registro sem recarregar o objeto completo do banco, é utilizada a interface de projeção `CustomerExcerpt`:

```java
public interface CustomerExcerpt {
    String getId();
    String getName();
    String getEmail();
}

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, String> {
    List<CustomerExcerpt> findByActiveTrue(); // Traz apenas colunas mapeadas no contrato leve
}

```

## Testes Automatizados no Módulo

Os testes automatizados estão localizados no diretório `src/test/java/dio/marketplace/`.

A estratégia de testes divide-se em:

1. **Testes Unitários de Casos de Uso (`application/`):** Validam as regras de orquestração isolando a camada de dados com mocks.
2. **Testes de Repositórios e Persistência (`infrastructure/persistence/`):** Validam a integração dos repositórios Spring Data contra instâncias de testes e containers para verificar mapeamentos, locks no Redis e queries customizadas.

```java
// Localização: src/test/java/dio/marketplace/MarketplaceApplicationTests.java
@SpringBootTest
class MarketplaceApplicationTests {

    @Test
    void contextLoads() {
        // Valida se o contexto da aplicação, mapeamentos JPA e Beans sobem sem falhas
    }
}

```

