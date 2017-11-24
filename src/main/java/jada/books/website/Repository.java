package jada.books.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class Repository {

    @Autowired
    private DataSource dataSource;

    public void addUser() {

    }

    //public Users(String firstName, String lastName, String address, String city, String email, String password) {


        public Users loginUser(String username, String pw) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT UserID, FirstName, LastName, Address, City, Email, Password FROM Users WHERE Email = ? AND Password = ?")) {
            ps.setString(1, username);
            ps.setString(2, pw);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                else return new Users(
                        rs.getInt("UserID"),
                        rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("Address"), rs.getString("City"),
                        rs.getString("Email"), rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getBook(int userid, String id) {
        System.out.println("userid: " + userid + "id: " + id);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO dbo.UserPurchase (UserID, BookID) " +
                     "VALUES (?, ?) ")) {
            ps.setInt(1, userid);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
