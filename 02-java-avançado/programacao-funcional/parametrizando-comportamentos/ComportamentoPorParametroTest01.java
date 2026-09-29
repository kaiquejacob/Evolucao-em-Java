import java.util.ArrayList;
import java.util.List;

public class ComportamentoPorParametroTest01 {
    private static List<Car> cars = List.of(new Car("green", 2011), new Car("black", 1998), new Car("red", 2019));

    static void main(String[] args) {
        System.out.println(filterGreenCar(cars));
        System.out.println(filterRedCar(cars));

        System.out.println(filterCarByColor(cars, "green"));
        System.out.println(filterCarByColor(cars, "red"));

        System.out.println("-------------");

        System.out.println(filterByYearBefore(cars, 2015));

    }

    private static List<Car> filterGreenCar(List<Car> cars) {
        List<Car> greenCar = new ArrayList<>();
        for (Car car : cars) {
            if (car.getColor().equals("green")) {
                greenCar.add(car);
            }
        }
        return greenCar;
    }

    private static List<Car> filterRedCar(List<Car> cars) {
        List<Car> redCar = new ArrayList<>();
        for (Car car : cars) {
            if (car.getColor().equals("red")) {
                redCar.add(car);
            }
        }
        return redCar;
    }

    private static List<Car> filterCarByColor(List<Car> cars, String color) {
        List<Car> colorCar = new ArrayList<>();
        for (Car car : cars) {
            if (car.getColor().equals(color)) {
                colorCar.add(car);
            }
        }
        return colorCar;
    }

    private static List<Car> filterByYearBefore(List<Car> cars, int year) {
        List<Car> filterCar = new ArrayList<>();
        for (Car car : cars) {
            if (car.getYear() < year) {
                filterCar.add(car);
            }
        }
        return filterCar;
    }

}
