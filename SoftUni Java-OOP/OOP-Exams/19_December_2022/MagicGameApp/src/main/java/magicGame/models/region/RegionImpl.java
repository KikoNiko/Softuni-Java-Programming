package magicGame.models.region;

import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RegionImpl implements Region {
    @Override
    public String start(Collection<Magician> magicians) {
        List<Magician> wizards = magicians.stream()
                .filter(m -> m.getClass().getSimpleName().equals("Wizard"))
                .collect(Collectors.toList());
        List<Magician> blackWidows = magicians.stream()
                .filter(m -> m.getClass().getSimpleName().equals("BlackWidow"))
                .collect(Collectors.toList());

        while (!wizards.isEmpty() && !blackWidows.isEmpty()) {
            Wizard wizard = (Wizard) wizards.get(0);
            BlackWidow blackWidow = (BlackWidow) blackWidows.get(0);

            blackWidow.takeDamage(wizard.getMagic().fire());
            if (blackWidow.isAlive()) {
                wizard.takeDamage(blackWidow.getMagic().fire());
                if (!wizard.isAlive()) {
                    wizards.remove(wizard);
                }
            } else {
                blackWidows.remove(blackWidow);
            }

        }
        if (wizards.size() > blackWidows.size()) {
            return "Wizards win!";
        }
        return "Black widows win!";
    }

}
