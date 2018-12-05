/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abc_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

/**
 *
 * @author aslam
 */
public class DBHelper {

    Connection conn;
    String url = "jdbc:sqlite:abc_db.db";
    Statement stmt;

    public void connect() {

        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void reConnect() {
        close();
        connect();
    }

    public void initTables() {

        try {

            String usersSQL = "CREATE TABLE IF NOT EXISTS users (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	username text NOT NULL,\n"
                    + "	password text NOT NULL\n"
                    + ");";

            String booksSQL = "CREATE TABLE IF NOT EXISTS books (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	name text NOT NULL,\n"
                    + "	author text NOT NULL\n"
                    + ");";

            conn.createStatement().execute(usersSQL);
            conn.createStatement().execute(booksSQL);
            
            System.out.println("Tables created successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void newUser(User user) {

        try {

            String sql = "INSERT INTO users(username, password) VALUES(?, ?);";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();

            System.out.println(user.getUsername() + " created successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void newBook(Book book) {

        try {

            String sql = "INSERT INTO books(name, author) VALUES(?, ?);";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getAuthor());
            pstmt.executeUpdate();

            System.out.println(book.getName() + " created successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getUsers() {

        List<User> usersList = new ArrayList<>();

        try {

            String sql = "SELECT id, username, password FROM users";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {

                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                usersList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return usersList;
    }

    public List<Book> getBooks() {

        List<Book> booksList = new ArrayList<>();

        try {

            String sql = "SELECT id, name, author FROM books";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {

                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));

                booksList.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return booksList;
    }

    public void updateUser(User user) {

        try {

            String sql = "UPDATE users set username = ?, password = ? WHERE id = ? ;";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getId());
            pstmt.executeUpdate();

            System.out.println(user.getUsername() + " updated successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBook(Book book) {

        try {

            String sql = "UPDATE books set name = ?, author = ? WHERE id = ? ;";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getId());
            pstmt.executeUpdate();

            System.out.println(book.getName() + " updated successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(User user) {

        try {

            String sql = "DELETE FROM users WHERE id = ? ;";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            pstmt.executeUpdate();

            System.out.println(user.getUsername() + " deleted successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteBook(Book book) {

        try {

            String sql = "DELETE FROM books WHERE id = ? ;";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, book.getId());
            pstmt.executeUpdate();

            System.out.println(book.getName() + " deleted successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getUser(int id) {

        User cUser = null;

        for (User user : getUsers()) {
            if (user.getId() == id) {
                cUser = user;
                break;
            }
        }

        return cUser;
    }

    public Book getBook(int id) {

        Book book = null;

        for (Book _book : getBooks()) {
            if (_book.getId() == id) {
                book = _book;
                break;
            }
        }

        return book;
    }

    public User getUser(String username) {

        User cUser = null;

        for (User user : getUsers()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                cUser = user;
                break;
            }
        }

        return cUser;
    }

}
