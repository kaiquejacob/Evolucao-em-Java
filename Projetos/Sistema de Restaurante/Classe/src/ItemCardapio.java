public abstract class ItemCardapio {
    protected String nome;
    protected double preco;
    protected CategoriaItem categoria;

    public ItemCardapio(String nome, double preco, CategoriaItem categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return
                "🍽️ " + nome +
                        "  |  R$" + String.format("%.2f", preco) +
                        "  |  " + categoria.nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public CategoriaItem getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaItem categoria) {
        this.categoria = categoria;
    }
}
