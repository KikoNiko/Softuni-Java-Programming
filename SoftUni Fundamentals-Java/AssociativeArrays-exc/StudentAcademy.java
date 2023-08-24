import java.util.*;

public class StudentAcademy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, List<Double>> studentMap = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String studentName = scanner.nextLine();
            studentMap.putIfAbsent(studentName, new ArrayList<>());

            double grade = Double.parseDouble(scanner.nextLine());
            studentMap.get(studentName).add(grade);
        }

        Map <String, Double> aboveAverageStudents = new LinkedHashMap<>();

        for (Map.Entry<String, List<Double>> entry : studentMap.entrySet()) {
            String name = entry.getKey();
            List<Double> grades = entry.getValue();
            double averageGrade = findAverageGrade(grades);
            if (averageGrade >= 4.50) {
                aboveAverageStudents.put(name, averageGrade);
            }
        }

        aboveAverageStudents.forEach((key, value) -> System.out.printf("%s -> %.2f%n", key, value));

    }

    private static double findAverageGrade(List<Double> gradesList) {
        double sum = 0;
        for (double grade : gradesList) {
            sum += grade;
        }
        return sum / gradesList.size();
    }
}
