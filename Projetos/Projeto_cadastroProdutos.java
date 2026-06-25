import java.util.Scanner;
import java.util.Locale;

/*
Cadastro de produtos simples Array de produtos (nome, preço, quantidade),
menu para listar, buscar por nome, mostrar o mais caro/barato. Me ajude a começar este projeto
 */
class Produto {

    private String nome;
    private double preco;
    private int estoque;

    public Produto(String nome, double preco, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }
}


class CadastroProdutos {
    private int quantidade;
    private Produto[] produtos;

    public CadastroProdutos(int tamanhoMaximo) {
        this.produtos = new Produto[tamanhoMaximo];
        this.quantidade = 0;
    }

    public void cadastrar(String nome, double preco, int estoque) {
        if (quantidade < produtos.length) {
            produtos[quantidade] = new Produto(nome, preco, estoque);
            quantidade++;
        } else {
            System.out.println("Cadastro cheio!");
        }
    }

    public void listarProdutos() {
        for (int i = 0; i < quantidade; i++) {
            System.out.println("\nNome: " + produtos[i].getNome());
            System.out.printf("Preço: R$ %.2f%n", produtos[i].getPreco());
            System.out.println("Estoque: " + produtos[i].getEstoque());
        }
    }

    public void buscarPorNome(String busca) {
        boolean encontrado = false;

        for (int i = 0; i < quantidade; i++) {
            if (produtos[i].getNome().equalsIgnoreCase(busca)) {
                System.out.println("\nProduto encontrado!");
                System.out.println("\nNome: " + produtos[i].getNome());
                System.out.printf("Preço: R$ %.2f%n", produtos[i].getPreco());
                System.out.println("Estoque: " + produtos[i].getEstoque());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Produto não encontrado!");
        }
    }

    public void maiorMenorPreco() {
        int indiceMaior = 0;
        int indiceMenor = 0;
        for (int i = 1; i < quantidade; i++) {
            if (produtos[i].getPreco() > produtos[indiceMaior].getPreco()) {
                indiceMaior = i;
            }
            if (produtos[i].getPreco() < produtos[indiceMenor].getPreco()) {
                indiceMenor = i;
            }
        }
        System.out.println("\n--- MAIOR PREÇO ---");
        System.out.println("Nome: " + produtos[indiceMaior].getNome());
        System.out.printf("Preço: R$ %.2f%n", produtos[indiceMaior].getPreco());

        System.out.println("\n--- MENOR PREÇO ---");
        System.out.println("Nome: " + produtos[indiceMenor].getNome());
        System.out.printf("Preço: R$ %.2f%n", produtos[indiceMenor].getPreco());
    }
}

public class Projeto_cadastroProdutos {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Quantos produtos deseja cadastrar? ");
        int tamanho = scanner.nextInt();
        scanner.nextLine();

        CadastroProdutos cadastro = new CadastroProdutos(tamanho);

        // Cadastro inicial
        for (int i = 0; i < tamanho; i++) {
            System.out.println("\n--- Produto " + (i + 1) + " ---");

            System.out.println("Nome: ");
            String nome = scanner.nextLine();

            System.out.println("Preço: ");
            double preco = scanner.nextDouble();

            System.out.println("Quantidade em estoque: ");
            int estoque = scanner.nextInt();
            scanner.nextLine();

            cadastro.cadastrar(nome, preco, estoque);  // passa os dados pro método
        }
        System.out.println("Produtos cadastrados com sucesso!");

        // Menu
        int opcao = 0;
        while (opcao != 4) {
            System.out.println("\n==== MENU ====");
            System.out.println("1 - Listar produtos");
            System.out.println("2 - Buscar por nome");
            System.out.println("3 - Maior e menor preço");
            System.out.println("4 - Sair");
            System.out.println("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastro.listarProdutos();
                    break;
                case 2:
                    System.out.println("Digite o nome do produto: ");
                    String busca = scanner.nextLine();
                    cadastro.buscarPorNome(busca);   // lê no main, passa pro método
                    break;
                case 3:
                    cadastro.maiorMenorPreco();
                    break;
                case 4:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}

