public class StringTest01 {
    static void main(String[] args) {
        String nome = "William";        // String constant pool
        String nome2 = "William";
        nome = nome.concat(" Suane");   // nome += " Suane "
        System.out.println(nome);
        System.out.println(nome == nome2);

        String nome3 = new String("William"); // 1 variável de referência, 2 objeto do tipo string, 3 uma string no pool de string
        System.out.println(nome2 == nome3);
        System.out.println(nome2 == nome3.intern());
    }
}
