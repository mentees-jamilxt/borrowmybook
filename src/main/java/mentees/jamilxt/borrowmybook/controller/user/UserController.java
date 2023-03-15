package mentees.jamilxt.borrowmybook.controller.user;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.model.domain.User;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateUserRequest;
import mentees.jamilxt.borrowmybook.service.RoleService;
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
    private final RoleService roleService;

    @GetMapping
    public ModelAndView getUsers() {
    	var modelAndView = new ModelAndView("/user/list");
    	modelAndView.addObject("title", "View Users");
        Page<User> users = userService.getUsers(Pageable.unpaged());
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/{id}/{name}")
    public ModelAndView getUserById(@PathVariable UUID id) {
        var modelAndView = new ModelAndView("/user/single");
        modelAndView.addObject("title", "User Profile");
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    
    @GetMapping("/create")
    public ModelAndView viewCreateUserPage() {
    	var modelAndView = new ModelAndView("/user/new-user");
        var createUserRequest = new CreateUserRequest();
        modelAndView.addObject("user", createUserRequest);
        modelAndView.addObject("roles", roleService.getRoles(Pageable.unpaged()));
        modelAndView.addObject("title", "Create User");
        return modelAndView;
    }

    @PostMapping
    public String createUser(@ModelAttribute CreateUserRequest request) {
        request.setEnable(true);
        userService.createUser(request);
        return "redirect:/users";
    }

    @GetMapping("/{id}/update")
    public ModelAndView viewUpdateUserPage(@PathVariable UUID id) {
        var modelAndView = new ModelAndView("user/update-user");
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", roleService.getRoles(Pageable.unpaged()));
        modelAndView.addObject("title", "Update User");
        return modelAndView;
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute CreateUserRequest request) {
        userService.updateUser(request);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
