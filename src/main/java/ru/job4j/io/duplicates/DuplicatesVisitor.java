package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private List<Path> pathList = new ArrayList<>();

    public List<Path> getDuplicates() {
        return findDuplicates(pathList);
    }

    public Path getOriginalFile() {
        List<Path> list = findDuplicates(pathList);
        if (list.isEmpty()) {
            throw new IllegalStateException("No duplicates found");
        }
        Path duplicate = list.get(0);
        return pathList.stream()
                .filter(path -> isEqualsFiles(duplicate, path))
                .findFirst()
                .orElse(null);
    }

    private boolean isEqualsFiles(Path duplicate, Path path) {
        return duplicate.toFile().getName().equals(path.toFile().getName())
                && duplicate.toFile().length() == path.toFile().length();
    }

    private List<Path> findDuplicates(List<Path> list) {
        Set<FileProperty> tmp = new HashSet<>();
        return list.stream()
                .filter(path -> !tmp.add(
                        new FileProperty(path.toFile().getName(), path.toFile().length())))
                .sorted()
                .toList();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        pathList.add(file);
        return super.visitFile(file, attrs);
    }
}
