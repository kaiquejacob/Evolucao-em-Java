// SCANNER

import java.util.Scanner;

public class LeiruraDoTecladoTest01 {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu nome: ");
        String nome = scanner.nextLine();

        System.out.println("Digite sua idade: ");
        int idade = scanner.nextInt();

        System.out.println("Digite M ou F para o sexo: ");
        char sexo = scanner.next().charAt(0);

        System.out.println("------------------------");
        System.out.println("Nome: " + nome);
        System.out.println("Idade : " + idade);
        System.out.println("Sexo: " + sexo);

    }
}
