/*
Exercício

--> Eu quero saber quanto eu tenho que pagar na Holanda em 2020 baseado no meu salário anual

 */

public class Estruturas_condicionais_exercicio {
    static void main(String[] args) {
        double salarioAnual = 70000;
        double primeiraFaixa = 9.70 / 100;
        double segundaFaixa = 37.35 / 100;
        double terceiraFaixa = 49.50 / 100;
        double valorImposto;

        if (salarioAnual <= 34712){
            valorImposto = salarioAnual * primeiraFaixa;
        }else if (salarioAnual >= 34713 && salarioAnual <= 68507){
            valorImposto = salarioAnual * segundaFaixa;
        }else{
            valorImposto = salarioAnual * terceiraFaixa;
        }
        System.out.println(valorImposto);
    /*
    -->Utilizando Switch
    Dados os valores de 1 a 7, imprima se é dia útil ou final de semana
    Considerando 1 como domingo
     */

        int dia = 1;
        switch (dia){
            case 1:
            case 7 :
                System.out.println("Final de semana");
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                System.out.println("Dia útil");
                break;
            default:
                System.out.println("Opção inválida");
        }
    }
}


