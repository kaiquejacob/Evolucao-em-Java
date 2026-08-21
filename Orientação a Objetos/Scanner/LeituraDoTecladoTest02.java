import java.util.Scanner;

public class LeituraDoTecladoTest02 {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==== O grande software de previsão do futuro ====");
        System.out.println("\nDigite uma pergunta que eu respondo sim ou não: ");
        String pergunta = scanner.nextLine();

        if (pergunta.charAt(0) == ' '){
            System.out.println("SIM");
        }else {
            System.out.println("NÃO");
        }
    }
}
