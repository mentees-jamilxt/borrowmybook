package mentees.jamilxt.borrowmybook.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.service.UserService;

@RequiredArgsConstructor
@Controller
public class HomeController {
	private final UserService userService;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/after-login-dashboard")
    public ModelAndView afterLoginAdminPanel(Principal principal) {
        var modelAndView = new ModelAndView("dashboard/index");
        modelAndView.addObject("pageTitle", "Dashboard");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        return modelAndView;
    }

}
