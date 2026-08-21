/*
Prática

Crie variáveis para os campos descritos abaixo entre <> e imprima a seguinte mensagem:

Eu <nome>, morando no endereço <endereço>,
confirmo que recebi o salário de <salario>, na data <data>.
 */

public class Tipos_primitivos_exercicio {
    static void main(String[] args) {
        String nome = "Kaíque";
        String endereco = "São Paulo";
        float salario = 2500;
        String dataRecebimento = "20/12/2026";
        String relatorio = "Eu "+ nome+ ", morando no endereço "+endereco+", confirmo que recebi o salário de "+ salario+", na data "+ dataRecebimento;

        System.out.println(relatorio);
    }
}
