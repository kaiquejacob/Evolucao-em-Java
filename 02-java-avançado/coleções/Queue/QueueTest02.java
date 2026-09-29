import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class MangaPrecoComparator2 implements Comparator<Manga> {

    @Override
    public int compare(Manga o1, Manga o2) {
        return Double.compare(o1.getPreco(), o2.getPreco());
    }
}

public class QueueTest02 {
    static void main(String[] args) {
        Queue<Manga> mangas = new PriorityQueue<>(new MangaPrecoComparator2().reversed());
        mangas.add(new Manga(5L,"Berserk",19.9, 5));
        mangas.add(new Manga(1L,"Naruto",9.5,0));
        mangas.add(new Manga(4L,"Pokemon",3.2, 0));
        mangas.add(new Manga(3L,"Atack on titan",11.20, 2));
        mangas.add(new Manga(2L,"Dragon ball Z",2.99, 0));
        mangas.add(new Manga(10L,"Aaragon",2.99, 0));

        while (!mangas.isEmpty()){
            System.out.println(mangas.poll());
        }

    }
}
