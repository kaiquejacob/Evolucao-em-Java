// 0 - Bloco de inicialização estático da super classe é executado quando o JVM carregar classe pai
// 1 - Bloco de inicialização estático da sub classe é executado quando o JVM carregar classe filha
// 2 - Alocado espaço em memória pro objeto da super classe
// 3 - Cada atributo de super classe é criado e inicializado com valores default ou o quer que for passada
// 4 - Bloco de inicialização da super classe é executado na ordem que aparece
// 5 - Construtor da super classe é executado
// 6 - Alocado espaço em memória pro objeto da sub classe
// 7 - Cada atributo de sub classe é criado e inicializado com valores default ou o quer que for passada
// 8 - Bloco de inicialização da sub classe é executado na ordem que aparece
// 9 - Construtor da sub classe é executado

public class HerancaTest02 {
    static void main(String[] args) {
        Funcionario funcionario = new Funcionario("Sandro");

    }
}
