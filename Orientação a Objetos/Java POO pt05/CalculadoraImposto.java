public class CalculadoraImposto {

    public static void calcularImposto(Produto produto) {
        System.out.println("--------Relatório de imposto--------");
        double imposto = produto.calcularImposto();
        System.out.println("\nProduto: " + produto.getNome());
        System.out.println("Valor: " + produto.getValor());
        System.out.println("Imposto a ser pago: " + imposto);

        if (produto instanceof Tomate) {
            Tomate tomate = (Tomate) produto;
            System.out.println("Data de validade: " + tomate.getDataValidade());
        }
    }
}
