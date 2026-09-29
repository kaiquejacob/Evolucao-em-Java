public class Pedido implements Pagavel {
    private int numeroMesa;
    private ItemCardapio[] itens;
    private int quantidadeItens;
    private StatusPedido status;

    public Pedido(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        this.itens = new ItemCardapio[10];
        this.quantidadeItens = 0;
        this.status = StatusPedido.ABERTO;
    }

    public void adicionarItem(ItemCardapio item) {
        itens[quantidadeItens] = item;
        quantidadeItens++;
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < quantidadeItens; i++) {
            total += itens[i].getPreco();
        }
        return total;
    }

    @Override
    public String toString() {
        return
                "\n📍 Mesa " + numeroMesa +
                        "\n🔄 Status: " + status +
                        "\n💰 Total: R$" + String.format("%.2f", calcularTotal()) + "\n";
    }

    public int getNumeroMesa() { return numeroMesa; }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
    public ItemCardapio[] getItens() { return itens; }
    public int getQuantidadeItens() { return quantidadeItens; }
}