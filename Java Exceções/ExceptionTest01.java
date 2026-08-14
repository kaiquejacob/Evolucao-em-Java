import java.io.File;
import java.io.IOException;

public class ExceptionTest01 {
    static void main(String[] args) {

    }
    private static void CriarNovoArquivo(){
        File file = new File("arquivo\\teste.txt");
        try{
            boolean isCriado = file.createNewFile();
            System.out.println("Arquivo criado" + isCriado);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
