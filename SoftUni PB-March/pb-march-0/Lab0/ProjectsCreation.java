package Lab0;

import java.util.Scanner;

public class ProjectsCreation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        int numOfProjects = Integer.parseInt(sc.nextLine());
        int time = numOfProjects * 3;

        System.out.printf("The architect %s will need %d hours to complete %d project/s.", name, time, numOfProjects);
    }
}
