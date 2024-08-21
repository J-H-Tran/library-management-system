package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.MemberDAO;
import com.library.dao.TransactionDAO;
import com.library.dao.DatabaseHelper;
import java.sql.Date;

public class LibraryManager {
    private final BookDAO bookDAO;
    private final MemberDAO memberDAO;
    private final TransactionDAO transactionDAO;

    public LibraryManager() {
        bookDAO = new BookDAO();
        memberDAO = new MemberDAO();
        transactionDAO = new TransactionDAO();

        DatabaseHelper dbHelper = new DatabaseHelper();

        dbHelper.createNewDatabase();
        dbHelper.createTables();
    }

    // Book CRUD operations
    public void addBook(String title, String author, String isbn, int availableCopies) {
        bookDAO.addBook(title, author, isbn, availableCopies);
    }

    public void getBook(int id) {
        bookDAO.getBook(id);
    }

    public void updateBook(int id, String title, String author, String isbn, int availableCopies) {
        bookDAO.updateBook(id, title, author, isbn, availableCopies);
    }

    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }

    // Member CRUD operations
    public void addMember(String name, String email, String phone) {
        memberDAO.addMember(name, email, phone);
    }

    public void getMember(int id) {
        memberDAO.getMember(id);
    }

    public void updateMember(int id, String name, String email, String phone) {
        memberDAO.updateMember(id, name, email, phone);
    }

    public void deleteMember(int id) {
        memberDAO.deleteMember(id);
    }

    // Issue and return books
    public void issueBook(int memberId, int bookId, Date issueDate, Date dueDate) {
        transactionDAO.issueBook(memberId, bookId, issueDate, dueDate);
    }

    public void returnBook(int transactionId, Date returnDate) {
        transactionDAO.returnBook(transactionId, returnDate);
    }

    // Track due dates
    public void trackDueDate() {
        transactionDAO.trackDueDate();
    }
}
