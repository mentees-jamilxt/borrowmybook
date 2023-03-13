package mentees.jamilxt.borrowmybook.controller;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.model.domain.User;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateUserRequest;
import mentees.jamilxt.borrowmybook.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ModelAndView getUsers() {
    	var modelAndView = new ModelAndView("user/view-users");
    	modelAndView.addObject("title", "View Users");
        Page<User> users = userService.getUsers(Pageable.unpaged());
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/{id}/{name}")
    public ModelAndView getUserById(@PathVariable UUID id) {
        var modelAndView = new ModelAndView("user/view-single-user");
        modelAndView.addObject("title", "User Profile");
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);
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
