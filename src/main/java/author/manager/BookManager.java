package author.manager;

import author.db.DBConnectionProvider;
import author.model.Author;
import author.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    private BookManager bookManager = new BookManager();

    public  void add(Book book) {
        System.out.println("before saving book");
        System.out.println(book);
        String sql = "INSERT INTO book(serialID, title, description, price, count, authors) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getSerialID());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getDescription());
            ps.setDouble(4, book.getPrice());
            ps.setInt(5, book.getCount());
            ps.setObject(6, book.getAuthors());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                book.setId(id);
            }
            System.out.println("book was added successfully");
            System.out.println(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book getBookById(int id) {
        String sql = "SELECT * from book WHERE id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book getBookBySerialID(String serialID) {
        String sql = "SELECT * FROM book WHERE serialID = '" + serialID + "'";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book getBookByTitle(String title) {
        String sql = "SELECT * FROM book WHERE title = '" + title + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM book";
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Set<Book> getBooksByAuthor(Author author) {
        Set<Book> books = new HashSet<>();
        String sql = "SELECT * FROM book WHERE author = '" + author + "'";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book getCountOfBookByAuthor(Author author) {
        String sql = "SELECT * FROM book WHERE author = '" + author + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteBookById(int id) {
        String sql = "DELETE FROM book WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBookByAuthor(Author author) {
        String sql = "DELETE FROM book WHERE author = " + author;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Book getBookFromResultSet(ResultSet resultSet) {
        try {
            return Book.builder()
                    .id(resultSet.getInt(1))
                    .title(resultSet.getString(2))
                    .description(resultSet.getString(3))
                    .price(resultSet.getDouble(4))
                    .count(resultSet.getInt(5))
                    .authors(bookManager.getBooksByAuthor(resultSet.getObject(6, )))
                    .build();
        } catch (SQLException e) {
            return null;
        }
    }
}
