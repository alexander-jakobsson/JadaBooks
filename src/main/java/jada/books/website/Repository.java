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
}
