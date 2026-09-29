public class ScannerTest01 {
    static void main(String[] args) {
        String texto = "Levi, Eren, Mikasa, true, 200";
        String[] nomes = texto.split(",");

        for (String nome : nomes){
            System.out.println(nome.trim());               // trim -> remove espaço
        }

    }
}
