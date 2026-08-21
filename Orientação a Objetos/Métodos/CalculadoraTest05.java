public class CalculadoraTest05 {
    static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        int[] numeros = {1, 2, 3, 4, 5};
        calculadora.somaArray(numeros);

        // Varargs
        calculadora.somaVarArgs(1, 3, 5, 7, 9, 11);
    }
}
