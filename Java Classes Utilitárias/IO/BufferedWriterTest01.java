import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterTest01 {
    static void main(String[] args) {
        File file = new File("file.txt");
        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write("O DevDojo é o melhor curso do brasillllll");
            bw.newLine();
            bw.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
