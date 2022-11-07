package ru.job4j.searcher;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Search {

    public static void main(String[] args) throws IOException {
        Search search = new Search();
        search.run(args);
    }

    private void run(String[] args) throws IOException {
        ParsingArgs parsingArgs = ParsingArgs.of(args);
        Path root = getAndCheckDir(parsingArgs);
        String fileName = parsingArgs.get("n");
        String type = parsingArgs.get("t");
        ValidatesParameters.isValidateNameAndType(fileName, type);
        List<Path> pathList = getPathList(root, fileName, type);
        saveResult(parsingArgs, pathList);
    }

    private List<Path> getPathList(Path root, String fileName, String type) throws IOException {
        return switch (type) {
            case "name" -> getPathsOfName(root, fileName);
            case "mask" -> getPathsOfMask(root, fileName);
            case "regex" -> getPathsOfRegex(root, fileName);
            default -> new ArrayList<>();
        };
    }

    private void saveResult(ParsingArgs parsingArgs, List<Path> pathList) {
        String saveFile = parsingArgs.get("o");
        ValidatesParameters.isValidateSaveFile(saveFile);
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(saveFile, true))) {
            pathList.forEach(writer::println);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private Path getAndCheckDir(ParsingArgs parsingArgs) {
        Path root = Paths.get(parsingArgs.get("d"));
        ValidatesParameters.isValidateDirValue(root);
        return root;
    }

    private List<Path> getPathsOfName(Path root, String name) throws IOException {
        return search(root, path -> name.equals(path.toFile().getName()));
    }

    private List<Path> getPathsOfMask(Path root, String mask) throws IOException {
        if (!mask.contains("*") && !mask.contains("?")) {
            throw new IllegalArgumentException("The mask does not contain pointers * or ?");
        }
        String finalMask = mask
                .replace(".", "\\.")
                .replace("?", ".?")
                .replace("*", ".*?");
        return search(root, path -> path.toFile().getName().matches(finalMask));
    }

    private List<Path> getPathsOfRegex(Path root, String regex) throws IOException {
        Pattern pattern = Pattern.compile(regex);
        return search(root, path -> pattern.matcher(path.toFile().getName()).matches());
    }

    private List<Path> search(Path root, Predicate<Path> predicate) throws IOException {
        SearchFiles searchFiles = new SearchFiles(predicate);
        Files.walkFileTree(root, searchFiles);
        return searchFiles.getPaths();
    }
}
