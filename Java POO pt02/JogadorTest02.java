public class JogadorTest02 {
    static void main(String[] args) {
        Jogador jogador1 = new Jogador("Pelé");
        Time time = new Time("Santos");

        jogador1.setTime(time);

        jogador1.imprimi();
    }
}
