import java.util.Scanner;

/*
5. Simulador de caixa eletrônico
Saldo inicial, menu com opções: sacar, depositar, ver saldo.
Usa switch + loop para manter o menu ativo até o usuário sair.
 */

class Caixa {
    private double saldo;
    private String[] historico = new String[50];               // até 50 transações
    private int totalTransacoes = 0;

    public Caixa(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    private void registrar(String transacao) {
        if (totalTransacoes < historico.length) {
            historico[totalTransacoes] = transacao;
            totalTransacoes++;
        }
    }

    public void verSaldo() {
        System.out.printf("Saldo : R$ %.2f%n", saldo);
    }

    public void sacar(Scanner scanner) {
        System.out.println("Valor que deseja sacar: ");
        double saque = scanner.nextDouble();
        if (saque > saldo) {
            System.out.println("Saldo insuficiente!");
            registrar("Saque NEGADO: R$ " + String.format("%.2f", saque));
        } else {
            saldo -= saque;
            System.out.println("Saque realizado! ");
            registrar("Saque:    - R$ " + String.format("%.2f", saque));
        }
    }

    public void depositar(Scanner scanner) {
        System.out.println("Valor que deseja depositar: ");
        double deposito = scanner.nextDouble();
        saldo += deposito;
        System.out.println("Deposito realizado!");
        registrar("Depósito: + R$ " + String.format("%.2f", deposito));
    }

    public void verExtrato() {
        System.out.println("\n===== EXTRATO =====");
        if (totalTransacoes == 0) {
            System.out.println("Nenhuma transação realizada.");
        } else {
            for (int i = 0; i < totalTransacoes; i++) {
                System.out.println(historico[i]);
            }
        }
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
        System.out.println("===================");
    }
}

public class Projeto_caixa {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o saldo: ");
        double saldoInicial = scanner.nextDouble();

        Caixa caixa = new Caixa(saldoInicial);

        int opcao = 0;
        while (opcao != 5) {
            System.out.println("----MENU----");
            System.out.println("1- Ver saldo");
            System.out.println("2- Sacar");
            System.out.println("3- Depositar");
            System.out.println("4 - Ver extrato");
            System.out.println("5- Sair");
            System.out.println("Digite a opção: ");
            opcao = scanner.nextInt();


            switch (opcao) {
                case 1:
                    caixa.verSaldo();
                    break;
                case 2:
                    caixa.sacar(scanner);
                    break;
                case 3:
                    caixa.depositar(scanner);
                    break;
                case 4:
                    caixa.verExtrato();
                    break;
                case 5:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
        scanner.close();
    }
}


