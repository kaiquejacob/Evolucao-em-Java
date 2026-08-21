public class EstudanteMTest01 {
    static void main(String[] args) {
        EstudanteM estudante01 = new EstudanteM();
        EstudanteM estudante02 = new EstudanteM();
        ImpressoraEstudante impressora = new ImpressoraEstudante();

        estudante01.nome = "Sandro";
        estudante01.idade = 56;
        estudante01.sexo = 'M';

        estudante02.nome = "Ana Paula";
        estudante02.idade = 53;
        estudante02.sexo = 'F';

        impressora.imprimi(estudante01);
        impressora.imprimi(estudante02);

        System.out.println("+++++++++++++++++++++++");

        impressora.imprimi(estudante01);
        impressora.imprimi(estudante02);
    }
}
