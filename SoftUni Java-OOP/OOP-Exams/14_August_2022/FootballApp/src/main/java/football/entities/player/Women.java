package football.entities.player;

public class Women extends BasePlayer {
    public Women(String name, String nationality, int strength) {
        super(name, nationality, 60.00, strength);
    }

    @Override
    public void stimulation() {
        this.setStrength(this.getStrength() + 115);
    }

    //I can only play on ArtificialTurf!
}
