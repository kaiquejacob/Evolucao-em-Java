public class JogadorTest03 {
    static void main(String[] args) {
        Jogador jogador = new Jogador("Cafú");
        Time time = new Time("Brasil");
        Jogador[] jogadores = {jogador};

        jogador.setTime(time);

        time.setJogadores(jogadores);

        System.out.println("--- Jogador ---");
        jogador.imprimi();

        System.out.println("--- Time ---");
        time.imprimi();
    }
}
