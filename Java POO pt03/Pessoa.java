public class Pessoa {
    // PROTECTED -> deixa eu acessar as variáveis dentro do pacote

    protected String nome;
    protected String cpf;
    protected Endereco endereco;

    static {
        System.out.println("Dentro do bloco de inicialização estático de pessoa");
    }
    {
        System.out.println("Dentro do bloco de inicialização de pessoa 1 ");
    }
    {
        System.out.println("Dentro do bloco de inicialização de pessoa 2 ");
    }

    public Pessoa(String nome){
        System.out.println("Dentro do construtor de pessoa");
        this.nome = nome;
    }

    public Pessoa(String nome, String cpf){
        this(nome);
        this.cpf = cpf;
    }

    public void imprime(){
        System.out.println(this.nome);
        System.out.println(this.cpf);
        System.out.println(this.endereco.getRua() + "  " + this.endereco.getCep());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereço() {
        return endereco;
    }

    public void setEndereço(Endereco endereço) {
        this.endereco = endereço;
    }
}
