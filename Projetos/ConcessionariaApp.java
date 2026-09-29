import java.util.Scanner;
import java.util.ArrayList;

class Carro {
    private String nome;
    private String modelo;
    private int ano;
    private double preco;

    public Carro() {}                            // construtor vazio necessário para new Carro() funcionar


    public Carro(String nome, String modelo, int ano, double preco) {
        this.nome = nome;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
    }

    // GET
    public String getNome() {
        return nome;
    }
    public String getModelo() {
        return modelo;
    }
    public int getAno() {
        return ano;
    }
    public double getPreco() {
        return preco;
    }

    //SET
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
}

class Concessionaria {

    private ArrayList<Carro> carros;


    public Concessionaria(ArrayList<Carro> carros) {             // recebe o array pelo construtor

        this.carros = carros;

    }


    public void exibirTodos() {
        System.out.println("\n===== CARROS DA CONCESSIONARIA =====");
        for (Carro carro : carros) {
            System.out.println("------------------------------------");
            System.out.println("Nome:   " + carro.getNome());
            System.out.println("Modelo: " + carro.getModelo());
            System.out.println("Ano:    " + carro.getAno());
            System.out.println("Preço:  R$ " + carro.getPreco());
        }
        System.out.println("====================================");
    }

    public void buscarPorNome(String nomeBuscado) {
        System.out.println("\n===== BUSCANDO: " + nomeBuscado + " =====");
        boolean encontrou = false;
        for (Carro carro : carros) {
            if (carro.getNome().equalsIgnoreCase(nomeBuscado)) {   // equalsIgnoreCase -> ignora letra maiuscula e minuscula
                System.out.println("Carro encontrado!");
                System.out.println("Nome:   " + carro.getNome());
                System.out.println("Modelo: " + carro.getModelo());
                System.out.println("Ano:    " + carro.getAno());
                System.out.println("Preço:  R$ " + carro.getPreco());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum carro encontrado com o nome: " + nomeBuscado);
        }
    }

    public void calcularPrecoMedio() {
        System.out.println("\n===== PREÇO MÉDIO DA FROTA =====");
        double soma = 0;
        for (Carro carro : carros) {
            soma += carro.getPreco();
        }
        double media = soma / carros.size();                   // .size() no lugar de .length para ArrayList
        System.out.printf("Preço médio: R$ %.2f%n", media);
    }

    public void removerCarro(String carroRemovido){
        System.out.println("\n===== REMOVENDO: " +  carroRemovido + " =====");
        boolean encontrou = false;
        for (Carro carro : carros) {
            if (carro.getNome().equalsIgnoreCase(carroRemovido)) {
                carros.remove(carro);
                System.out.println("Carro removido com sucesso!");
                encontrou = true;
                break;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum carro encontrado com o nome: " + carroRemovido);
        }

    }
}

public class ConcessionariaApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("====== CONCESSIONÁRIA ======");

        System.out.println("\nQuantos carros deseja cadastrar ?");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Carro> carros = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {
            Carro carro = new Carro();

            System.out.println("\n--- Cadastro do carro " + (i + 1) + " ---");

            System.out.print("Nome: ");
            carro.setNome(scanner.nextLine());

            System.out.print("Modelo: ");
            carro.setModelo(scanner.nextLine());

            System.out.print("Ano: ");
            carro.setAno(scanner.nextInt());

            System.out.print("Preço: ");
            carro.setPreco(scanner.nextDouble());
            scanner.nextLine();

            carros.add(carro);                // .add() no lugar de carros[i] = carro -> ArrayList
        }

        Concessionaria concessionaria = new Concessionaria(carros);

        int opcao = 0;
        while (opcao != 5) {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Exibir todos os carros");
            System.out.println("2 - Buscar por nome");
            System.out.println("3 - Calcular preço médio");
            System.out.println("4 - Remover carro");
            System.out.println("5 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1:
                    concessionaria.exibirTodos();
                    break;

                case 2:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    concessionaria.buscarPorNome(nome);
                    break;

                case 3:
                    concessionaria.calcularPrecoMedio();
                    break;

                case 4:
                    System.out.println("Digite o nome do carro a remover: ");
                    String nomeRemover = scanner.nextLine();
                    concessionaria.removerCarro(nomeRemover);
                    break;

                case 5:
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        }

        System.out.println("Encerrando...");
        scanner.close();
    }
}
