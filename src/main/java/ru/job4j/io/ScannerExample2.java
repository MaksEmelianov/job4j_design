package ru.job4j.io;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class ScannerExample2 {

    public static void main(String[] args) {
        String data = "1@1.com, 2@2.com, 3@3.com";
        Scanner scanner = new Scanner(new ByteArrayInputStream(data.getBytes()))
                .useDelimiter(", ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
