package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {

    public static void main(String[] args) {
        File file = new File("/Users/iam/Documents/projects/");
        if (!file.exists()) {
            throw new IllegalStateException(String.format("Not exist %s", file.getAbsolutePath()));
        }
        if (!file.isDirectory()) {
            throw new IllegalStateException(String.format("Not directory %s", file.getAbsolutePath()));
        }
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            System.out.printf("name - %s | size - %s%n", subfile.getName(), subfile.length());
        }
    }
}
