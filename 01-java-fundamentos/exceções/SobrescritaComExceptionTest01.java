import java.io.FileNotFoundException;

public class SobrescritaComExceptionTest01 {
    static void main(String[] args) {
        Pessoa pessoa = new Pessoa();
        Funcionario funcionario = new Funcionario();

        try {
            funcionario.salvar();
        } catch (LoginInvalidoException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
