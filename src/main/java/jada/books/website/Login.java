package jada.books.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Controller
public class Login {
    private String currentBookID;

    @Autowired
    private Repository repository;

    @PostMapping("/login")
    ModelAndView login(@RequestParam String user, @RequestParam String pw) {
        Users userObj = repository.loginUser(user, pw);
        if (userObj != null) {

            return new ModelAndView("login").addObject("user", userObj);
        }
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/BuyTheBook")
    String getBook(@RequestParam String id) {


        repository.getBook(id);

        return "BuyTheBook";
    }

    @GetMapping("/logout")
    String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
