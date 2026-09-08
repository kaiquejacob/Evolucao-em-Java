/*
Crie uma classe Funcionario com os seguintes atributos

nome
idade
salario // três salários devem ser guardados

Crie dois métodos
1 - Para imprimir os dados
2 - Para tirar a média dos salários e imprimir o resultado

 */

public class Exercicio_Funcionario {
    public String nome;
    public int idade;
    public double[] salarios;

    public void imprimi(){
        System.out.println(this.nome);
        System.out.println(this.idade);
        if (salarios == null){
            return;
        }
        System.out.print("Salários: ");
        for (double salario : salarios){
            System.out.print(salario + " | ");
        }

        imprimiMediaSalario();
    }

    public void imprimiMediaSalario(){
        if (salarios == null) {
            return;
        }
        double media = 0;

        for (double salario : salarios){
            media += salario;
        }
        media /= salarios.length;             // lenght -> tamanho do array
        System.out.println("\n\nMédia salarial: " + media);
    }
}
