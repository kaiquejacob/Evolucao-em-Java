public class StringBuilderTest {
    static void main(String[] args) {
        String nome = "Sandro Luiz";
        nome.concat("Dev Dojo");
        System.out.println(nome);
        StringBuilder sb = new StringBuilder("Sandro Luiz");
        sb.append(" Dev Dojo").append(" Academy");
        sb.reverse();
        sb.reverse();
        sb.delete(0,3);
        System.out.println(sb);

    }
}
