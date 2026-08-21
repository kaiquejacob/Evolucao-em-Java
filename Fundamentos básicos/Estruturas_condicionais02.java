public class Estruturas_condicionais02 {
    static void main(String[] args) {
        // idade < 15 -> categoria infantil
        // idade >= 15 && < 18 -> categoria juvenil
        // idade >= 18 -> categoria adulto
        int idade = 15;

        if (idade < 15){
            System.out.println("Categoria infantil ");
        } else if (idade >= 15 && idade < 18) {
            System.out.println("Categoria juvenil");
        } else{
            System.out.println("Categoria adulto ");
        }
    }
}
