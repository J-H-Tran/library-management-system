package com.library;

import com.library.service.LibraryManager;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();

        libraryManager.addBook("The Alchemist", "Paulo Coelho", "9780062315007", 5);
        libraryManager.addBook("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 3);
        libraryManager.addBook("To Kill a Mockingbird", "Harper Lee", "9780060935467", 2);
        libraryManager.addBook("1984", "George Orwell", "9780451524935", 4);
        libraryManager.addBook("Pride and Prejudice", "Jane Austen", "9780679783268", 3);

        libraryManager.addMember("Alice", "alice@test.com", "1234567890");
        libraryManager.addMember("Bob", "bob@test.com", "2345678901");
        libraryManager.addMember("Charlie", "charlie@test.com", "3456789012");

        // Issue books
        libraryManager.issueBook(1, 1, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L)); // Alice checks out "The Alchemist"
        libraryManager.updateBook(1, "The Alchemist", "Paulo Coelho", "9780062315007", 4); // Update available copies of "The Alchemist"

        libraryManager.issueBook(2, 2, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L)); // Bob checks out "The Great Gatsby"
        libraryManager.updateBook(2, "The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 2); // Update available copies of "The Great Gatsby"

        libraryManager.issueBook(3, 3, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000L)); // Charlie checks out "To Kill a Mockingbird" (overdue)
        libraryManager.updateBook(3, "To Kill a Mockingbird", "Harper Lee", "9780060935467", 1); // Update available copies of "To Kill a Mockingbird"


        // Return book
        libraryManager.returnBook(1, new Date(System.currentTimeMillis())); // Alice returns "The Alchemist"

        libraryManager.trackDueDate();
    }
}
