package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswer;

    public ConsoleChat(String path, String botAnswer) {
        this.path = path;
        this.botAnswer = botAnswer;
    }

    public void run() {
        boolean statusChat = true;
        boolean statusAnswer = true;
        boolean oldStatusAnswer;
        List<String> log = new ArrayList<>();
        while (statusChat) {
            Scanner scanner = new Scanner(System.in);
            String textIn = scanner.nextLine();
            String textAnswer;
            oldStatusAnswer = statusAnswer;
            log.add(textIn);
            if (STOP.equals(textIn)) {
                statusAnswer = false;
            } else if (CONTINUE.equals(textIn)) {
                statusAnswer = true;
            } else if (OUT.equals(textIn)) {
                statusChat = false;
            }
            if (statusChat && statusAnswer && oldStatusAnswer) {
                textAnswer = getRandomPhrase();
                System.out.println(textAnswer);
                log.add(textAnswer);
            }
        }
        saveLog(log);
//        switch (textIn) {
//            case OUT -> statusChat = false;
//            case STOP -> statusAnswer = false;
//            case CONTINUE -> statusAnswer = true;
//        }
    }

    private String getRandomPhrase() {
        return readPhrases().get(new Random().nextInt(9));
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswer))) {
            list = reader.lines().toList();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            log.forEach(writer::println);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/log.txt", "./data/phrases_for_bot.txt");
        cc.run();
    }
}
