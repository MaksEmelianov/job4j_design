package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {

    public static void main(String[] args) {
        try (FileOutputStream outputStream = new FileOutputStream("result.txt")) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    int multi = i * j;
                    String rsl = "" + i + " * " + j + " = " + multi;
                    outputStream.write(rsl.getBytes());
                    outputStream.write(System.lineSeparator().getBytes());
                }
                outputStream.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] matrix = new int[10][10];
        try (FileOutputStream outputStream = new FileOutputStream("result2.txt")) {
            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix[i].length; j++) {
                    int multi = i * j;
                    String rsl = " " + multi;
                    outputStream.write(rsl.getBytes());
                }
                outputStream.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
