import java.util.Locale;
import java.util.Scanner;

public class Projeto_aluno {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Quantos alunos deseja cadastrar: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // limpa o \n do buffer

        String[] nomes = new String[quantidade];
        double[] nota1 = new double[quantidade];
        double[] nota2 = new double[quantidade];
        double[] medias = new double[quantidade];

        // Cadastro
        for (int i = 0; i < quantidade; i++) {
            System.out.println("\n--- Aluno " + (i + 1) + " ---");

            System.out.println("Nome: ");
            nomes[i] = scanner.nextLine();

            System.out.println("Nota 1: ");
            nota1[i] = scanner.nextDouble();

            System.out.println("Nota 2: ");
            nota2[i] = scanner.nextDouble();
            scanner.nextLine(); // limpa o \n antes da próxima iteração

            medias[i] = (nota1[i] + nota2[i]) / 2;
        }

        // Resultado
        System.out.println("\n---- RESULTADOS ----");
        for (int i = 0; i < quantidade; i++) {
            System.out.println("\nAluno: " + nomes[i]);
            System.out.printf("Média: %.2f%n", medias[i]);

            if (medias[i] >= 6) {
                System.out.println("Situação: APROVADO");
            } else {
                System.out.println("Situação: REPROVADO");
            }
        }

        // Resumo Geral
        int aprovados = 0;
        int reprovados = 0;

        for (int i = 0; i < quantidade; i++) {
            if (medias[i] >= 6) {
                aprovados++;
            } else {
                reprovados++;
            }
        }

        System.out.println("\n---- RESUMO ----");
        System.out.println("Total de alunos: " + quantidade);
        System.out.println("Aprovados:       " + aprovados);
        System.out.println("Reprovados:      " + reprovados);

        scanner.close();
    }
}