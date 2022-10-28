package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            for (File file : source) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }


    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private void isValidateArgsAndGetFile(ArgsName jvm) {
        File file = new File(jvm.get("d"));
        if (!file.exists()) {
            throw new IllegalStateException(String.format("Not exist %s", file.getAbsolutePath()));
        }
        if (!file.isDirectory()) {
            throw new IllegalStateException(String.format("Not directory %s", file.getAbsolutePath()));
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName jvm = ArgsName.of(args);
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        zip.isValidateArgsAndGetFile(jvm);
        zip.packFiles(getSearchList(jvm), new File(jvm.get("o")));
    }

    private static List<File> getSearchList(ArgsName jvm) throws IOException {
        return Search.search(Path.of(jvm.get("d")),
                path -> !path.toFile().getName().contains(jvm.get("e")))
                .stream().map(Path::toFile).toList();
    }
}
