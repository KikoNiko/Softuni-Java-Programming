package carShop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CarShopTests {

    private final String CAR_MODEL = "TEST";
    private final int HORSEPOWER = 50;
    private final double PRICE = 1000;

    private Car car;
    private CarShop carShop;

    @Before
    public void setUp() {
        car = new Car(CAR_MODEL, HORSEPOWER, PRICE);
        carShop = new CarShop();
    }

    @Test
    public void test_CreateCarShop() {
        CarShop carShop = new CarShop();
        assertEquals(new ArrayList<>(), carShop.getCars());
    }

    @Test
    public void test_EmptyCarList() {
        assertEquals(0, carShop.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_AddCar_WithNullValue_ShouldThrow() {
        carShop.add(null);
    }

    @Test
    public void test_AddCar_ShouldCorrectlyIncrease_CarCount() {
        carShop.add(car);
        assertEquals(1, carShop.getCount());
    }

    @Test
    public void test_RemoveCar_ShouldCorrectlyDecrease_CarCount() {
        carShop.add(car);
        assertTrue(carShop.remove(car));
        assertEquals(0, carShop.getCars().size());
    }

    @Test
    public void test_RemoveCar_WhenListEmpty() {
        assertFalse(carShop.remove(car));
    }

    @Test
    public void test_findAllCarsWithMaxHorsePower() {
        carShop.add(new Car("Toyota", 90, 10000));
        carShop.add(new Car("Ford", 80, 6000));
        carShop.add(car);
        List<Car> list = carShop.findAllCarsWithMaxHorsePower(HORSEPOWER);
        assertFalse(list.contains(car));
        assertEquals(2, list.size());
    }

    @Test
    public void test_getTheMostLuxuryCar() {
        carShop.add(new Car("Toyota", 90, 10000));
        carShop.add(new Car("Ford", 80, 6000));
        carShop.add(car);
        Car mostExpensive = carShop.getTheMostLuxuryCar();
        assertEquals("Toyota", mostExpensive.getModel());
    }

    @Test
    public void test_findAllCarByModel() {
        carShop.add(new Car("Toyota", 90, 10000));
        carShop.add(new Car("Ford", 80, 6000));
        carShop.add(new Car(CAR_MODEL, 70, 3000));
        carShop.add(car);
        List<Car> allCarByModel = carShop.findAllCarByModel(CAR_MODEL);
        assertEquals(2, allCarByModel.size());
    }

    @Test
    public void test_findAllCarByModel_2() {
        carShop.add(new Car("Toyota", 90, 10000));
        carShop.add(new Car("Ford", 80, 6000));
        carShop.add(new Car(CAR_MODEL, 70, 3000));
        carShop.add(car);
        List<Car> allCarByModel = carShop.findAllCarByModel(CAR_MODEL);
        Car car1 = allCarByModel.get(0);
        Car car2 = allCarByModel.get(1);
        assertEquals(car2.getModel(), car1.getModel());
        assertEquals(CAR_MODEL, car1.getModel());
    }

}

