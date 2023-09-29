import java.util.*;

public class _05_AverageStudentsGrades {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, List<Double>> map = new TreeMap<>();
        double averageGrade = 0.0;

        for (int i = 0; i < n; i++) {

            String[] studentData = scanner.nextLine().split("\\s+");
            String studentName = studentData[0];
            double grade = Double.parseDouble(studentData[1]);

            if (!map.containsKey(studentName)) {
                map.put(studentName, new ArrayList<>());
                map.get(studentName).add(grade);
            } else {
                map.get(studentName).add(grade);
            }
        }

        for (Map.Entry<String, List<Double>> entry : map.entrySet()) {
            averageGrade = calculateAverage(entry.getValue());
            System.out.printf("%s -> ", entry.getKey());
            entry.getValue().forEach(d -> System.out.printf("%.2f ", d));
            System.out.printf("(avg: %.2f)%n", averageGrade);
        }
    }

    private static double calculateAverage(List<Double> list) {
        return list.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0);
    }
}
