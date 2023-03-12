package mentees.jamilxt.borrowmybook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @GetMapping
    public ModelAndView getUsers() {
    	var modelAndView = new ModelAndView("user/view-users");
    	modelAndView.addObject("title", "View Users");
        return modelAndView;
    }
    
    @GetMapping("/create")
    public ModelAndView viewCreateUserPage() {
    	var modelAndView = new ModelAndView("user/create-user");
        modelAndView.addObject("title", "Create User");
        return modelAndView;
    }
    
}
