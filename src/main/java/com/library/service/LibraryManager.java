package com.library.service;

import com.library.dao.BooksDAO;
import com.library.dao.MembersDAO;
import com.library.dao.TransactionsDAO;
import com.library.dao.DatabaseHelper;
import java.sql.Date;

public class LibraryManager {
    private final BooksDAO booksDAO;
    private final MembersDAO membersDAO;
    private final TransactionsDAO transactionsDAO;

    public LibraryManager() {
        booksDAO = new BooksDAO();
        membersDAO = new MembersDAO();
        transactionsDAO = new TransactionsDAO();

        DatabaseHelper dbHelper = new DatabaseHelper();

        dbHelper.createNewDatabase();
        dbHelper.createTables();
    }

    // Book CRUD operations
    public void addBook(String title, String author, String isbn, int availableCopies) {
        booksDAO.addBook(title, author, isbn, availableCopies);
    }

    public void getBook(int id) {
        booksDAO.getBook(id);
    }

    public void updateBook(int id, String title, String author, String isbn, int availableCopies) {
        booksDAO.updateBook(id, title, author, isbn, availableCopies);
    }

    public void deleteBook(int id) {
        booksDAO.deleteBook(id);
    }

    // Member CRUD operations
    public void addMember(String name, String email, String phone) {
        membersDAO.addMember(name, email, phone);
    }

    public void getMember(int id) {
        membersDAO.getMember(id);
    }

    public void updateMember(int id, String name, String email, String phone) {
        membersDAO.updateMember(id, name, email, phone);
    }

    public void deleteMember(int id) {
        membersDAO.deleteMember(id);
    }

    // Issue and return books
    public void issueBook(int memberId, int bookId, Date issueDate, Date dueDate) {
        transactionsDAO.issueBook(memberId, bookId, issueDate, dueDate);
    }

    public void returnBook(int transactionId, Date returnDate) {
        transactionsDAO.returnBook(transactionId, returnDate);
    }

    // Track due dates
    public void trackDueDate() {
        transactionsDAO.trackDueDate();
    }
}
