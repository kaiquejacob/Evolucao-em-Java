public class HerancaTest01 {
    static void main(String[] args) {
        Endereco endereco = new Endereco();
        endereco.setRua("Rua 3");
        endereco.setCep("012345 - 209");

        Pessoa pessoa = new Pessoa("Sandro");
        pessoa.setCpf("11111111");
        pessoa.setEndereço(endereco);


        pessoa.imprime();

        Funcionario funcionario = new Funcionario("Ana Paula");
        funcionario.setCpf("2222222");
        funcionario.setEndereço(endereco);
        funcionario.setSalario(20000);

        System.out.println("---------------------");
        funcionario.imprime();

    }
}
