public class Estruturas_de_repeticao {
    static void main(String[] args) {
        // while
        int contagem = 0;
        while (contagem < 10){
            System.out.println(contagem);              // (contagem++) -> funciona também
            contagem += 1;
        }
        // do while

        do {
            System.out.println("Dentro do do-while");
        } while (contagem < 10);
        // for   ->   for ( declara a variável; fala a condição; como a variavel vai alterar o status)

        for (int i = 0; i < 10; i++){
            System.out.println("For "+ i);
        }
    }
}
