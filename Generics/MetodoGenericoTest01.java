import java.util.List;

public class MetodoGenericoTest01 {
    static void main(String[] args) {
        List<Barco> barcoList = criarArrayComUmObjeto(new Barco("Canoa Marota"));
        System.out.println(barcoList);
    }

    private static <T>List<T> criarArrayComUmObjeto(T t){
        return List.of(t);
    }

//    private static <T extends Comparable<T>> List<T> criarArrayComUmObjeto(T t){
//        return List.of(t);
//    }
}
