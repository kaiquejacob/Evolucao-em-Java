public class Estrutura_de_repeticao_break {
    static void main(String[] args) {
        // Imprima os primeiros 25 números de um dado valor. Por exemplo, 50

        int valorMaximo = 50;
        for (int j = 0; j <= valorMaximo; j++) {
            if (j > 25){
                break;
            }
            System.out.println(j);
        }
    }
}
