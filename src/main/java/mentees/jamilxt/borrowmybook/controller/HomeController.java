package mentees.jamilxt.borrowmybook.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.model.domain.User;
import mentees.jamilxt.borrowmybook.service.UserService;

@RequiredArgsConstructor
@Controller
public class HomeController {
	private final UserService userService;
	
	public void loadUserDetails(Model model, Principal principal) {
		String username = principal.getName();
		User loggedInUser = userService.getUserByUsername(username);
		model.addAttribute("loggedInUser", loggedInUser);
	}

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/after-login-dashboard")
    public ModelAndView afterLoginAdminPanel(Model model, Principal principal) {
        var modelAndView = new ModelAndView("dashboard/index");
        loadUserDetails(model, principal);
        modelAndView.addObject("pageTitle", "Dashboard");
        return modelAndView;
    }

}
