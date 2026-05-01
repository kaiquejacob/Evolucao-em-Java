import java.util.Scanner;

/*
Calculadora no terminal
Recebe dois números e uma operação (+, -, *, /) via Scanner, usa switch para executar e imprime o resultado.
 */
class Calculadora{

    public double soma(double a, double b){
        return a+ b;
    }

    public double subtracao(double a, double b){
        return a - b;
    }

    public double multiplicacao(double a, double b){
        return a * b;
    }

    public double divisao(double a, double b){
        return a / b;
    }

    public double modulo(double a, double b){
        return a % b;
    }
}

public class Projeto_Calculadora {
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== CALCULADORA =====");
        System.out.println("Digite 9 na operação para sair.");

        while (true) {
            System.out.println("\nDigite o primeiro número: ");
            double num1 = scanner.nextDouble();

            System.out.println("Digite o segundo número: ");
            double num2 = scanner.nextDouble();

            System.out.println("Digite a operação ( +  -  *  /  % ) ou 9 para sair: ");
            char operacao = scanner.next().charAt(0);

            if (operacao == '9') {
                System.out.println("Saindo...");
                break;
            }

            double resultado;

            switch (operacao) {
                case '+':
                    resultado = calculadora.soma(num1, num2);
                    System.out.println("Resultado: " + resultado);
                    break;
                case '-':
                    resultado = calculadora.subtracao(num1, num2);
                    System.out.println("Resultado: " + resultado);
                    break;
                case '*':
                    resultado = calculadora.multiplicacao(num1, num2);
                    System.out.println("Resultado: " + resultado);
                    break;
                case '/':
                    if (num2 == 0) {
                        System.out.println("Não existe divisão por zero!");
                    } else {
                        resultado = calculadora.divisao(num1, num2);
                        System.out.println("Resultado: " + resultado);
                    }
                    break;
                case '%':
                    resultado = calculadora.modulo(num1, num2);
                    System.out.println("Resultado: " + resultado);
                    break;
                default:
                    System.out.println("Operação inválida!");
            }
        }

        scanner.close();
    }
}


