import java.util.ArrayList;
import java.util.List;

public class GenericsTest01 {
    static void main(String[] args) {
        List<String>    lista = new ArrayList<>();
        lista.add("Midoriya");
        lista.add("Midoriya");

        for (String o : lista){
            System.out.println(o);
        }

        for (Object o : lista){
            System.out.println(o);
        }

    }
}
