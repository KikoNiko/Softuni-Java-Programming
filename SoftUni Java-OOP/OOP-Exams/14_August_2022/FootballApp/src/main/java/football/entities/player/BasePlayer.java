package football.entities.player;

import football.common.ExceptionMessages;

public abstract class BasePlayer implements Player {
    private String name;
    private String nationality;
    private double kilograms;
    private int strength;

    public BasePlayer(String name, String nationality, double kilograms, int strength) {
        this.setName(name);
        this.setNationality(nationality);
        this.kilograms = kilograms;
        this.setStrength(strength);
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(
                    ExceptionMessages.PLAYER_NAME_NULL_OR_EMPTY
            );
        }
        this.name = name;
    }

    public void setNationality(String nationality) {
        if (nationality == null || nationality.trim().isEmpty()) {
            throw new NullPointerException(
                    ExceptionMessages.PLAYER_NATIONALITY_NULL_OR_EMPTY
            );
        }
        this.nationality = nationality;
    }

    public void setStrength(int strength) {
        if (strength <= 0) {
            throw new IllegalArgumentException(
                    ExceptionMessages.PLAYER_STRENGTH_BELOW_OR_EQUAL_ZERO
            );
        }
        this.strength = strength;
    }

    @Override
    public double getKg() {
        return kilograms;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStrength() {
        return strength;
    }

}
