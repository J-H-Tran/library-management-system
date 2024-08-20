package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.MemberDAO;
import com.library.dao.TransactionDAO;
import com.library.dao.DatabaseHelper;

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

    // methods to handle CRUD operations, issue and return books, etc.
}
