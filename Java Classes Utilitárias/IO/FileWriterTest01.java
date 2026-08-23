import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// File
// FileWriter
// FileReader
// BufferedWriter
// BufferedReader


public class FileWriterTest01 {
    static void main(String[] args) {
        File file = new File("file.txt");
        try(FileWriter fw = new FileWriter(file, true)) {
            fw.write("O DevDojo é o melhor curso do brasillllll\nContinuandoooo\n");
            fw.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
