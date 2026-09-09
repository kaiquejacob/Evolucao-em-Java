public class EqualsTest01 {
    static void main(String[] args) {
        SmartPhone s1 = new SmartPhone("1ABC1", "iPhone");
        SmartPhone s2 = new SmartPhone("1ABC1", "iPhone");
        System.out.println(s1.equals(s2));

    }
}
