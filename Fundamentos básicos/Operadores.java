public class Operadores {
    static void main(String[] args) {
        // + - / *
        int numero1 = 10;
        int numero2 = 20;
        IO.println(numero2 + numero1);             // = 30
        IO.println(numero2 + numero1 + " = Valor");  // 30 = Valor
        IO.println("Valor = " + numero1 + numero2);  // Valor = 1020

        int soma = numero2 + numero1;
        IO.println(soma);

        // %                                                -> Resto da divisão
        int resto = 20 % 2;
        IO.println(resto);

        // < > <= >= == !=
        boolean maior = 10 > 20;
        boolean menor = 10 < 20;
        boolean igual = 10 == 20;
        boolean igualVerdadeiro = 10 == 10;
        boolean diferente = 10 != 20;

        IO.println("Maior " + maior);
        IO.println("Menor " + menor);
        IO.println("Igual " + igual);
        IO.println("IgualVerdadeiro " + igualVerdadeiro);
        IO.println("Diferente " + diferente);

        // && (AND)   || (OR)
        int idade = 35;
        float salario = 3500;
        boolean maiorQ = idade > 30 && salario >= 4500;
        boolean menorQ = idade < 30 && salario >= 3400;

        System.out.println("MaiorQ " + maiorQ);
        System.out.println("MenorQ " + menorQ);

        double contaCorrente = 200;
        double contaPoupanca = 10000;
        float ps5 = 5000;

        boolean ps5compravel = contaCorrente > ps5 || contaPoupanca > ps5;
        System.out.println("Compravel "+ ps5compravel);

        // = += -= *= /= %=
        double bonus = 2000;
        bonus += 1000;                       // mesma coisa que -> bonus = bonus + 1000

        System.out.println(bonus);

        // ++ --
        int contador = 0;
        contador++;                          // acrescenta +1
        contador--;                          // diminui -1
        System.out.println(contador);
    }
}
