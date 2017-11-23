package jada.books.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD
import javax.servlet.http.HttpSession;
=======
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
>>>>>>> 72d562f97496777320d5f678421ea47637883fbb

@Controller
public class Login {

    @Autowired
    private Repository repository;
    private DataSource dataSource;

    @PostMapping("/login")
<<<<<<< HEAD
    String login(@RequestParam String user, @RequestParam String pw, HttpSession currentSession) {      // Enter cookie here HTTPSession
        if (repository.loginUser(user, pw)) {
            currentSession.setAttribute(user, true);
            return "login";
=======
    //String login(@RequestParam String user, @RequestParam String pw) {
    ModelAndView login(@RequestParam String user, @RequestParam String pw) {
        Users userObj = repository.loginUser(user, pw);
        if (userObj != null) {

            // gör anrop till databasen för att få användaren (userObject)
            // return new ModelAndView("login").addObject("user",userObject); // i html-koden heter userObject bara user

            //return "login";
            return new ModelAndView("login").addObject("user",userObj);
>>>>>>> 72d562f97496777320d5f678421ea47637883fbb
        }
        //return "redirect:/";
        return new ModelAndView("redirect:/");
    }
<<<<<<< HEAD

    @GetMapping("/BuyTheBook")
    String buyTheBook(@RequestParam String id){
        System.out.println(id);
        return "BuyTheBook";
    }

}
=======
}
>>>>>>> 72d562f97496777320d5f678421ea47637883fbb
