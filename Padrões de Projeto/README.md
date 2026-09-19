# Padrões de Projeto (Design Patterns)

## Builder
Resolve o problema de construtores com muitos parâmetros opcionais, onde fica difícil saber (só olhando a chamada) qual valor corresponde a qual parâmetro, e onde você precisaria de várias sobrecargas de construtor pra cobrir combinações diferentes. Builder constrói o objeto passo a passo, com métodos encadeados (cada um retornando `this`), e só monta o objeto de fato no `.build()` final.

```java
public class Person {
    public static final class PersonBuilder {
        private PersonBuilder() {}
        public static PersonBuilder builder() { return new PersonBuilder(); }

        public PersonBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this; // permite encadear a próxima chamada
        }
        // ... outros campos

        public Person build() {
            return new Person(firstName, lastName, userName, email);
        }
    }
}
```

```java
Person build = Person.PersonBuilder.builder()
        .firstName("William")
        .lastName("Suane")
        .userName("ViradoNoJiraya")
        .build();
```

A leitura fica autoexplicativa (cada valor tem o nome do campo do lado), e omitir um campo opcional não exige uma sobrecarga de construtor a mais — só não chama aquele método.

## Factory
Centraliza a lógica de **qual implementação concreta criar**, escondendo essa decisão de quem só quer usar o objeto. Quem chama a fábrica programa contra a interface (`Currency`), sem precisar saber (nem importar) as classes concretas (`Real`, `USDollar`) diretamente.

```java
public class CurrencyFactory {
    public static Currency newCurrency(Country country) {
        return switch (country) {
            case USA -> new USDollar();
            case BRAZIL -> new Real();
            default -> throw new IllegalArgumentException("No currency found for this country");
        };
    }
}
```

```java
Currency currency = CurrencyFactory.newCurrency(Country.BRAZIL); // não precisa saber que por trás é um "Real"
```

Vantagem prática: se amanhã aparecer um novo país, a mudança fica isolada dentro da fábrica — quem usa `CurrencyFactory.newCurrency(...)` não muda nada.

## Singleton: o Problema que Resolve
Singleton garante que existe **uma única instância** de uma classe em toda a aplicação, com um ponto de acesso global a ela. Sem isso, nada impede que várias partes do código criem suas próprias instâncias de algo que deveria ser compartilhado — no exemplo do avião, se cada parte do código faz `new Aircraft(...)`, cada uma tem seu próprio conjunto de assentos disponíveis, e reservar o assento "1A" duas vezes "funciona" (porque na prática são dois aviões diferentes, não um).

## Singleton: Eager Initialization
A instância única é criada **assim que a classe é carregada pela JVM**, atribuída direto a um atributo `static final`. Simples e naturalmente thread-safe (o carregamento de classe pela JVM já é sincronizado), mas cria o objeto mesmo que ele nunca chegue a ser usado — desperdício se a inicialização for cara.

```java
public class AircraftSingletonEager {
    private static final AircraftSingletonEager INSTANCE = new AircraftSingletonEager("787-900");

    public static AircraftSingletonEager getINSTANCE() {
        return INSTANCE;
    }
}
```

## Singleton: Lazy Initialization
A instância só é criada na **primeira vez** que alguém pede ela, não no carregamento da classe — evita o desperdício do eager quando a instância é cara e pode nunca ser usada. Em compensação, precisa de cuidado extra pra ser thread-safe: sem sincronização, duas threads podem passar pelo `if (INSTANCE == null)` ao mesmo tempo e criar duas instâncias.

```java
public static AircraftSingletonLazy getINSTANCE() {
    if (INSTANCE == null) {                              // primeira checagem, sem lock (rápida)
        synchronized (AircraftSingletonLazy.class) {
            if (INSTANCE == null) {                       // segunda checagem, já dentro do lock
                INSTANCE = new AircraftSingletonLazy("787-900");
            }
        }
    }
    return INSTANCE;
}
```

Esse padrão de checagem dupla (*double-checked locking*) existe porque sincronizar o método inteiro toda vez que alguém pede a instância seria caro — a sincronização só é necessária **uma vez**, na criação; depois disso, a checagem sem lock já é suficiente e rápida.

**Fragilidade do Lazy (e do Eager também):** mesmo com o construtor privado, é possível burlar um Singleton clássico usando **reflection** — `getDeclaredConstructor()` + `setAccessible(true)` conseguem chamar o construtor "privado" diretamente e criar uma segunda instância, quebrando a garantia do padrão.

```java
Constructor<AircraftSingletonLazy> constructor = AircraftSingletonLazy.class.getDeclaredConstructor(String.class);
constructor.setAccessible(true);
AircraftSingletonLazy outraInstancia = constructor.newInstance("787-900"); // burla o singleton
```

## Singleton: Enum
A forma mais robusta de implementar Singleton em Java, porque a própria JVM **garante** que cada constante de um enum é instanciada uma única vez — e essa garantia vale mesmo contra reflection (a JVM proíbe explicitamente instanciar enum via reflection) e contra desserialização (que normalmente poderia criar uma segunda instância a partir de bytes salvos, mas enums são tratados de forma especial nesse processo também).

```java
public enum AircraftSingletonEnum {
    INSTANCE;

    private final Set<String> availableSeats;

    AircraftSingletonEnum() {
        this.availableSeats = new HashSet<>();
        this.availableSeats.add("1A");
    }

    public boolean bookSeat(String seat) {
        return availableSeats.remove(seat);
    }
}
```

```java
AircraftSingletonEnum.INSTANCE.bookSeat("1A"); // acesso direto, sem getInstance()
```

## DTO (Data Transfer Object)
Um DTO é uma classe cujo único propósito é **carregar dados** entre camadas ou processos (ex: montar uma resposta de API, agregar dados de várias entidades num único objeto de saída) — sem lógica de negócio, só os campos e (geralmente) um Builder pra montar. Combina naturalmente com os outros padrões: o exemplo do relatório usa `Aircraft`, `Currency` (via Factory) e `Person` (via Builder) como fontes, e monta um `ReportDto` (também via Builder) só com os dados que interessam pro relatório final.

```java
ReportDto reportDto = ReportDto.ReportDtoBuilder.builder()
        .aircraftName(aircraft.getName())
        .country(country)
        .currency(currency)
        .personName(person.getFirstName())
        .build();
```

A vantagem de um DTO dedicado, em vez de expor as entidades originais diretamente: o relatório mostra só o que interessa (nome, não o objeto `Aircraft` inteiro com seus assentos internos), e mudanças nas entidades de origem não quebram automaticamente o formato de saída.
