package com.library.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {
    private final DatabaseHelper dbHelper;

    public TransactionDAO() {
        dbHelper = new DatabaseHelper();
    }
    // Methods to issue and return books, track due dates
    public void issueBook(int memberId, int bookId, Date issueDate, Date dueDate) {
        String sql = """
                INSERT INTO Transactions(member_id, book_id, issue_date, due_date)
                VALUES(?, ?, ?, ?)
                """;
        try (Connection conn = dbHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberId);
            pstmt.setInt(2, bookId);
            pstmt.setDate(3, issueDate);
            pstmt.setDate(4, dueDate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void returnBook(int transactionId, Date returnDate) {
        String sql = """
                update Transactions
                set return_date = ?, returned = true
                where id = ?
                """;
        try (Connection conn = dbHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, returnDate);
            pstmt.setInt(2, transactionId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void trackDueDate() {
        String sql = """
                SELECT * FROM Transactions
                WHERE returned = false
                AND due_date < current_date
                """;
        try (Connection conn = dbHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Transaction ID: " + rs.getInt("id"));
                System.out.println("Member ID: " + rs.getInt("member_id"));
                System.out.println("Book ID: " + rs.getInt("book_id"));
                System.out.println("Issue Date: " + rs.getDate("issue_date"));
                System.out.println("Due Date: " + rs.getDate("due_date"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
