package jada.books.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Controller
public class RegMember {
    @Autowired
    private DataSource dataSource;

    @PostMapping("/regMem")
    public String postMem(String inputFname, String inputLname, String inputAddress,
                        String inputCity, String inputEmail, String inputPass) throws SQLException {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO DBO.Users (FirstName,LastName,Address,City,Email,Password) " +
                     "VALUES (?,?,?,?,?,?) ", new String[] {"UserID"})) {
            ps.setString(1, inputFname);
            ps.setString(2, inputLname);
            ps.setString(3, inputAddress);
            ps.setString(4, inputCity);
            ps.setString(5, inputEmail);
            ps.setString(6, inputPass);
            ps.executeUpdate();
        } return "redirect:/validation.html";
    }
}
