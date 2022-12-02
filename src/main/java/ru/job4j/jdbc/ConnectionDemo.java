package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        ClassLoader cl = ConnectionDemo.class.getClassLoader();
        String path = Objects.requireNonNull(cl.getResource("app.properties")).getPath();
        Config config = new Config(path);
        config.load();
        Class.forName(config.value("driver_class"));
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
