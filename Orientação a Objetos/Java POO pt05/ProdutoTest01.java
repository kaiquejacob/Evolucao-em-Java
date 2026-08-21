public class ProdutoTest01 {
    static void main(String[] args) {
        Computador computador = new Computador("Macbook", 20000);
        Tomate tomate = new Tomate("Tomate Siciliano", 10);
        Televisao tv = new Televisao("Samsung 50\" ", 5000);

        CalculadoraImposto.calcularImposto(computador);
        System.out.println("----------------------------");
        CalculadoraImposto.calcularImposto(tomate);
        System.out.println("----------------------------");
        CalculadoraImposto.calcularImposto(tv);

    }
}
