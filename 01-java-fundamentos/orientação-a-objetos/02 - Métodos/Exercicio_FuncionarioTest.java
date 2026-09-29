public class Exercicio_FuncionarioTest {
    static void main(String[] args) {
        Exercicio_Funcionario funcionario = new Exercicio_Funcionario();
        funcionario.nome = "Sandro";
        funcionario.idade = 56;
        funcionario.salarios = new double[]{1200, 987.65, 2000};

        funcionario.imprimi();
    }
}
