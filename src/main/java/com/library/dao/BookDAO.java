package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {
    private final DatabaseHelper dbHelper;

    public BookDAO() {
        dbHelper = new DatabaseHelper();
    }

    public void addBook(String title, String author, String isbn, int available_copies) {
        String sql = """
                INSERT INTO Books(title, author, isbn, available_copies)
                VALUES(?, ?, ?, ?)
                """;
        try (Connection conn = dbHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(1, author);
            pstmt.setString(1, isbn);
            pstmt.setInt(1, available_copies);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBook(int id, String title, String author, String isbn, int available_copies) {
        String sql = """
                UPDATE Books
                SET title = ?, author = ?, isbn = ?, available_copies = ?
                WHERE id = ?
                """;
        try (Connection conn = dbHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(1, author);
            pstmt.setString(1, isbn);
            pstmt.setInt(1, available_copies);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM Books WHERE id = ?";
        try (Connection conn = dbHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchBook(int id) {
        String sql = "SELECT * FROM Books WHERE id = ?";
        try (Connection conn = dbHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("ISBN: " + rs.getString("isbn"));
                System.out.println("Available Copies: " + rs.getInt("available_copies"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
