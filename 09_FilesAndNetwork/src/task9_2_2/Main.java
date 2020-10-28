package task9_2_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static Path fromDirectory;
    public static Path toDirectory;

    public static void main(String[] args) {
        System.out.println("Введите путь");
        fromDirectory = getPath();
        System.out.println("Введите путь назначения");
        toDirectory = getPath();

        try {
            Files.walk(fromDirectory).forEach(Main::copy);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static Path getPath() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path path = null;
        while (true) {
            try {
                path = Paths.get(reader.readLine());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            if (Files.isReadable(path))
                return path;
            else
                System.out.println("Проверьте правильность ввденного пути");

        }
    }

    public static void copy(Path fromFolder) {
        if (!Files.isDirectory(fromFolder)) {
            try {
                Path relativePath = toDirectory.resolve(fromDirectory.relativize(fromFolder));
                Files.copy(fromFolder, relativePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.getMessage();
            }
        }
        if (Files.isDirectory(fromFolder)) {
            try {
                Path relativePath = toDirectory.resolve(fromDirectory.relativize(fromFolder));
                Files.createDirectory(relativePath);
            } catch (Exception e) {

            }
        }
    }
}