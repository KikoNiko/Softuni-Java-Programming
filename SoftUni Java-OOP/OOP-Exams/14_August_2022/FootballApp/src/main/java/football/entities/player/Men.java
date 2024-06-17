package football.entities.player;

public class Men extends BasePlayer {
    public Men(String name, String nationality, int strength) {
        super(name, nationality, 85.50, strength);
    }

    @Override
    public void stimulation() {
        this.setStrength(this.getStrength() + 145);
    }

    //I can only play on NaturalGrass!
}