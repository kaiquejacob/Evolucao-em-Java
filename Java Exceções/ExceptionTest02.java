import java.io.File;
import java.io.IOException;

public class ExceptionTest02 {
    static void main(String[] args) {

    }

    public static void CriarNovoArquivo() throws IOException {
        File file = new File("arquivo\\teste.txt");
        try {
            boolean isCriado = file.createNewFile();
            System.out.println("Arquivo criado" + isCriado);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}


