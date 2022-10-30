package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String[] firstLine = in.readLine().split(" ");
                    String path = firstLine[1];
                    isValidateFirstLine(firstLine, path);
                    String msg = path.split("=")[1];
                    if ("Exit".equals(msg)) {
                        server.close();
                    } else if ("Hello".equals(msg)) {
                        System.out.println("Hello");
                        out.write("Hello, dear friend.".getBytes());
                    } else {
                        System.out.println(msg);
                        out.write("What?".getBytes());
                    }
                    out.flush();
                }
            }
        }
    }

    private static void isValidateFirstLine(String[] firstLine, String path) {
        if (firstLine.length != 3) {
            throw new IllegalArgumentException("Check for arguments");
        }
        if (!path.contains("msg")) {
            throw new IllegalArgumentException("No msg");
        }
        if (!path.contains("=")) {
            throw new IllegalArgumentException("No separator =");
        }
    }
}
