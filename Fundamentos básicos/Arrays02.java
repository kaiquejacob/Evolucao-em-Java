public class Arrays02 {
    static void main(String[] args) {
        // byte, short, int, long, float, double = 0
        // char = '\u0000'''
        // boolean = false
        // String = null

        String[] nomes = new String[3];
        nomes[0] = "Goku";
        nomes[1] = "Sandro";
        nomes[2] = "Ana";

        for (int i = 0; i < nomes.length; i++) {     // nomes.lenght  -> ja retorna o número de elementos do array
            System.out.println(nomes[i]);
        }
    }
}
