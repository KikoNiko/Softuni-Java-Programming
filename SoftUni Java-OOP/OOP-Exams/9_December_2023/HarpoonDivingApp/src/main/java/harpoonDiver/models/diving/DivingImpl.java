package harpoonDiver.models.diving;


import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.divingSite.DivingSite;

import java.util.Collection;
import java.util.List;

public class DivingImpl implements Diving {
    @Override
    public void searching(DivingSite divingSite, Collection<Diver> divers) {
        Collection<String> seaCreatures = divingSite.getSeaCreatures();
        for (Diver diver : divers) {
            while (diver.canDive()) {
                if (!seaCreatures.isEmpty()) {
                    String creature = seaCreatures.iterator().next();
                    diver.shoot();
                    diver.getSeaCatch().getSeaCreatures().add(creature);
                    seaCreatures.remove(creature);
                }
                if (seaCreatures.isEmpty()) return;
            }

        }
    }
    //⦁	Divers cannot dive on site if their oxygen is equal to or below 0.
    //⦁	They dive into the water and start searching the site for sea creatures one by one.
    //⦁	If they find a sea creature, they shoot it, and their oxygen is decreased.
    //⦁	They add the creature to their catch. The sea creature should then be removed from the diving site.
    //⦁	Divers cannot continue shooting if their oxygen drops to 0.
    //⦁	If their oxygen drops to 0, the next diver starts diving.
}
