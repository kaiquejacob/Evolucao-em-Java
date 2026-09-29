public class OuterClassesTest03 {
    private String name = "Willaim";
    static class Nestd{
        private String lastName = "Suane";

        void print(){
            System.out.println(new OuterClassesTest03().name + " " + lastName);
        }
    }

    static void main(String[] args) {
        Nestd nestd = new Nestd();
        nestd.print();
    }
}
