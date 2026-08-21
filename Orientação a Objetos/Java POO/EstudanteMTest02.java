public class EstudanteMTest02 {
    static void main(String[] args) {
        EstudanteM estudante01 = new EstudanteM();
        EstudanteM estudante02 = new EstudanteM();

        estudante01.nome = "Sandro";
        estudante01.idade = 56;
        estudante01.sexo = 'M';

        estudante02.nome = "Ana Paula";
        estudante02.idade = 53;
        estudante02.sexo = 'F';

        estudante01.imprimi();
        estudante02.imprimi();

    }
}
