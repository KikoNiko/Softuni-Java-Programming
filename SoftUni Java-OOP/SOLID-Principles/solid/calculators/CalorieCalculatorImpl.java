package solid.calculators;

import solid.products.Product;

import java.util.List;

public class CalorieCalculatorImpl implements Calculator {

    private CalorieCalculatorImpl() {
    }

    @Override
    public double sum(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::amountOfCalories)
                .sum();
    }

    @Override
    public double average(List<Product> products) {
        return sum(products) / products.size();
    }
}
