package mentees.jamilxt.borrowmybook.controller;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.exception.custom.AlreadyExistsException;
import mentees.jamilxt.borrowmybook.model.dto.response.ResponseMessage;
import mentees.jamilxt.borrowmybook.model.domain.User;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateUserRequest;
import mentees.jamilxt.borrowmybook.service.RoleService;
import mentees.jamilxt.borrowmybook.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.UUID;

import javax.validation.Valid;

import static mentees.jamilxt.borrowmybook.constant.AppConstant.DEFAULT_PAGE_SIZE;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public ModelAndView getUsers(@RequestParam(defaultValue = "0") int page, Principal principal) {
        var modelAndView = new ModelAndView("/user/list");
        Page<User> users = userService.getUsers(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        modelAndView.addObject("pageTitle", "View Users");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        modelAndView.addObject("users", users);
        modelAndView.addObject("pagesForPagination", users);
        modelAndView.addObject("url", "/users");
        return modelAndView;
    }

    @GetMapping("/{id}/")
    public ModelAndView getUser(@PathVariable UUID id, Principal principal) {
        var modelAndView = new ModelAndView("/user/single");
        User user = userService.getUserById(id);
        modelAndView.addObject("pageTitle", "User Profile");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView viewCreateUserPage(Principal principal) {
        var modelAndView = new ModelAndView("/user/new-user");
        var createUserRequest = new CreateUserRequest();
        modelAndView.addObject("pageTitle", "Add User");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        modelAndView.addObject("user", createUserRequest);
        modelAndView.addObject("roles", roleService.getRoles(Pageable.unpaged()));
        return modelAndView;
    }

    @PostMapping
    public String createUser(@Valid @ModelAttribute("user") CreateUserRequest request,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Principal principal) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("pageTitle", "Add User");
                model.addAttribute("loggedInUser", userService.getLoggedInUser(principal));
                model.addAttribute("user", request);
                model.addAttribute("roles", roleService.getRoles(Pageable.unpaged()));
                return "user/new-user";
            }

            if (request.getRoles().isEmpty()) {
                throw new Exception("Please select a role and submit again.");
            }

            User existingUser = userService.getUserByUsername(request.getEmail());
            if (existingUser != null) {
                throw new AlreadyExistsException("User already exists with email " + request.getEmail() + ".");
            }

            request.setEnable(true);
            userService.createUser(request);
            return "redirect:/users";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("responseMessage", new ResponseMessage("alert-danger", "Something went wrong. " + e.getMessage()));
            return "redirect:/users/create";
        }
    }

    @GetMapping("/{id}/update")
    public ModelAndView viewUpdateUserPage(@PathVariable UUID id, Principal principal) {
        var modelAndView = new ModelAndView("user/update-user");
        User user = userService.getUserById(id);
        modelAndView.addObject("pageTitle", "Update User");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", roleService.getRoles(Pageable.unpaged()));
        return modelAndView;
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") CreateUserRequest request,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Principal principal) {
        try {
            if (bindingResult.hasFieldErrors("id") || bindingResult.hasFieldErrors("firstName") || bindingResult.hasFieldErrors("lastName") || bindingResult.hasFieldErrors("email")) {
                model.addAttribute("pageTitle", "Update User");
                model.addAttribute("loggedInUser", userService.getLoggedInUser(principal));
                model.addAttribute("user", request);
                model.addAttribute("roles", roleService.getRoles(Pageable.unpaged()));
                return "user/update-user";
            }

            if (request.getRoles().isEmpty()) {
                throw new Exception("Please select a role and submit again.");
            }

            userService.updateUser(request);
            return "redirect:/users";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("responseMessage", new ResponseMessage("alert-danger", "Something went wrong. " + e.getMessage()));
            return "redirect:/users/" + request.getId() + "/update";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
