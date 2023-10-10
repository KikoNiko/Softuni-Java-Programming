package google;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Person> peopleMap = new HashMap<>();

        String data = scanner.nextLine();
        while (!data.equals("End")) {
            String[] tokens = data.split("\\s+");
            String personName = tokens[0];

            if (!peopleMap.containsKey(personName)) {
                peopleMap.put(personName, new Person());
            }
            switch (tokens[1]) {
                case "company":
                    String companyName = tokens[2];
                    String department = tokens[3];
                    double salary = Double.parseDouble(tokens[4]);
                    Company company = new Company(companyName, department, salary);
                    peopleMap.get(personName).setCompany(company);
                    break;
                case "pokemon":
                    String pokemonName = tokens[2];
                    String pokemonType = tokens[3];
                    Pokemon pokemon = new Pokemon(pokemonName, pokemonType);
                    peopleMap.get(personName).getPokemons().add(pokemon);
                    break;
                case "parents":
                    String parentName = tokens[2];
                    String parentBirthday = tokens[3];
                    Parent parent = new Parent(parentName, parentBirthday);
                    peopleMap.get(personName).getParents().add(parent);
                    break;
                case "children":
                    String childName = tokens[2];
                    String childBirthday = tokens[3];
                    Child child = new Child(childName, childBirthday);
                    peopleMap.get(personName).getChildren().add(child);
                    break;
                case "car":
                    String carModel = tokens[2];
                    int carSpeed = Integer.parseInt(tokens[3]);
                    Car car =  new Car(carModel, carSpeed);
                    peopleMap.get(personName).setCar(car);
                    break;
            }

            data = scanner.nextLine();
        }

        String searchedPerson = scanner.nextLine();
        System.out.println(searchedPerson);
        System.out.println(peopleMap.get(searchedPerson));

    }
}
