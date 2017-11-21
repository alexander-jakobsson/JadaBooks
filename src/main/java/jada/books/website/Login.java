package jada.books.website;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {

    @RequestMapping("login")
    String login() {
        return "Log in here.";
    }

}
