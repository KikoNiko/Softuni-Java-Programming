package MoreExc;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ThePianist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, String> pieceComposerMap = new LinkedHashMap<>();
        Map<String, String> pieceKeyMap = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String informationText = scanner.nextLine();
            String[] tokens = informationText.split("\\|");
            String piece = tokens[0];
            String composer = tokens[1];
            String key = tokens[2];
            pieceComposerMap.putIfAbsent(piece, composer);
            pieceKeyMap.putIfAbsent(piece,key);
        }

        String commands = scanner.nextLine();
        while (!commands.equals("Stop")) {
            String[] tokens = commands.split("\\|");
            String command = tokens[0];
            String piece = tokens[1];

            switch (command) {
                case "Add":
                    String composer = tokens[2];
                    String key = tokens[3];
                    if (pieceComposerMap.containsKey(piece)) {
                        System.out.println(piece + " is already in the collection!");
                    } else {
                        pieceComposerMap.put(piece, composer);
                        pieceKeyMap.put(piece, key);
                        System.out.printf("%s by %s in %s added to the collection!%n", piece, composer, key);
                    }
                    break;
                case "Remove":
                    if (!pieceComposerMap.containsKey(piece)) {
                        System.out.printf("Invalid operation! %s does not exist in the collection.%n", piece);
                    } else {
                        pieceComposerMap.remove(piece);
                        pieceKeyMap.remove(piece);
                        System.out.printf("Successfully removed %s!%n", piece);
                    }
                    break;
                case "ChangeKey":
                    if (!pieceKeyMap.containsKey(piece)) {
                        System.out.printf("Invalid operation! %s does not exist in the collection.%n", piece);
                    } else {
                        String newKey = tokens[2];
                        pieceKeyMap.put(piece, newKey);
                        System.out.printf("Changed the key of %s to %s!%n", piece, newKey);
                    }
                    break;
            }

            commands = scanner.nextLine();
        }
        //"{Piece} -> Composer: {composer}, Key: {key}"
        for (Map.Entry<String, String> entry : pieceComposerMap.entrySet()) {
            System.out.printf("%s -> Composer: %s, Key: %s%n", entry.getKey(), entry.getValue(), pieceKeyMap.get(entry.getKey()));
        }

    }
}
