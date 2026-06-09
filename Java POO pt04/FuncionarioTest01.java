public class FuncionarioTest01 {
    static void main(String[] args) {
        Gerente gerente = new Gerente("Ana Paula", 5000);
        Desenvolvedor desenvolvedor = new Desenvolvedor("Sandro", 12000);

        System.out.println(gerente);
        System.out.println(desenvolvedor);

        gerente.imprimi();
        desenvolvedor.imprimi();
    }
}
