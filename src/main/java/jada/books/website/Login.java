package jada.books.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {

    @Autowired
    private Repository repository;

    @PostMapping("/login")
    String login(@RequestParam String user, @RequestParam String pw) {
        if (repository.loginUser(user, pw)) {
            return "login";
        }
        return "redirect:/";
    }

}
