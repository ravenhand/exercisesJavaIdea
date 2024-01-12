
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.Scanner;


public class HomeWork {

    // Én ezeket a könyveket írtam be a konzolba:
    // HP-Rowling-1997-06-26-3-FANTASY
    // LOTR-Tolkien-1954-07-29-2-FANTASY
    // Hannibal-Harris-1999-06-08-5-FICTION
    // Dune-Herbert-1965-08-01-6-SCIENCE_FICTION

    public static void main(String[] args) {
        List<String> library = getBookToLibraryFromUser();
        getBookListToConsole(library);
        searchBookInList(library);
        List<String> newLibrary = changeQuantity(library);
        writeToFile(newLibrary);
    }

    public static String scanner(String textToUser) {
        Scanner sc = new Scanner(System.in);
        System.out.println(textToUser);
        return sc.nextLine();
    }

    public static List<String> getBookToLibraryFromUser() {
        List<String> library = new ArrayList<>(3);
        String book = "";
        while (!(library.size() == 4)) {
            try {
                String title = scanner("Give me the book title: ");
                String author = scanner("Give me the author of the book: ");
                System.out.println("Give me the publication year of the book: ");
                String publicationYear = scanner("Give me the publication year of the book: ");
                int publicationYearInt = Integer.parseInt(publicationYear);
                int quantity = Integer.parseInt(scanner("Give me the quantity of the book: "));
                System.out.println("Give me the genre of the book: ");
                BookGenre genre = BookGenre.valueOf(scanner("Give me the genre of the book: "));
                if (genre.equals("fiction")) {
                    genre = BookGenre.FICTION;
                } else if (genre.equals("non fiction")) {
                    genre = BookGenre.NON_FICTION;
                } else if (genre.equals("fantasy")) {
                    genre = BookGenre.FANTASY;
                } else if (genre.equals("sci-fi")) {
                    genre = BookGenre.SCIENCE_FICTION;
                }
                book = STR."\{title}-\{author}-\{publicationYearInt}-\{quantity}-\{genre}";
                library.add(book);
            } catch (DateTimeParseException e) {
                System.out.println(STR."Invalid date format\{e.getMessage()}");
            }
        }
        return library;
    }

    public static void getBookListToConsole(List<String> library) {
        for (String book : library) {
            System.out.println(book);
        }
    }

    public enum BookGenre {
        FICTION, NON_FICTION, FANTASY, SCIENCE_FICTION
    }

    public static void searchBookInList(List<String> library) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which book are you looking for?");
        String userInput = sc.nextLine();
        String bookTitle = "";
        for(String book:library) {
            String[] bookArray = book.split("-");
            bookTitle = bookArray[0];
            if (userInput.equals(bookTitle)) {
                System.out.println(STR."\{userInput} is in the Library");
            } else {
                System.out.println("There is no such book in our system.");
            }
        }
    }

    public static List<String> changeQuantity(List<String> list) {
        List<String> changedBookList = new ArrayList<>();
        String searchedBook = scanner("Which book do you want to change of its quantity?");
        String newQuantity = scanner("New quantity number:");
        String bookTitle = "";
        for (String books : list) {
            String[] booksArray = books.split("-");
            bookTitle = booksArray[0];
            if (bookTitle.equals(searchedBook)) {
                System.out.println("I found the book");
                newQuantity = booksArray[4];
                System.out.println("New quantity number changed to: " + booksArray[4]);
            } else {
                System.out.println("There is no such book in our system.");
            }
            String book = booksArray[0] + "-" + booksArray[1] + "-" + booksArray[2] + "-" + booksArray[3] + "-" + booksArray[4] + "-";
            changedBookList.add(book);
        }
        return changedBookList;
    }
    public static void writeToFile(List<String> list) {

        for (String book : list) {
            try {
                Files.write(Paths.get("resources/library.txt"), (book).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            } catch (Exception e) {
                System.out.println("No such file");
            }
        }
    }

}