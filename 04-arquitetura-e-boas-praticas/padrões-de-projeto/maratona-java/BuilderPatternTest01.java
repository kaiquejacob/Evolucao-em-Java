public class BuilderPatternTest01 {
    static void main(String[] args) {
        Person build = Person.PersonBuilder
                .builder()
                .firstName("William")
                .lastName("Suane")
                .userName("ViradoNoJiraya")
                .email("william.suane@devdojo.academy")
                .build();

        System.out.println(build);

    }
}
