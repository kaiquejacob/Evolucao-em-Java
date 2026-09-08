public class CalculadoraTest03 {
    static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        double resultado = calculadora.divideDoisNumeros(20, 0);
        System.out.println(resultado);

        System.out.println(calculadora.divideDoisNumeros2(20, 2));

        System.out.println("------------");

        calculadora.imprimiDivisaoDeDoisNumeros(10,0);
    }
}
