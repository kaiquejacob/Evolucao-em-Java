import java.util.Scanner;
import java.util.Locale;

/*
2. Verificador de IMC
Usuário digita peso e altura, calcula o IMC e usa if/else para classificar
(abaixo do peso, normal, sobrepeso, etc.).
 */

public class Projeto_IMC {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Digite o peso: ");
        double peso = scanner.nextDouble();

        System.out.println("Digite a altura: ");
        double altura = scanner.nextDouble();

        double imc = peso / (altura * altura);

        if (imc <= 18.5){
            System.out.println("Abaixo do peso");
        } else if (imc > 18.5 && imc <= 25) {
            System.out.println("Peso normal");
        } else if (imc > 25 && imc < 30) {
            System.out.println("Sobrepeso");
        }else{
            System.out.println("Obesidade");
        }
        System.out.println("Seu IMC é %.2f%n" + imc);

        scanner.close();
    }
}
