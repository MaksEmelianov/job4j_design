package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> map = new HashMap<>();

    public void printDuplicates() {
        for (var entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.printf("Original file: %s | Size: %s%n",
                        entry.getKey().getName(), entry.getKey().getSize());
                System.out.println("Duplicate list: ");
                entry.getValue().forEach(System.out::println);
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(
                file.toFile().getName(), file.toFile().length());
        map.putIfAbsent(fileProperty, new ArrayList<>());
        map.get(fileProperty).add(file);
        return super.visitFile(file, attrs);
    }
}
