# Spring Cloud OpenFeign e Integrações HTTP - Compliance Application

## Clientes HTTP Declarativos com OpenFeign
Este módulo aborda a comunicação HTTP resiliente entre microsserviços e APIs externas no contexto de conformidade e análise de risco (`ComplianceApplication`).

A abstração do **Spring Cloud OpenFeign** substitui chamadas manuais via `RestTemplate` ou `WebClient` por interfaces declarativas anotadas com `@FeignClient`. A implementação do cliente, serialização/deserialização de objetos JSON e tratamento de URLs são geridos dinamicamente pelo framework.

* **`AntiMoneyLaunderingClient`:** Interface de consulta a serviços externos de prevenção à lavagem de dinheiro (AML/PEP).
* **`SanctionClient`:** Cliente de verificação de sanções com suporte a mecanismo de tolerância a falhas (*fallback*).

```java
@FeignClient(name = "sanction-client", url = "${integration.sanction.url}", fallback = SanctionClient.Fallback.class)
public interface SanctionClient {

    @GetMapping("/sanctions/{companyId}")
    SanctionResult checkSanctions(@PathVariable("companyId") String companyId);

    @Component
    class Fallback implements SanctionClient {
        @Override
        public SanctionResult checkSanctions(String companyId) {
            // Retorna um resultado padronizado em caso de timeout ou indisponibilidade do serviço remoto
            return SanctionResult.empty(companyId);
        }
    }
}

```

## Análise de Risco no Domínio

O caso de uso `AnalyzeCompanyRiskUseCase` orquestra as consultas HTTP declarativas e consolida as respostas na entidade de domínio `RiskAssessment`:

1. Executa triagens simultâneas nas APIs de sanções e lavagem de dinheiro (`ComplianceScreening`).
2. Avalia as políticas de conformidade da empresa (`CompliancePolicy`).
3. Classifica o nível de risco da organização (`RiskLevel`: `LOW`, `MEDIUM`, `HIGH`) sem acoplar a regra de cálculo aos detalhes dos DTOs externos (`AmlResult` e `SanctionResult`).

## Mocks de Integração (Mockoon)

Para simular contratos de APIs externas de KYC e AML durante o desenvolvimento local sem depender de endpoints reais, o repositório disponibiliza ambientes Mockoon em `src/main/resources/`:

* `mockoon_aml.json`
* `mockoon_kyc.json`

---

## Testes Automatizados no Módulo

Os testes estão situados em `src/test/java/dio/compliance/`.

A estratégia de testes abrange:

1. **Testes Unitários dos Casos de Uso:** Garantem que a lógica de decisão do `AnalyzeCompanyRiskUseCase` reage corretamente a cenários com ou sem sanções, simulando os clientes Feign com **Mockito**.
2. **Testes de Integração com Feign/WireMock:** Validam a desserialização dos JSONs retornados e a execução dos métodos de *fallback* em caso de falha de rede.

```java
// Localização: src/test/java/dio/compliance/ComplianceApplicationTests.java
@SpringBootTest
class ComplianceApplicationTests {

    @Test
    void contextLoads() {
        // Valida se as interfaces @EnableFeignClients e o contexto Spring sobem corretamente
    }
}

```
