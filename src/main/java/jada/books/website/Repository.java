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

    public boolean loginUser(String username, String pw) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT Email, Password FROM Users WHERE Email = ? AND Password = ?")) {
            ps.setString(1, username);
            ps.setString(2, pw);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return false;
                else return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
