package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor dv = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("/Users/iam/Documents/projects/test_find_duplicates"), dv);
        List<Path> pathList = dv.getDuplicates();
        Path original = dv.getOriginalFile();
        System.out.printf("Original file: %s | Size: %s%n",
                original.toAbsolutePath(), original.toFile().length());
        System.out.println("Duplicate list:");
        pathList.forEach(System.out::println);
    }
}
