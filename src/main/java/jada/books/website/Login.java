package jada.books.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Controller
public class Login {

    @Autowired
    private Repository repository;
    private DataSource dataSource;

    @PostMapping("/login")
    //String login(@RequestParam String user, @RequestParam String pw) {
    ModelAndView login(@RequestParam String user, @RequestParam String pw) {
        Users userObj = repository.loginUser(user, pw);
        if (userObj != null) {

            // gör anrop till databasen för att få användaren (userObject)
            // return new ModelAndView("login").addObject("user",userObject); // i html-koden heter userObject bara user

            //return "login";
            return new ModelAndView("login").addObject("user",userObj);
        }
        //return "redirect:/";
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/BuyTheBook")
    String getBook(@RequestParam String id) {
        return "BuyTheBook";
    }
}
