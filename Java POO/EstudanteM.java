public class EstudanteM {

    public String nome;
    public int idade;
    public char sexo;

    public void imprimi(){
        System.out.println("-----------");
        System.out.println(this.nome);            //this = esse ->  (esse nome)
        System.out.println(this.idade);           // (essa idade)
        System.out.println(this.sexo);            // (esse sexo)
    }
}
