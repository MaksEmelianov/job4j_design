package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private final Properties properties;

    public TableEditor(Properties properties)
            throws SQLException, ClassNotFoundException, IOException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException, IOException {
        Class.forName(properties.getProperty("driver_class"));
        var url = properties.getProperty("url");
        var login = properties.getProperty("login");
        var password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                "create table if not exists %s(%s);",
                tableName,
                "id serial primary key");
        executeRequest(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "drop table if exists %s;", tableName);
        executeRequest(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "alter table if exists %s add column if not exists %s %s;",
                tableName, columnName, type);
        executeRequest(sql);
    }

    public void dropColumn(String tableName, String columnName)
            throws SQLException {
        String sql = String.format(
                "alter table if exists %s drop column if exists %s;",
                tableName, columnName);
        executeRequest(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName)
            throws SQLException {
        String sql = String.format(
                "alter table if exists %s rename column %s to %s;",
                tableName, columnName, newColumnName);
        executeRequest(sql);
    }

    public String getTableScheme(String tableName) throws SQLException {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1;", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i)));
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            properties.load(in);
            try (TableEditor tableEditor = new TableEditor(properties)) {
                String tableName = "table_test";
                tableEditor.createTable(tableName);
                printScheme(tableEditor, tableName);
                tableEditor.addColumn(tableName, "name", "varchar(255)");
                printScheme(tableEditor, tableName);
                tableEditor.renameColumn(tableName, "name", "full_name");
                printScheme(tableEditor, tableName);
                tableEditor.dropColumn(tableName, "full_name");
                printScheme(tableEditor, tableName);
                tableEditor.dropTable(tableName);
            }
        }
    }

    private static void printScheme(TableEditor tableEditor, String tableName) throws SQLException {
        System.out.println(tableEditor.getTableScheme(tableName));
    }

    private void executeRequest(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
