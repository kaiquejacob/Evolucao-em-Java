public class SerieTest01 {
    static void main(String[] args) {
        Serie serie = new Serie();

        for (int episodio : serie.getEpisodios()){
            System.out.print(episodio + "  ");
        }
    }
}
