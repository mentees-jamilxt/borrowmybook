package mentees.jamilxt.borrowmybook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

//    @GetMapping
//    public String home() {
//        return "dashboard/index";
//    }

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String viewLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/after-login-dashboard")
    public String afterLogin() {
        return "dashboard/index";
    }

}
