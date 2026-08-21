public class CarroTest01 {
    static void main(String[] args) {
        Carro carro01 = new Carro();
        Carro carro02 = new Carro();

        carro01.nome = "Fusca";
        carro01.modelo = "Sport";
        carro01.ano = 1969;

        carro02.nome = "Mustang";
        carro02.modelo = "GT 500";
        carro02.ano = 1968;

        /*
        Referencia:

        carro01 = carro02;
         */

        System.out.println("Carro 1: ");
        System.out.println(carro01.nome);
        System.out.println(carro01.modelo);
        System.out.println(carro01.ano);

        System.out.println("\nCarro 2: ");
        System.out.println(carro02.nome);
        System.out.println(carro02.modelo);
        System.out.println(carro02.ano);

    }
}
