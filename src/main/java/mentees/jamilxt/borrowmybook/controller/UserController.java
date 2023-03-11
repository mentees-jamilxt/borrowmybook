package mentees.jamilxt.borrowmybook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("title", "View Users");
        return "user/view-users";
    }
    
    @GetMapping("/add-user")
    public String viewCreateUserPage(Model model) {
        model.addAttribute("title", "Create User");
        return "user/create-user";
    }
    
}
