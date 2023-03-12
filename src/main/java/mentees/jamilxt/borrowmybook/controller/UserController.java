package mentees.jamilxt.borrowmybook.controller;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateUserRequest;
import mentees.jamilxt.borrowmybook.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

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
        var createUserRequest = new CreateUserRequest();
        modelAndView.addObject("user", createUserRequest);
        return modelAndView;
    }

    @PostMapping
    public String createUser(@ModelAttribute CreateUserRequest request) {
        request.setEnable(true);
        userService.createUser(request);
        return "redirect:/users";
    }
}
