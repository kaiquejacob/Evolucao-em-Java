import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Reference to a static method
public class MethodReferenceTest01 {
    static void main(String[] args) {
        List<Anime> animeList = new ArrayList<>(List.of(new Anime("Berzerk", 43), new Anime("One Piece", 900), new Anime("Naruto", 500)));
//        Collections.sort(animeList, (a1, a2) -> a1.getTitle().compareTo(a2.getTitle()));
//        Collections.sort(animeList, AnimeComparators::compareByTitle);
        Collections.sort(animeList, AnimeComparators::compareByEpisodes);
        System.out.println(animeList);

    }
}
