package football.core;


import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private SupplementRepository supplementRepository;
    private Map<String, Field> fields;

    public ControllerImpl() {
        this.supplementRepository = new SupplementRepositoryImpl();
        this.fields = new LinkedHashMap<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field = null;
        switch (fieldType) {
            case "ArtificialTurf":
                field = new ArtificialTurf(fieldName);
                break;
            case "NaturalGrass":
                field = new NaturalGrass(fieldName);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
        }

        this.fields.put(fieldName, field);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement supplement = null;
        switch (type) {
            case "Powdered":
                supplement = new Powdered();
                break;
            case "Liquid":
                supplement = new Liquid();
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }

        this.supplementRepository.add(supplement);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Supplement supplement = supplementRepository.findByType(supplementType);
        if (supplement == null) {
            throw new IllegalArgumentException(String.format(
                    ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType)
            );
        }

        this.fields.get(fieldName).addSupplement(supplement);
        supplementRepository.remove(supplement);
        return String.format(
                ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName
        );
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Player player = null;
        switch (playerType) {
            case "Men":
                player = new Men(playerName, nationality, strength);
                break;
            case "Women":
                player = new Women(playerName, nationality, strength);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }

        Field field = this.fields.get(fieldName);
        if (!playerCanPlayOnField(playerType, field.getClass().getSimpleName())) {
            return ConstantMessages.FIELD_NOT_SUITABLE;
        }
        field.addPlayer(player);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
    }

    private boolean playerCanPlayOnField(String playerType, String fieldType) {
        if (playerType.equals("Men") && fieldType.equals("NaturalGrass")) {
            return true;
        } else if (playerType.equals("Women") && fieldType.equals("ArtificialTurf")) {
            return true;
        }
        return false;
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = this.fields.get(fieldName);
        field.drag();

        return String.format(ConstantMessages.PLAYER_DRAG, field.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = this.fields.get(fieldName);
        int strengthValue = field.getPlayers().stream()
                .mapToInt(Player::getStrength)
                .sum();
        return String.format(ConstantMessages.STRENGTH_FIELD, fieldName, strengthValue);
    }

    @Override
    public String getStatistics() {
        return this.fields.values()
                .stream()
                .map(Field::getInfo)
                .collect(Collectors.joining(System.lineSeparator())).trim();
    }
}
