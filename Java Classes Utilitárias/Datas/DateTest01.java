import java.util.Date;

public class DateTest01 {
    static void main(String[] args) {
        Date date = new Date();         // long 100000
        date.setTime(date.getTime() + 3_600_000);
        System.out.println(date);

    }
}
