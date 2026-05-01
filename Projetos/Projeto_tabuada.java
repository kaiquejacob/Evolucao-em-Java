import java.util.Scanner;

/*
3. Tabuada interativa
Pede um número e imprime a tabuada dele de 1 a 10 com for.
Pode incluir opção de repetir para outro número.
 */

public class Projeto_tabuada {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nDigite um número:");
        int num = scanner.nextInt();
        System.out.println("----TABUADA----");

        for (int i = 1; i <= 10; i++) {
            System.out.println("\n" + num + " * " + i + " = " + num * i);
        }
    }
}
