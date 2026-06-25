import java.util.Scanner;
import java.util.Locale;

/*
8. Conversor de unidades

Converte temperatura (°C ↔ °F ↔ K), distância (km ↔ milhas), peso (kg ↔ lb)
— menu com switch e cálculos com double.
*/

class Conversor{
    private String[] historico = new String[50];
    private int totalConversoes = 0;

    private void registrar (String conversao){
        if (totalConversoes < historico.length){
            historico[totalConversoes] = conversao;
            totalConversoes++;
        }
    }

    public void temperatura(Scanner scanner){

        System.out.println("\n--- TEMPERATURA ---");
        System.out.println("1 - Celsius para Fahrenheit");
        System.out.println("2 - Fahrenheit para Celsius");
        System.out.println("3 - Celsius para Kelvin");
        System.out.println("4 - Kelvin para Celsius");
        System.out.println("Escolha: ");
        int opcao = scanner.nextInt();

        System.out.println("Digite o valor: ");
        double valorT = scanner.nextDouble();

        String linha;
        switch (opcao){
            case 1:
                double f = (valorT * 9.0 / 5.0) + 32;
                linha = String.format("%.2f °C → %.2f °F", valorT, f);
                System.out.println("Resultado: " + linha);
                registrar(linha);
                break;
            case 2:
                double c = (valorT - 32) * 5.0 / 9.0;
                linha = String.format("%.2f °F → %.2f °C", valorT, c);
                System.out.println("Resultado: " + linha);
                registrar(linha);
                break;
            case 3:
                if (valorT < -273.15) {
                    System.out.println("Erro: temperatura abaixo do zero absoluto!");
                } else {
                    double k = valorT + 273.15;
                    linha = String.format("%.2f °C → %.2f K", valorT, k);
                    System.out.println("Resultado: " + linha);
                    registrar(linha);
                }
                break;
            case 4:
                double c2 = valorT - 273.15;
                linha = String.format("%.2f K → %.2f °C", valorT, c2);
                System.out.println("Resultado: " + linha);
                registrar(linha);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    public void distancia(Scanner scanner){

        System.out.println("\n--- DISTÂNCIA ---");
        System.out.println("1 - KM para Milhas");
        System.out.println("2 - Milhas para KM");
        System.out.println("Escolha: ");
        int opcaoDis = scanner.nextInt();

        System.out.println("Digite o valor: ");
        double valorD = scanner.nextDouble();

        String linha;
        switch (opcaoDis){
            case 1:
                double milhas = valorD * 0.621371;
                linha = String.format("%.2f km → %.2f milhas", valorD, milhas);
                System.out.println("Resultado: " + linha);
                registrar(linha);
                break;
            case 2:
                double km = valorD * 1.60934;
                linha = String.format("%.2f milhas → %.2f km", valorD, km);
                System.out.println("Resultado: " + linha);
                registrar(linha);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    public void peso(Scanner scanner){

        System.out.println("\n--- PESO ---");
        System.out.println("1 - KG para LB(libra)");
        System.out.println("2 - LB(libra) para KG");
        System.out.println("Escolha: ");
        int opcaoPeso = scanner.nextInt();

        System.out.println("Digite o valor: ");
        double valorP = scanner.nextDouble();

        String linha;
        switch (opcaoPeso){
            case 1:
                double lb = valorP * 2.20462;
                linha = String.format("%.2f kg → %.2f lb", valorP, lb);
                System.out.println("Resultado: " + linha);
                registrar(linha);
                break;
            case 2:
                double kg = valorP / 2.20462;
                linha = String.format("%.2f lb → %.2f kg", valorP, kg);
                System.out.println("Resultado: " + linha);
                registrar(linha);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    public void verHistorico() {
        System.out.println("\n===== HISTÓRICO =====");
        if (totalConversoes == 0) {
            System.out.println("Nenhuma conversão realizada.");
        } else {
            for (int i = 0; i < totalConversoes; i++) {
                System.out.println((i + 1) + ". " + historico[i]);
            }
        }
        System.out.println("=====================");
    }
}
public class Projeto_conversorUnidades {
    static void main(String[] args) {
        Conversor conversor = new Conversor();

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        int categoria = 0;
        while (categoria != 5){
            System.out.println("\n===== CONVERSOR =====");
            System.out.println("1 - Temperatura");
            System.out.println("2 - Distância");
            System.out.println("3 - Peso");
            System.out.println("4 - Ver histórico");
            System.out.println("5 - Sair");
            System.out.println("Escolha: ");
            categoria = scanner.nextInt();

            switch (categoria){
                case 1:
                    conversor.temperatura(scanner);
                    break;

                case 2:
                    conversor.distancia(scanner);
                    break;

                case 3:
                    conversor.peso(scanner);
                    break;

                case 4:
                    conversor.verHistorico();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
