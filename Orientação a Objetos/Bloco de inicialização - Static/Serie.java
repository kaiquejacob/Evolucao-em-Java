public class Serie {
    private String nome;
    private int[] episodios;
    // 1 - Alocado espaço em memória pro objeto
    // 2 - Cada atributo de classe é criado e inicializado com valores default ou o quer que for passada
    // 3 - Bloco de inicialização é executado
    // 4 - Construtor é executado


    // BLOCO DE INICIALIZAÇÃO
    {
        System.out.println("Dentro do bloco de inicialização");
        episodios = new int[100];
        for (int i = 0; i < episodios.length; i++) {
            episodios[i] = i + 1;
        }
    }

    public Serie(String nome) {
        this.nome = nome;
    }

    public Serie(){
        for (int episodio : this.episodios){
            System.out.print(episodio + "  ");
        }
    }

    public String getNome() {
        return nome;
    }

    public int[] getEpisodios() {
        return episodios;
    }
}
