package jada.books.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Repository {

    @Autowired
    private DataSource dataSource;

    public void addUser() {

    }

    //public Users(String firstName, String lastName, String address, String city, String email, String password) {


        public Users loginUser(String username, String pw) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT FirstName, LastName, Address, City, Email, Password FROM Users WHERE Email = ? AND Password = ?")) {
            ps.setString(1, username);
            ps.setString(2, pw);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                else return new Users(rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Address"), rs.getString("City"), rs.getString("Email"), rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getBook(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO DBO.UserPurchase (UserID, BookID) " +
                     "VALUES (1, ?) ", new String[] {"UserID"})) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getBookIDsForUser(Users userObj) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT BookID FROM DBO.UserPurchase WHERE UserID = ?")) {
                 ps.setInt(1, userObj.getUserID());
                 ResultSet rs = ps.executeQuery();
                 List<String> bookIdList = new ArrayList<>();
                 while (rs.next()) {
                     bookIdList.add(rs.getString("BookID"));
                 }
                 return bookIdList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
