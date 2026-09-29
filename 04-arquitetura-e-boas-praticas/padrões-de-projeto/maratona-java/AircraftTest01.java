public class AircraftTest01 {
    static void main(String[] args) {
        bookSeat("1A");
        bookSeat("1A");

    }

    static void bookSeat(String seat) {
        System.out.println(AircraftSingletonEager.getINSTANCE());
//        Aircraft aircraft = new Aircraft("787-900");
//        System.out.println(aircraft.bookSeat(seat));
    }
}
