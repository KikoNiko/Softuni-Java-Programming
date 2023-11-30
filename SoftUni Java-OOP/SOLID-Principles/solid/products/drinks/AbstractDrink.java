package solid.products.drinks;

public abstract class AbstractDrink implements Drink {

    private double milliliters;
    private double caloriesPer100Grams;
    private double density;

    AbstractDrink(double milliliters, double caloriesPer100Grams, double density) {
        this.milliliters = milliliters;
        this.caloriesPer100Grams = caloriesPer100Grams;
        this.density = density;
    }

    public double getMilliliters() {
        return milliliters;
    }

    @Override
    public double amountOfDrink() {
        return milliliters / 1000 * density;
    }

    @Override
    public double amountOfCalories() {
        return caloriesPer100Grams / 100 * (getMilliliters() * density);
    }
}
