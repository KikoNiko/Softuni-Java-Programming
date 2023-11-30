package solid.products.foods;

public abstract class AbstractFood implements Food {
    private double grams;
    private double caloriesPer100Grams;

    AbstractFood(double grams, double caloriesPer100Grams) {
        this.grams = grams;
        this.caloriesPer100Grams = caloriesPer100Grams;
    }

    public double getGrams() {
        return grams;
    }

    @Override
    public double amountOfFood() {
        return grams / 1000;
    }

    @Override
    public double amountOfCalories() {
        return caloriesPer100Grams / 100 * getGrams();
    }

}
