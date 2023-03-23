package mentees.jamilxt.borrowmybook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/after-login-dashboard")
    public ModelAndView afterLoginAdminPanel() {
        var modelAndView = new ModelAndView("dashboard/index");
        modelAndView.addObject("pageTitle", "Dashboard");
        return modelAndView;
    }

}
