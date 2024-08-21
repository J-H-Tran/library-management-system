package com.library.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionsDAO {
    private final DatabaseHelper dbHelper;

    public TransactionsDAO() {
        dbHelper = new DatabaseHelper();
    }

    public void issueBook(int memberId, int bookId, Date issueDate, Date dueDate) {
        String sql = """
                INSERT INTO Transactions (member_id, book_id, issue_date, due_date)
                VALUES (?, ?, ?, ?)
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
                System.out.println("\nTransaction ID: " + rs.getInt("id"));
                System.out.println("Member ID: " + rs.getInt("member_id"));
                System.out.println("Book ID: " + rs.getInt("book_id"));

                long issueTimestamp = rs.getLong("issue_date");
                java.util.Date utilIssueDate = new java.util.Date(issueTimestamp);
                Date sqlIssueDate = new Date(utilIssueDate.getTime());
                System.out.println("Issue Date: " + sqlIssueDate);

                long dueDateTimestamp = rs.getLong("due_date");
                java.util.Date utilDueDate = new java.util.Date(dueDateTimestamp);
                Date sqlDueDate = new Date(utilDueDate.getTime());
                System.out.println("Due Date: " + sqlDueDate);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
