public class Restaurante {
    private String nome;
    private Pedido[] pedidos;
    private int quantidadePedidos;

    public Restaurante(String nome) {
        this.nome = nome;
        this.pedidos = new Pedido[10];
        this.quantidadePedidos = 0;
    }

    private ItemCardapio[] cardapio = {
            new Prato("Frango Grelhado", 35.0),
            new Prato("Massa Bolonhesa", 40.0),
            new Prato("Filé Mignon a milanesa", 50),
            new Bebida("Cerveja", 9),
            new Bebida("Suco de Laranja", 10.0),
            new Bebida("Refrigerante", 8.0),
            new Sobremesa("Bolo de chocolate", 16),
            new Sobremesa("Pudim", 15.0),
            new Sobremesa("Sorvete", 12.0)
    };

    public void abrirPedido(int numeroMesa) {
        pedidos[quantidadePedidos] = new Pedido(numeroMesa);
        quantidadePedidos++;
    }

    public Pedido buscarPedido(int numeroMesa) {
        for (int i = 0; i < quantidadePedidos; i++) {
            if (pedidos[i].getNumeroMesa() == numeroMesa) {
                return pedidos[i];
            }
        }
        return null;
    }

    public ItemCardapio[] getCardapio() {
        return cardapio;
    }
}
