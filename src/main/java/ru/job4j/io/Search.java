package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        isValidate(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1]))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> predicate) throws IOException {
        SearchFiles searchFiles = new SearchFiles(predicate);
        Files.walkFileTree(root, searchFiles);
        return searchFiles.getPaths();
    }

    private static void isValidate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number of arguments");
        }
        Dir.checkAndGetFile(args);
        if (args[1].length() < 2) {
            throw new IllegalStateException("Value less than 2 characters");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalStateException("Value does not start in dots");
        }
    }
}
