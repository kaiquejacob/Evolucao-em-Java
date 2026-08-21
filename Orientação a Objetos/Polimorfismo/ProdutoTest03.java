public class ProdutoTest03 {
    static void main(String[] args) {
        Produto produto = new Computador("Ryzen 9", 3000);

        Tomate tomate = new Tomate("Tomate Americano", 20);
        tomate.setDataValidade("11/02/2028");

        CalculadoraImposto.calcularImposto(tomate);
        System.out.println("----------------------");
        CalculadoraImposto.calcularImposto(produto);

    }
}
