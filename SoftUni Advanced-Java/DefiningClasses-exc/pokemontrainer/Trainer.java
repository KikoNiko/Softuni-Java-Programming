package pokemontrainer;

import java.util.List;

public class Trainer {

    private String name;
    private int numberOfBadges;
    private List<Pokemon> pokemonList;

    public Trainer(String name, List<Pokemon> pokemonList) {
        this.name = name;
        this.numberOfBadges = 0;
        this.pokemonList = pokemonList;
    }

    public int getNumberOfBadges() {
        return numberOfBadges;
    }

    public void checkPokemon(String element) {
        if (isExist(element)) {
            numberOfBadges += 1;
        } else {
            for (int i = 0; i < pokemonList.size(); i++) {
                Pokemon currentPokemon = pokemonList.get(i);
                currentPokemon.setHealth(currentPokemon.getHealth() - 10);
                if (currentPokemon.getHealth() <= 0) {
                    pokemonList.remove(currentPokemon);
                    i--;
                }
            }
        }
    }

    public boolean isExist(String element) {
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getElement().equals(element)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public String toString() {
        return String.format("%s %d %d", name, numberOfBadges, pokemonList.size());
    }
}
