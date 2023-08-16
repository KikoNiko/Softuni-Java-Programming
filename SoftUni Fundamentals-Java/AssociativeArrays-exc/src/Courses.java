import java.util.*;

public class Courses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String data = scanner.nextLine();

        Map<String, List<String>> coursesMap = new LinkedHashMap<>();

        while (!data.equals("end")) {
            String courseName = data.split(" : ")[0];
            String studentName = data.split(" : ")[1];

            if (!coursesMap.containsKey(courseName)) {
                coursesMap.put(courseName, new ArrayList<>());
            }
            coursesMap.get(courseName).add(studentName);

            data = scanner.nextLine();
        }

        for (Map.Entry<String, List<String>> entry : coursesMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
            entry.getValue().forEach(s -> System.out.println("-- " + s));
        }

    }
}
