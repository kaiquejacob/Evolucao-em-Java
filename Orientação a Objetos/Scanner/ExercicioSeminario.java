/*
Crie um sistema que gerencie seminários
sistema deverá cadastrar seminários,estudantes, professores, local
onde será realizado

 Um aluno poderá estar em apenas um seminário
 Um seminário poderá ter nenhum ou vários alunos
 Um professor poderá ministrar vários seminários
 Um seminário deve ter um local

 Campos básicos (excluindo relacionamento)
 -> seminário:título
 -> aluno: nome e idade14
 -> professor: nome, especialidade15
 -> local: endereco
 */


class Local {
    private String endereco;

    public Local(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}

class Aluno {
    private String nome;
    private int idade;
    private Seminario seminario;

    public Aluno(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}

class Seminario {
    private String titulo;
    private Aluno[] alunos;
    private Local local;

    public Seminario(String titulo) {
        this.titulo = titulo;
    }

    public Seminario(String titulo, Aluno[] alunos) {
        this.titulo = titulo;
        this.alunos = alunos;
    }

    public Seminario(String titulo, Aluno[] alunos, Local local) {
        this.titulo = titulo;
        this.alunos = alunos;
        this.local = local;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Aluno[] getAlunos() {
        return alunos;
    }

    public void setAlunos(Aluno[] alunos) {
        this.alunos = alunos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

class ProfessorE {
    private String nome;
    private String especialidade;
    private Seminario[] seminarios;

    public ProfessorE(String nome, String especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public ProfessorE(String nome, String especialidade, Seminario[] seminarios) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.seminarios = seminarios;
    }

    public void imprime(){
        System.out.println("---------------");
        System.out.println("Professor: " + this.nome);
        if (this.seminarios == null) return;
        System.out.println("=== Seminários cadastrados === ");
        for (Seminario seminario : seminarios){
            System.out.println(seminario.getTitulo());
            System.out.println(seminario.getLocal().getEndereco());
            if (seminario.getAlunos() == null) continue;
            System.out.println("=== Alunos ===");
            for (Aluno aluno : seminario.getAlunos()){
                System.out.println("Aluno: " + aluno.getNome());
                System.out.println("Idade: " + aluno.getIdade());

            }
        }

    }

    public Seminario[] getSeminarios() {
        return seminarios;
    }

    public void setSeminarios(Seminario[] seminarios) {
        this.seminarios = seminarios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}


public class ExercicioSeminario {
    public static void main(String[] args) {
        Local local = new Local("Rua das laranjeiras");
        Aluno aluno = new Aluno("Sandro", 56);
        ProfessorE professor = new ProfessorE("Paulão", "Matemática");
        Aluno[] alunosParaSeminario = {aluno};

        Seminario seminario = new Seminario("Onde aprender matemática", alunosParaSeminario, local);
        Seminario[] seminariosDisponiveis = {seminario};

        professor.setSeminarios(seminariosDisponiveis);
        professor.imprime();
    }
}
