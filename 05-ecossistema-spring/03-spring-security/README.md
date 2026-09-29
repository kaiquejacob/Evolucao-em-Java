# Spring Security e Controle de Acesso - Proposal Management

## Autenticação Customizada e Arquitetura
Este módulo implementa segurança e controle de acesso granular na API de Gestão de Propostas (`ProposalManagementApplication`), separando a infraestrutura de segurança do domínio central:

* **Autenticação REST via Filtro:** A autenticação é gerida por um filtro customizado (`RestUsernamePasswordAuthenticationFilter`), que intercepta as credenciais enviadas em requisições HTTP e valida a autenticidade usando o `JpaUserDetailsService`.
* **Configuração de Segurança (`SecurityConfig`):** Define a cadeia de filtros (`SecurityFilterChain`), desabilita CSRF para APIs stateless, configura o gerenciamento de sessão e expõe os endpoints públicos de login e proteção por *roles* (`UserRole`).

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/auth/login").permitAll()
                .anyRequest().authenticated()
            )
            .build();
    }
}

```

## Controle de Acesso Dinâmico (Strategy Pattern)

Em vez de espalhar `if/else` e anotações nos controladores para checar permissões, o projeto utiliza o **Strategy Pattern** no módulo `proposal/list/` para definir o escopo de visibilidade de dados:

* **`OwnStrategy`:** Restringe a listagem apenas às propostas criadas pelo próprio usuário logado.
* **`AllStrategy`:** Permite a visualização de todas as propostas cadastradas (escopo administrativo).
* **`Factory`:** Seleciona dinamicamente a estratégia adequada com base nos papéis (`UserRole`) do token do usuário autenticado.

```java
public class ProposalListStrategyFactory {

    public ListProposalsStrategy getStrategy(UserRole role) {
        if (role == UserRole.ADMIN) {
            return new AllStrategy();
        }
        return new OwnStrategy();
    }
}

```

## Camadas de Domínio e Infraestrutura

A estrutura isola totalmente o contexto de segurança (`auth/`) do contexto de negócios (`proposal/`):

* **Contexto Auth:** Gerencia entidades de usuário (`User`), perfis de acesso (`UserRole`) e os adaptadores de persistência e autenticação JPA.
* **Contexto Proposal:** Contém entidades de domínio (`Proposal`, `Owner`), casos de uso (`CreateProposalUseCase`, `ListProposalsUseCase`) e adaptadores REST e JPA desacoplados.

---

## Testes Automatizados no Módulo

Os testes automatizados estão estruturados em `src/test/java/dio/proposalmanagement/`.

A estratégia de testes cobre:

1. **Testes de Integração de Segurança:** Validam se endpoints protegidos recusam requisições não autenticadas (HTTP 401/403) e aceitam credenciais válidas.
2. **Testes das Estratégias de Acesso:** Garantem que os filtros de consulta respeitam o escopo do usuário sem vazamento de dados entre proprietários distintos.

```java
// Localização: src/test/java/dio/proposalmanagement/ProposalManagementApplicationTests.java
@SpringBootTest
class ProposalManagementApplicationTests {

    @Test
    void contextLoads() {
        // Valida a inicialização da cadeia do Spring Security e do contexto Spring
    }
}

```

