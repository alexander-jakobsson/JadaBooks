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
<<<<<<< HEAD
    //String login(@RequestParam String user, @RequestParam String pw) {
    ModelAndView login(@RequestParam String user, @RequestParam String pw, HttpSession session) {
        Users userObj;
        if (session.getAttribute("currentSession") != null) {
            userObj = (Users) session.getAttribute("currentSession");
        } else {
            userObj = repository.loginUser(user, pw);
        }

        if (userObj != null) {
            session.setAttribute("currentSession", userObj);
            // gör anrop till databasen för att få användaren (userObject)
            // return new ModelAndView("login").addObject("user",userObject); // i html-koden heter userObject bara user

            //return "login";
            return new ModelAndView("login").addObject("user",userObj);
=======
    ModelAndView login(@RequestParam String user, @RequestParam String pw) {
        Users userObj = repository.loginUser(user, pw);
        if (userObj != null) {

            return new ModelAndView("login").addObject("user", userObj);
>>>>>>> 37f57517defcaf9fbe852aba69fb431a750899a1
        }
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/BuyTheBook")
    String getBook( @RequestParam String id, HttpSession session) {
        Users userObj;
        int userID = 1;
        if (session.getAttribute("currentSession") != null) {
            userObj = (Users) session.getAttribute("currentSession");

            userID = userObj.getUserID();
        }
        repository.getBook(userID, id);

        return "BuyTheBook";
    }

    @GetMapping("/logout")
    String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
