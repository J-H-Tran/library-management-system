package com.library.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:src/main/resources/library.db";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = java.sql.DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
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
        String createBooksTable = """
                CREATE TABLE IF NOT EXISTS Books (
                    id INTEGER PRIMARY KEY autoincrement,
                    title TEXT NOT NULL,
                    author TEXT NOT NULL,
                    isbn TEXT NOT NULL,
                    available_copies INTEGER NOT NULL
                );
                """;
        String createMembersTable = """
                CREATE TABLE IF NOT EXISTS Members (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    email TEXT NOT NULL,
                    phone TEXT NOT NULL
                );
                """;
        String createTransactionsTable = """
                CREATE TABLE IF NOT EXISTS Transactions (
                    id integer primary key autoincrement,
                    book_id integer not null,
                    member_id integer not null,
                    issue_date text not null,
                    due_date text not null,
                    return_date text,
                    foreign key (book_id) references Books(id),
                    foreign key (member_id) references Members(id)
                );
                """;
        try (var conn = this.connect();
             var stmt = conn.createStatement()) {
            stmt.execute(createBooksTable);
            stmt.execute(createMembersTable);
            stmt.execute(createTransactionsTable);
            System.out.println("Tables have been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
