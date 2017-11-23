package jada.books.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class Login {

    @Autowired
    private Repository repository;

    @PostMapping("/login")
    String login(@RequestParam String user, @RequestParam String pw, HttpSession currentSession) {      // Enter cookie here HTTPSession
        if (repository.loginUser(user, pw)) {
            currentSession.setAttribute(user, true);
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/BuyTheBook")
    String buyTheBook(@RequestParam String id){
        System.out.println(id);
        return "BuyTheBook";
    }

}