import java.util.ArrayList;
import java.util.List;

public class SmartPhoneListTest01 {
    static void main(String[] args) {
        SmartPhone s1 = new SmartPhone("1ABC1", "iPhone");
        SmartPhone s2 = new SmartPhone("22222", "Pixel");
        SmartPhone s3 = new SmartPhone("33333", "Samsung");
        List<SmartPhone> smartPhones = new ArrayList<>(6);

        smartPhones.add(s1);
        smartPhones.add(s2);
        smartPhones.add(0, s3);

        for (SmartPhone smartPhone : smartPhones){
            System.out.println(smartPhone);
        }

        SmartPhone s4 = new SmartPhone("22222", "Pixel");

        System.out.println(smartPhones.contains(s4));
        int indexSmartphone4 = smartPhones.indexOf(s4);
        System.out.println(smartPhones.get(indexSmartphone4));

    }
}
