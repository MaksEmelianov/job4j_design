package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (var line : lines) {
                int num = Integer.parseInt(line);
                String isEven = num % 2 == 0 ? "Even" : "Odd";
                String rsl = "" + num + " - " + isEven;
                System.out.println(rsl);
            }
        } catch (FileNotFoundException notFound) {
            notFound.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
