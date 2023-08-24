package Articles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split(", ");
        String title = tokens[0];
        String content = tokens[1];
        String author = tokens[2];

        Article article = new Article(title, content, author);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String command = scanner.nextLine();
            String commandType = command.split(": ")[0];
            String commandParam = command.split(": ")[1];

            switch (commandType) {
                case "Edit":
                    article.edit(commandParam);
                    break;
                case "ChangeAuthor":
                    article.changeAuthor(commandParam);
                    break;
                case "Rename":
                    article.rename(commandParam);
                    break;
            }
        }

        System.out.println(article.toString());
    }
}
