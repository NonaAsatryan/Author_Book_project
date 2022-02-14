package author.manager;

import author.db.DBConnectionProvider;
import author.model.Author;
import author.model.Gender;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AuthorManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public  void add(Author author) {
        System.out.println("before saving author");
        System.out.println(author);
        String sql = "INSERT INTO author(name, surname, email, age, gender, dateofbirth) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setInt(4, author.getAge());
            ps.setString(5, author.getGender().name());
            ps.setString(6, author.getDateOfBirth().toString());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                author.setId(id);
            }
            System.out.println("author was added successfully");
            System.out.println(author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Author getAuthorById(long id) {
        String sql = "SELECT * FROM author WHERE id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Author getAuthorByEmail(String email) {
        String sql = "SELECT * FROM author WHERE email = '" + email + "'";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM author";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authors.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public Author getAuthorByName(String name) {
        String sql = "SELECT * FROM author WHERE name = '" + name + "'";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Author> getAuthorByAge(int minAge, int maxAge) {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT MIN(age), MAX(age)  FROM author WHERE age = '" + minAge + "' AND age = '" + maxAge + "'";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authors.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public void deleteAuthorById(int id) {
        String sql = "DELETE FROM author WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) {
        try {
            return Author.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .surname(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .age(resultSet.getInt(5))
                    .gender(Gender.valueOf(resultSet.getString(6)))
                    .dateOfBirth(sdf.parse(resultSet.getString(7)))
                    .build();
        } catch (SQLException e) {
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
