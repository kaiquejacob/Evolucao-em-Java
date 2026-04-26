public class ProfessorTest01 {
    static void main(String[] args) {
        Professor professor = new Professor();
        professor.nome = "Sandra";
        professor.idade = 50;
        professor.sexo = 'F';

        System.out.println("Nome: "+professor.nome + "\nIdade: " + professor.idade + "\nSexo: "+ professor.sexo);
    }
}
