public class Desenho {
    private String nome;
    private static int[] episodios;

    // 0 - Bloco de inicialização é executado quando o JVM carregar classe
    // 1 - Alocado espaço em memória pro objeto
    // 2 - Cada atributo de classe é criado e inicializado com valores default ou o quer que for passada
    // 3 - Bloco de inicialização é executado
    // 4 - Construtor é executado


    // BLOCO DE INICIALIZAÇÃO STATIC
    static {
        System.out.println("Dentro do bloco de inicialização estático");
        episodios = new int[100];
        for (int i = 0; i < episodios.length; i++) {
            episodios[i] = i + 1;
        }
    }
    static {
        System.out.println("Dentro do bloco de inicialização estático 2");
    }
    static {
        System.out.println("Dentro do bloco de inicialização estático 3");
    }
    {
        System.out.println("\nNão estático");
    }

    public Desenho(String nome) {
        this.nome = nome;
    }

    public Desenho(){
        for (int episodio : Desenho.episodios){
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


