import java.util.ArrayList;
import java.util.List;

// Reference to an instance method of a particular object
public class MethodReferenceTest02 {
    static void main(String[] args) {
        AnimeComparators animeComparators = new AnimeComparators();
        List<Anime> animeList = new ArrayList<>(List.of(new Anime("Berzerk", 43), new Anime("One Piece", 900), new Anime("Naruto", 500)));
        animeList.sort(animeComparators :: compareByEpisodesNonStatic);
        animeList.sort((a1, a2) -> animeComparators.compareByEpisodesNonStatic(a1, a2));
        System.out.println(animeList);

    }
}
