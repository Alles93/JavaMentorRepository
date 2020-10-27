package web.service;

import web.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {
    @Override
    public List<Car> listCars(int countCars) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Tesla",3));
        cars.add(new Car("Audi",5));
        cars.add(new Car("Nissan",2));
        cars.add(new Car("Hummer",6));
        cars.add(new Car("Pejo",10));

        return countCars > 5 ? cars : cars.subList(0,countCars);
    }
}
