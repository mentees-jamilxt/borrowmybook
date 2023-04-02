package mentees.jamilxt.borrowmybook.controller.user;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.model.domain.User;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateUserRequest;
import mentees.jamilxt.borrowmybook.service.RoleService;
import mentees.jamilxt.borrowmybook.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.UUID;

import static mentees.jamilxt.borrowmybook.constant.AppConstant.DEFAULT_PAGE_SIZE;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    
    public void loadUserDetails(ModelAndView modelAndView, Principal principal) {
		String username = principal.getName();
		User loggedInUser = userService.getUserByUsername(username);
		modelAndView.addObject("loggedInUser", loggedInUser);
	}

    @GetMapping
    public ModelAndView getUsers(@RequestParam(defaultValue = "0") int page, Principal principal) {
        Page<User> users = userService.getUsers(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        var modelAndView = new ModelAndView("/user/list");
        loadUserDetails(modelAndView, principal);
        modelAndView.addObject("users", users);
        modelAndView.addObject("pageTitle", "View Users");
        modelAndView.addObject("pagesForPagination", users);
        modelAndView.addObject("url", "/users");
        return modelAndView;
    }

    @GetMapping("/{id}/")
    public ModelAndView getUser(@PathVariable UUID id, Principal principal) {
        var modelAndView = new ModelAndView("/user/single");
        loadUserDetails(modelAndView, principal);
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageTitle", "User Profile");
        return modelAndView;
    }
    
    @GetMapping("/create")
    public ModelAndView viewCreateUserPage(Principal principal) {
    	var modelAndView = new ModelAndView("/user/new-user");
    	loadUserDetails(modelAndView, principal);
        var createUserRequest = new CreateUserRequest();
        modelAndView.addObject("user", createUserRequest);
        modelAndView.addObject("roles", roleService.getRoles(Pageable.unpaged()));
        modelAndView.addObject("pageTitle", "Create User");
        return modelAndView;
    }

    @PostMapping
    public String createUser(@ModelAttribute CreateUserRequest request) {
        request.setEnable(true);
        userService.createUser(request);
        return "redirect:/users";
    }

    @GetMapping("/{id}/update")
    public ModelAndView viewUpdateUserPage(@PathVariable UUID id, Principal principal) {
        var modelAndView = new ModelAndView("user/update-user");
        loadUserDetails(modelAndView, principal);
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", roleService.getRoles(Pageable.unpaged()));
        modelAndView.addObject("pageTitle", "Update User");
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
