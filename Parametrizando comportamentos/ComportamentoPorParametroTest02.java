import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ComportamentoPorParametroTest02 {
    private static List<Car> cars = List.of(new Car("green", 2011), new Car("black", 1998), new Car("red", 2019));

    static void main(String[] args) {
//        List<Car> greenCars = filter(cars, new CarPredicate() {
//            @Override
//            public boolean test(Car car) {
//                return car.getColor().equals("green");
//            }
//        });

        List<Car> greenCar = filter(cars, car -> car.getColor().equals("green"));
        List<Car> redCar = filter(cars, car -> car.getColor().equals("red"));
        List<Car> yearBeforeCar = filter(cars, car -> car.getYear() < (2015));

        System.out.println(greenCar);
        System.out.println(redCar);
        System.out.println(yearBeforeCar);

    }

    private static List<Car> filter(List<Car> cars, Predicate<Car> carPredicate) {
        List<Car> filterCar = new ArrayList<>();
        for (Car car : cars) {
            if (carPredicate.test(car)) {
                filterCar.add(car);
            }
        }
        return filterCar;
    }



}
