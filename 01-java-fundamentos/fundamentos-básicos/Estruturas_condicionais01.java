public class Estruturas_condicionais01 {
    static void main(String[] args) {
        // IF
        int idade = 10;

        if (idade >= 18){
            System.out.println("Autorizado ");
        }
        if (idade >= 18 == false){
            System.out.println("Não autorizado ");
        }

        // IF e ELSE
        if (idade >= 18){
            System.out.println("Autorizado ");
        }
        else {
            System.out.println("Não autorizado ");
        }

    }
}
