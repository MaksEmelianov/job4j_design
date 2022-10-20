package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor dv = new DuplicatesVisitor();
        Files.walkFileTree(
                Path.of("/Users/iam/Documents/projects/test_find_duplicates"), dv);
        dv.printDuplicates();
    }
}
