package barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

public class RetireCommand extends Command {
    public RetireCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        String unitType = this.getData()[1];

        String res = unitType + " retired!";

        try {
            this.getRepository().removeUnit(unitType);
        } catch (IllegalArgumentException ex) {
            return ex.getMessage();
        }

        return res;
    }
}
