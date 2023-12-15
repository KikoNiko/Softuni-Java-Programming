package vehicleShop.models.worker;

public class SecondShift extends BaseWorker {
    public SecondShift(String name) {
        super(name, 70);
    }

    private int strength = getStrength();
    @Override
    public void working() {
        if (strength - 15 < 0) {
            setStrength(0);
        } else {
            this.setStrength(strength - 15);
        }
    }
}
