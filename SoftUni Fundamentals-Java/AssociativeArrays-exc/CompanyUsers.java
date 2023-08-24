import java.util.*;

public class CompanyUsers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Map<String, List<String>> usersMap = new LinkedHashMap<>();

        while (!input.equals("End")) {
            String company = input.split(" -> ")[0];
            String id = input.split(" -> ")[1];

            if (!usersMap.containsKey(company)) {
                usersMap.put(company, new ArrayList<>());
            }
            if (!usersMap.get(company).contains(id)) {
                usersMap.get(company).add(id);
            }

            input = scanner.nextLine();
        }

        for (Map.Entry<String, List<String>> entry : usersMap.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().forEach(id -> System.out.println("-- " + id));
        }

    }
}
