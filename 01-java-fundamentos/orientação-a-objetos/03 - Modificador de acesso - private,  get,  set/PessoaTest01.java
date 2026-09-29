public class PessoaTest01 {
    static void main(String[] args) {
        Pessoa pessoa = new Pessoa();

        //pessoa.nome = "Sandro";
        pessoa.setNome("Sandro");
        pessoa.setIdade(56);
        //pessoa.imprimi();

        System.out.println(pessoa.getNome());
        System.out.println(pessoa.getIdade());

    }
}
