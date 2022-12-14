package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.Objects;
import java.util.StringJoiner;

public class ConnectionDemo {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "create table if not exists demo_table(%s, %s);",
                        "id serial primary key",
                        "name varchar(255)");
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws SQLException {
        String rowSep = "-".repeat(30).concat(System.lineSeparator());
        String header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        StringJoiner buffer = new StringJoiner(rowSep, rowSep, rowSep);
        buffer.add(header);
        try (Statement statement = connection.createStatement()) {
            ResultSet selection = statement.executeQuery(
                    String.format(
                            "select * from %s limit 1", tableName));
            ResultSetMetaData metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i)));
            }
        }
        return buffer.toString();
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        ClassLoader cl = ConnectionDemo.class.getClassLoader();
        String path = Objects.requireNonNull(cl.getResource("app.properties")).getPath();
        Config config = new Config(path);
        config.load();
        Class.forName(config.value("driver_class"));
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        return DriverManager.getConnection(url, login, password);
    }
}
