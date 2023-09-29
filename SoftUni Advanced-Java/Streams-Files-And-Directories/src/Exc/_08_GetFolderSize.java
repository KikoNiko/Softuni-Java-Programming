package Exc;

import java.io.File;

public class _08_GetFolderSize {

    public static void main(String[] args) {
        String path = "C:\\Users\\Kiko\\Desktop\\CodingStuff\\JavaStudy\\SoftUni Advanced-Java\\Streams-Files-And-Directories\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\Exercises Resources";

        File directory = new File(path);
        int size = 0;
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();

            assert files != null;
            for (File file : files) {
                if (!file.isDirectory()) {
                    size += file.length();
                }
            }
        }

        System.out.println("Folder size: " + size);
    }
}
