public class StringTest02 {
    static void main(String[] args) {
        String nome = "     Sandro      ";
        String numeros = "012345";
        System.out.println(nome.charAt(0));
        System.out.println(nome.length());
        System.out.println(nome.replace("a", "o"));
        System.out.println(nome.toLowerCase());
        System.out.println(nome.toUpperCase());

        System.out.println(numeros.length());
        System.out.println(numeros.substring(3,numeros.length()));

        System.out.println(nome.trim());        // remove valor em branco


    }
}
