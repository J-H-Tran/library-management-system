package com.library;

import com.library.service.LibraryManager;
import java.sql.Date;
import java.util.Scanner;

public class Main {
	private static final LibraryManager libraryManager = new LibraryManager();
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		libraryManager.addBook(
				"Test",
				"Author Test",
				"9780062315007test",
				99
		);
		libraryManager.getBook(1);
		System.out.println("test book 1.\n");

        libraryManager.addBook(
			"The Alchemist",
			"Paulo Coelho",
			"9780062315007",
			5
		);
        libraryManager.addBook(
			"The Great Gatsby",
			"F. Scott Fitzgerald",
			"9780743273565",
			3
		);
        libraryManager.addBook(
			"To Kill a Mockingbird",
			"Harper Lee",
			"9780060935467",
			2
		);
        libraryManager.addBook(
			"1984",
			"George Orwell",
			"9780451524935",
			4
		);
        libraryManager.addBook(
			"Pride and Prejudice",
			"Jane Austen",
			"9780679783268",
			3
		);
		System.out.println("Books added.\n");

        libraryManager.addMember(
			"Alice",
			"alice@test.com",
			"1234567890"
		);
        libraryManager.addMember(
			"Bob",
			"bob@test.com",
			"2345678901"
		);
        libraryManager.addMember(
			"Charlie",
			"charlie@test.com",
			"3456789012"
		);
		System.out.println("Members added.\n");
		System.out.println("Initial state of the library created.");

		while (true) {
			printMenu();
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1 -> addBook();
				case 2 -> addMember();
				case 3 -> issueBook();
				case 4 -> returnBook();
				case 5 -> searchBook();
				case 6 -> searchMember();
				case 7 -> System.exit(0);
				default -> System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void printMenu() {
		System.out.println("\nLibrary Management System");
		System.out.println("1. Add Book");
		System.out.println("2. Add Member");
		System.out.println("3. Issue Book");
		System.out.println("4. Return Book");
		System.out.println("5. Search Books");
		System.out.println("6. Search Members");
		System.out.println("7. Exit");
		System.out.print("Enter your choice: ");
	}

	private static void addBook() {
		System.out.print("Enter book title: ");
		String title = scanner.nextLine();
		System.out.print("Enter book author: ");
		String author = scanner.nextLine();
		System.out.print("Enter book ISBN: ");
		String isbn = scanner.nextLine();
		System.out.print("Enter available copies: ");
		int availableCopies = Integer.parseInt(scanner.nextLine());

		libraryManager.addBook(title, author, isbn, availableCopies);
		System.out.println("Book added successfully.");
	}

	private static void addMember() {
		System.out.print("Enter member name: ");
		String name = scanner.nextLine();
		System.out.print("Enter member email: ");
		String email = scanner.nextLine();
		System.out.print("Enter member phone: ");
		String phone = scanner.nextLine();

		libraryManager.addMember(name, email, phone);
		System.out.println("Member added successfully.");
	}

	private static void issueBook() {
		System.out.print("Enter book ID: ");
		int bookId = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter member ID: ");
		int memberId = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter issue date (yyyy-mm-dd): ");
		Date issueDate = Date.valueOf(scanner.nextLine());
		System.out.print("Enter due date (yyyy-mm-dd): ");
		Date dueDate = Date.valueOf(scanner.nextLine());

		libraryManager.issueBook(memberId, bookId, issueDate, dueDate);
		System.out.println("Book issued successfully.");
	}

	private static void returnBook() {
		System.out.print("Enter transaction ID: ");
		int transactionId = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter return date (yyyy-mm-dd): ");
		Date returnDate = Date.valueOf(scanner.nextLine());

		libraryManager.returnBook(transactionId, returnDate);
		System.out.println("Book returned successfully.");
	}

	private static void searchBook() {
		System.out.print("Enter book id to search: ");
		int query = Integer.parseInt(scanner.nextLine());;
		libraryManager.getBook(query);
	}

	private static void searchMember() {
		System.out.print("Enter member id to search: ");
		int query = Integer.parseInt(scanner.nextLine());;
		libraryManager.getMember(query);
	}
}