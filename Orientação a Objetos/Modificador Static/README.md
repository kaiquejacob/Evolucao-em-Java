# Modificador static

## Atributo Static
Um atributo `static` pertence à **classe**, não a cada objeto individualmente — existe uma única cópia dele na memória, compartilhada por todas as instâncias. Alterar o valor através de um objeto afeta o valor visto por todos os outros.

```java
public class Carro {
    public static double velocidadeLimite = 250;
}
```

```java
Carro.setVelocidadeLimite(180); // muda pra TODOS os carros, não só um específico
```

## Método Static
Um método `static` também pertence à classe e pode ser chamado sem precisar criar um objeto (`Carro.getVelocidadeLimite()`). Por não estar ligado a uma instância, um método estático **não pode acessar atributos ou métodos de instância** diretamente, nem usar `this` — ele só enxerga outros membros estáticos.

```java
public static void setVelocidadeLimite(double velocidadeLimite) {
    Carro.velocidadeLimite = velocidadeLimite;
}
```

## Quando Usar
Static faz sentido para dados ou comportamentos que fazem sentido no nível da "categoria", não de um objeto específico — um contador de quantos objetos já foram criados, uma constante compartilhada, um método utilitário que não depende de estado (tipo `Math.sqrt()`). Se o dado varia de objeto para objeto, ele não deveria ser static.
