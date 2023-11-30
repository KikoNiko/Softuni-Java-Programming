package solid.calculators;

import solid.products.Product;
import solid.products.drinks.Drink;
import solid.products.foods.Food;

import java.util.List;

public class QuantityCalculatorImpl implements Calculator {

    private QuantityCalculatorImpl() {
    }

    @Override
    public double sum(List<Product> products) {
        double sum = 0;
        for (Product product : products) {
            if (product instanceof Food) {
                sum += ((Food) product).amountOfFood();
            } else if (product instanceof Drink) {
                sum += ((Drink) product).amountOfDrink();
            }
        }
        return sum;
    }

    @Override
    public double average(List<Product> products) {
        return sum(products) / products.size();
    }
}
