package author;

public interface AuthorBookCommands {

    String EXIT = "0";
    String ADD_AUTHOR = "1";
    String SEARCH_AUTHORS = "2";
    String SEARCH_AUTHORS_BY_AGE = "3";
    String PRINT_AUTHORS = "4";
    String ADD_BOOK = "5";
    String SEARCH_BOOKS_BY_TITLE = "6";
    String PRINT_BOOKS = "7";
    String SEARCH_BOOKS_BY_AUTHOR = "8";
    String COUNT_BOOKS_BY_AUTHOR = "9";
    String CHANGE_AUTHOR = "10";
    String CHANGE_BOOK_AUTHOR = "11";
    String DELETE_AUTHOR = "12";
    String DELETE_BY_AUTHOR = "13";
    String DELETE_BOOK = "14";
    String ADD_TAGS_TO_BOOK = "15";
    String REMOVE_TAGS_FROM_BOOK = "16";

    // user commands
    String LOG_OUT = "17";
    String LOG_IN = "1";
    String REGISTER = "2";

    static void printCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + LOG_IN + " for logging in");
        System.out.println("Please input " + REGISTER + " for registering");
    }

    static void printAdminCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_AUTHOR + " for adding author");
        System.out.println("Please input " + SEARCH_AUTHORS + " for searching author by name");
        System.out.println("Please input " + SEARCH_AUTHORS_BY_AGE + " for searching author by age");
        System.out.println("Please input " + PRINT_AUTHORS + " for printing authors");
        System.out.println("Please input " + ADD_BOOK + " for adding books");
        System.out.println("Please input " + SEARCH_BOOKS_BY_TITLE + " for searching books by title");
        System.out.println("Please input " + PRINT_BOOKS + " for printing books");
        System.out.println("Please input " + SEARCH_BOOKS_BY_AUTHOR + " for searching books by author");
        System.out.println("Please input " + COUNT_BOOKS_BY_AUTHOR + " for counting books by author");
        System.out.println("Please input " + CHANGE_AUTHOR + " for changing author");
        System.out.println("Please input " + CHANGE_BOOK_AUTHOR + " for changing author");
        System.out.println("Please input " + DELETE_AUTHOR + " for deleting author");
        System.out.println("Please input " + DELETE_BY_AUTHOR + " for deleting by author");
        System.out.println("Please input " + ADD_TAGS_TO_BOOK + " for adding tag to book");
        System.out.println("Please input " + REMOVE_TAGS_FROM_BOOK + " for removing tags book");
        System.out.println("Please input " + LOG_OUT + " for logging out");
    }


    static void printUserCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_AUTHOR + " for adding author");
        System.out.println("Please input " + SEARCH_AUTHORS + " for searching author by name");
        System.out.println("Please input " + SEARCH_AUTHORS_BY_AGE + " for searching author by age");
        System.out.println("Please input " + PRINT_AUTHORS + " for printing authors");
        System.out.println("Please input " + ADD_BOOK + " for adding books");
        System.out.println("Please input " + SEARCH_BOOKS_BY_TITLE + " for searching books by title");
        System.out.println("Please input " + PRINT_BOOKS + " for printing books");
        System.out.println("Please input " + SEARCH_BOOKS_BY_AUTHOR + " for searching books by author");
        System.out.println("Please input " + COUNT_BOOKS_BY_AUTHOR + " for counting books by author");
        System.out.println("Please input " + CHANGE_AUTHOR + " for changing author");
        System.out.println("Please input " + LOG_OUT + " for logging out");
    }

}
