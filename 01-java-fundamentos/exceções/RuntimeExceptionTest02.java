public class RuntimeExceptionTest02 {
    static void main(String[] args) {
        divisao(1,0);

        System.out.println("Código finalizado");

    }
    private static int divisao(int a, int b){
        if (b == 0){
            throw new IllegalArgumentException("Argumento ilegal, não pode ser 0");
        }
        return a/b;
    }
}
