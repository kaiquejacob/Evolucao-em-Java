import java.util.Scanner;

public class mainR {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurante restaurante = new Restaurante("Restaurante");

        int opcao = 0;
        while (opcao != 6) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║   🍽️  RESTAURANTE - MENU      ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1 - Abrir pedido              ║");
            System.out.println("║ 2 - Adicionar item ao pedido  ║");
            System.out.println("║ 3 - Atualizar status          ║");
            System.out.println("║ 4 - Ver pedido da mesa        ║");
            System.out.println("║ 5 - Fechar e pagar pedido     ║");
            System.out.println("║ 6 - Sair                      ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- 🆕 Abrir Pedido ---");
                    System.out.print("Número da mesa: ");
                    int numeroMesa = scanner.nextInt();
                    restaurante.abrirPedido(numeroMesa);

                    System.out.println("Pedido iniciado com sucesso! ");
                    break;

                case 2:
                    System.out.println("\n--- 🍴 Adicionar Item ---");
                    System.out.print("Número da mesa: ");
                    numeroMesa = scanner.nextInt();
                    Pedido pedido = restaurante.buscarPedido(numeroMesa);
                    if (pedido == null) {
                        System.out.println("Mesa não encontrada! ");
                        break;
                    }
                    ItemCardapio[] cardapio = restaurante.getCardapio();
                    System.out.println("\nCardápio:");
                    for (int i = 0; i < cardapio.length; i++) {
                        System.out.println(i + " - " + cardapio[i]);
                    }
                    System.out.print("Escolha um item: ");
                    int escolha = scanner.nextInt();

                    pedido.adicionarItem(cardapio[escolha]);
                    System.out.println("Item adicionado! ");

                    break;

                case 3:
                    System.out.println("\n--- 🔄 Atualizar Status ---");
                    System.out.print("Número da mesa: ");
                    numeroMesa = scanner.nextInt();

                    pedido = restaurante.buscarPedido(numeroMesa);
                    if (pedido == null) {
                        System.out.println("Mesa não encontrada! ");
                        break;
                    }
                    StatusPedido[] status = StatusPedido.values();
                    for (int i = 0; i < status.length; i++) {
                        System.out.println(i + " - " + status[i]);
                    }
                    System.out.print("Escolha o novo status: ");
                    int escolhaStatus = scanner.nextInt();

                    pedido.setStatus(status[escolhaStatus]);
                    System.out.println("Status atualizado! ");
                    break;

                case 4:
                    System.out.println("\n--- 📋 Ver Pedido ---");
                    System.out.print("Número da mesa: ");
                    numeroMesa = scanner.nextInt();
                    pedido = restaurante.buscarPedido(numeroMesa);
                    if (pedido == null) {
                        System.out.println("Mesa não encontrada! ");
                        break;
                    }
                    System.out.println(pedido);
                    break;

                case 5:
                    System.out.println("\n--- 💳 Fechar e Pagar ---");
                    System.out.print("Número da mesa: ");
                    numeroMesa = scanner.nextInt();
                    pedido = restaurante.buscarPedido(numeroMesa);
                    if (pedido == null) {
                        System.out.println("Mesa não encontrada! ");
                        break;
                    }
                    System.out.println("💰 Total: R$" + String.format("%.2f", pedido.calcularTotal()));

                    pedido.setStatus(StatusPedido.ENTREGUE);
                    System.out.println("Pedido encerrado! ");
                    break;

                case 6:
                    break;
                default:
                    System.out.println("Opção inválida! ");
            }
        }

        System.out.println("Encerrando...");
        scanner.close();

    }
}
