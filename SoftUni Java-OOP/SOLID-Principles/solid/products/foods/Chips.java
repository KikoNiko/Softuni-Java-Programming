package solid.products.foods;


public class Chips extends AbstractFood {

    public static final double CALORIES_PER_100_GRAMS = 529.0;

    public Chips(double grams) {
        super(grams, CALORIES_PER_100_GRAMS);
    }
}
