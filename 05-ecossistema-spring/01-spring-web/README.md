# Spring Web REST API - Task Manager

## Arquitetura e Separação de Camadas
Este projeto aplica os princípios de Clean Architecture e DDD para isolar o ecossistema Spring das regras de negócio centrais.

* **Domain (`domain/`):** Contém as entidades centrais (`Task`), Value Objects (`TaskId`), exceções de negócio (`TaskNotFoundException`) e os contratos de interface (`TaskRepository`). Isento de dependências do framework.
* **Application (`application/`):** Casos de uso (`CreateTaskUseCase`, `GetTasksUseCase`, etc.) que orquestram o fluxo da aplicação sem dependência direta de detalhes HTTP ou de persistência.
* **Infrastructure (`infrastructure/`):** Implementações tecnológicas externas.
  * `http/`: Controladores REST (`TaskController`) e manipuladores globais de exceção (`GlobalExceptionHandler`).
  * `repository/`: Implementações concretas de persistência (`InMemoryTaskRepository`).

## Injeção de Dependência e DTOs
O isolamento entre a API pública e o domínio é garantido através de DTOs de entrada e saída:

* **Requests/Responses:** As classes `CreateTaskRequest` e `TaskResponse` evitam a exposição direta das entidades de domínio para o cliente HTTP.
* **Injeção via Construtor:** Injeção das dependências de caso de uso diretamente pelo construtor do controller, favorecendo imutabilidade e facilidade nos testes unitários.

```java
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody CreateTaskRequest request) {
        TaskOutput output = createTaskUseCase.execute(request.toInput());
        return ResponseEntity.created(URI.create("/tasks/" + output.id())).body(TaskResponse.from(output));
    }
}

```

## Tratamento Global de Exceções

Erros de domínio são capturados na camada de infraestrutura pelo `GlobalExceptionHandler` configurado com `@RestControllerAdvice`. Exceções como `TaskNotFoundException` são convertidas automaticamente em respostas HTTP sem expor o stacktrace interno.

## Testes Automatizados no Módulo

Estruturados em `src/test/java/dio/taskmanager/`, a estratégia de testes divide-se por responsabilidade:

1. **Testes Unitários dos Use Cases (`application/`):** Isolam as regras de aplicação utilizando **Mockito** para simular as operações de repositório.
2. **Testes de Controladores (`infrastructure/http/`):** Validam serialização JSON, mapeamento de rotas REST e respostas de código de status HTTP.

```java
// Localização: src/test/java/dio/taskmanager/application/CreateTaskUseCaseTest.java
@ExtendWith(MockitoExtension.class)
class CreateTaskUseCaseTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private CreateTaskUseCase useCase;

    @Test
    void deveCriarTarefaComSucesso() {
        CreateTaskInput input = new CreateTaskInput("Estudar Spring", "Descrição");
        
        TaskOutput output = useCase.execute(input);

        assertNotNull(output.id());
        verify(taskRepository, times(1)).save(any(Task.class));
    }
}

```

