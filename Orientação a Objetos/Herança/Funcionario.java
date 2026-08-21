public class Funcionario extends Pessoa{

    private double salario;

    static {
        System.out.println("Dentro do bloco de inicialização estático de funcionario");
    }
    {
        System.out.println("Dentro do bloco de inicialização de funcionario 1 ");
    }
    {
        System.out.println("Dentro do bloco de inicialização de funcionario 2 ");
    }

    // SUPER
    public Funcionario(String nome){
        super(nome);
        System.out.println("Dentro do construtor do funcionario");
    }

    public void imprime(){
        super.imprime();
        System.out.println(this.salario);
    }

    // PROTECTED na classe pessoa -> deixa eu usar o this.nome
    public void relatorioPagamento(){
        System.out.println("Eu" + this.nome + " recebi o salário de " + this.salario);
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
