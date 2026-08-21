public class Estrutura_de_repeticao_exercicio {
    static void main(String[] args) {
        // Imprima todos os números pares de 0 a 1000

        for (int i = 1; i <= 1000 ; i+=2) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }

        // Dado o valor de um carro, descubra em quantas ele pode ser parcelado
        // Condição valorParcela >= 1000

        double valorTotal = 30000;
        for (int parcela = 1; parcela <= valorTotal ; parcela++) {
            double valorParcela = valorTotal / parcela;
            if (valorParcela < 1000){
                break;
            }
            System.out.println("Parcela "+ parcela+ " R$ "+ valorParcela);
        }
    }
}
