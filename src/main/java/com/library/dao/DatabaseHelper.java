package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:src/main/resources/library.db";
    private static final String BOOKS_TABLE = "Books";
    private static final String MEMBERS_TABLE = "Members";
    private static final String TRANSACTIONS_TABLE = "Transactions";

    public Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Connection to SQLite has failed.");
            e.printStackTrace();
            return null;
        }
    }

    public void createNewDatabase() {
        try (var conn = this.connect()) {
            if (conn != null) {
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTables() {
        createBooksTable();
        createMembersTable();
        createTransactionsTable();
    }

    private void createBooksTable() {
        String createBooksTable = """
                CREATE TABLE IF NOT EXISTS Books (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    author TEXT NOT NULL,
                    isbn TEXT NOT NULL,
                    available_copies INTEGER NOT NULL
                );
                """;
        executeUpdate(createBooksTable, BOOKS_TABLE);
    }

    private void createMembersTable() {
        String createMembersTable = """
                CREATE TABLE IF NOT EXISTS Members (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    email TEXT NOT NULL,
                    phone TEXT NOT NULL
                );
                """;
        executeUpdate(createMembersTable, MEMBERS_TABLE);
    }

    private void createTransactionsTable() {
        String createTransactionsTable = """
                CREATE TABLE IF NOT EXISTS Transactions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    book_id INTEGER NOT NULL,
                    member_id INTEGER NOT NULL,
                    issue_date TEXT NOT NULL,
                    due_date TEXT NOT NULL,
                    return_date TEXT,
                    returned BOOLEAN NOT NULL DEFAULT false,
                    FOREIGN KEY (book_id) REFERENCES Books(id),
                    FOREIGN KEY (member_id) REFERENCES Members(id)
                );
                """;
        executeUpdate(createTransactionsTable, TRANSACTIONS_TABLE);
    }

    private void executeUpdate(String sql, String tableName) {
        try (var conn = this.connect();
             var stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.printf("%s Table has been created.%n", tableName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
