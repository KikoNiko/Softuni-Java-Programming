package harpoonDiver.core;

import harpoonDiver.models.diver.DeepWaterDiver;
import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.diver.OpenWaterDiver;
import harpoonDiver.models.diver.WreckDiver;
import harpoonDiver.models.diving.Diving;
import harpoonDiver.models.diving.DivingImpl;
import harpoonDiver.models.divingSite.DivingSite;
import harpoonDiver.models.divingSite.DivingSiteImpl;
import harpoonDiver.repositories.DiverRepository;
import harpoonDiver.repositories.DivingSiteRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static harpoonDiver.common.ConstantMessages.*;
import static harpoonDiver.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{

    private DiverRepository diverRepository;
    private DivingSiteRepository divingSiteRepository;
    private Diving diving;

    private int divingSitesCount = 0;

    public ControllerImpl() {
        this.diverRepository = new DiverRepository();
        this.divingSiteRepository = new DivingSiteRepository();
        this.diving = new DivingImpl();
    }

    @Override
    public String addDiver(String kind, String diverName) {
        Diver diver;
        switch (kind) {
            case "DeepWaterDiver":
                diver = new DeepWaterDiver(diverName);
                break;
            case "OpenWaterDiver":
                diver = new OpenWaterDiver(diverName);
                break;
            case "WreckDiver":
                diver = new WreckDiver(diverName);
                break;
            default:
                throw new IllegalArgumentException(DIVER_INVALID_KIND);
        }

        diverRepository.add(diver);
        return String.format(DIVER_ADDED, kind, diverName);
    }

    @Override
    public String addDivingSite(String siteName, String... seaCreatures) {
        DivingSite divingSite = new DivingSiteImpl(siteName);
        divingSite.getSeaCreatures().addAll(List.of(seaCreatures));
//        for (String seaCreature : seaCreatures) {
//            divingSite.getSeaCreatures().add(seaCreature);
//        }
        divingSiteRepository.add(divingSite);
        return String.format(DIVING_SITE_ADDED, siteName);
    }

    @Override
    public String removeDiver(String diverName) {
        Diver diver = diverRepository.byName(diverName);
        if (diver == null) {
            throw new IllegalArgumentException(String.format(DIVER_DOES_NOT_EXIST, diverName));
        }
        diverRepository.remove(diver);
        return String.format(DIVER_REMOVE, diverName);
    }

    @Override
    public String startDiving(String siteName) {
        List<Diver> divers = diverRepository.getCollection()
                .stream().filter(d -> d.getOxygen() > 30)
                .collect(Collectors.toList());

        if (divers.isEmpty()) {
            throw new IllegalArgumentException(SITE_DIVERS_DOES_NOT_EXISTS);
        }
        DivingSite divingSite = divingSiteRepository.byName(siteName);
        diving.searching(divingSite, divers);
        int removedDiversCount = (int) divers.stream().filter(d -> !d.canDive()).count();

        divingSitesCount++;
        return String.format(SITE_DIVING, siteName, removedDiversCount);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_DIVING_SITES, divingSitesCount)).append(System.lineSeparator());
        sb.append(FINAL_DIVERS_STATISTICS).append(System.lineSeparator());

        for (Diver diver : diverRepository.getCollection()) {
            Collection<String> seaCreatures = diver.getSeaCatch().getSeaCreatures();
            sb.append(String.format(FINAL_DIVER_NAME, diver.getName())).append(System.lineSeparator());
            sb.append(String.format(FINAL_DIVER_OXYGEN, diver.getOxygen())).append(System.lineSeparator());
            sb.append(String.format(
                    FINAL_DIVER_CATCH, seaCreatures.isEmpty()
                            ? "None"
                            : String.join(FINAL_DIVER_CATCH_DELIMITER, seaCreatures))
            ).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    //"The dive took place at {count} site/s.
    //Diver's statistics:
    //Name: {diverName}
    //Oxygen: {diverOxygen}
    //Diver's catch: {seaCreature1, seaCreature2, seaCreature3, …, seaCreaturen}
}
