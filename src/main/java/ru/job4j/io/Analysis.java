package ru.job4j.io;

import java.io.*;
import java.util.List;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            List<String> lines = reader.lines().toList();
            recordPeriod(lines, target);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private void recordPeriod(List<String> lines, String target) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            StringBuilder stringBuilder = new StringBuilder(System.lineSeparator());
            checkStatusAndFillingBuffer(lines, stringBuilder);
            writer.write(stringBuilder.toString());
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private void checkStatusAndFillingBuffer(List<String> lines, StringBuilder stringBuilder) {
        boolean oldStatus = true;
        for (var line : lines) {
            if (!line.contains("#") && !line.isBlank() && validate(line)) {
                String[] elements = line.split(" ");
                if (!isWork(line)) {
                    if (oldStatus) {
                        stringBuilder.append(elements[1]).append(";");
                    }
                    oldStatus = false;
                } else {
                    if (!oldStatus) {
                        stringBuilder.append(elements[1])
                                .append(";").append(System.lineSeparator());
                    }
                    oldStatus = true;
                }
            }
        }
    }

    private boolean isWork(String line) {
        int status = Integer.parseInt(line.split(" ")[0]);
        return status != 400 && status != 500;
    }

    private boolean validate(String line) {
        String[] split = line.split(" ", 2);
        if (!line.contains(" ")) {
            throw new IllegalArgumentException("There is a string that does not contain separator");
        }
        if (split[0].isBlank()) {
            throw new IllegalArgumentException("There is a string containing an empty key");
        }
        if (split[1].isBlank()) {
            throw new IllegalArgumentException("There is a string containing an empty value");
        }
        return true;
    }

    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            writer.println("15:01:30;15:02:32");
            writer.println("15:10:30;23:12:32");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
