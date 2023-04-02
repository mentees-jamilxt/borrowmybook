package mentees.jamilxt.borrowmybook.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.model.domain.User;
import mentees.jamilxt.borrowmybook.service.UserService;

@RequiredArgsConstructor
@Controller
public class HomeController {
	
	private final UserService userService;
	
	public void loadUserDetails(ModelAndView modelAndView, Principal principal) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		modelAndView.addObject("user", user);
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
    public ModelAndView afterLoginAdminPanel(Principal principal) {
        var modelAndView = new ModelAndView("dashboard/index");
        loadUserDetails(modelAndView, principal);
        modelAndView.addObject("pageTitle", "Dashboard");
        return modelAndView;
    }

}
