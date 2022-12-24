package mentees.jamilxt.borrowmybook.controllers;

import mentees.jamilxt.borrowmybook.entity.Role;
import mentees.jamilxt.borrowmybook.entity.RootAdmin;
import mentees.jamilxt.borrowmybook.entity.User;
import mentees.jamilxt.borrowmybook.repositories.RoleRepository;
import mentees.jamilxt.borrowmybook.service.RoleService;
import mentees.jamilxt.borrowmybook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class Home {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "Login");
        return "login";
    }


    // Create a new User
    @GetMapping("/register")
    public String addNewUserForm(Model model) {
        User user = new User();
        RootAdmin rootAdmin = new RootAdmin();
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("pageTitle", "User Registration");
        model.addAttribute("listRoles", roleService.specificRole("SiteAdmin"));
        model.addAttribute("user", user);
        model.addAttribute("rootAdmin", rootAdmin);
        return "register-site-admin";
    }

    // Process save user
    @PostMapping("/save-user")
    public String processNewUserForm(@Valid @ModelAttribute("user") User user,
                                     BindingResult bindingResult, Model model) throws IOException {

        System.out.println(user);

//        if (this.userService.getUserByEmail(user.getEmail()) != null) {
//            bindingResult.rejectValue("email", "error.user", "An account already exists for this email.");
//            model.addAttribute("listRoles", userService.listRoles());
//        } else if (user.getPassword().length() < 8) {
//            bindingResult.rejectValue("password", "error.password", "Password must Min 8 character.");
//            model.addAttribute("listRoles", userService.listRoles());
//        }
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("pageTitle", "User Registration");
//            model.addAttribute("listRoles", userService.listRoles());
//            return "redirect:/register";
//        }

//        if (!multipartFile.isEmpty()) {
//            String fileNameSelected = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//            user.setPhotos(fileNameSelected);
//            Integer userId = userService.saveUser(user).getId();
//
//            String uploadDir = "user-photos/" + userId;
//            FileUploadUtil.cleanDirectory(uploadDir);
//            FileUploadUtil.saveFile(uploadDir, fileNameSelected, multipartFile);
//        } else {
//            if (user.getPhotos().isEmpty()) {
//
//            }
//        model.addAttribute("successMsg", "successfully signed up. Please Login!");
//        model.addAttribute("successMsgType", "alert-success");

        //user.setPhotos(null);


        user.setEnabled(true);
        user.getSiteAdmin().setUser(user);
        Set<Role> roles = roleService.specificRole("SiteAdmin");
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/register";
    }




    @GetMapping("/dashboard")
    public String afterLogin(Model model, Principal principal) {
        model.addAttribute("pageTitle", "Welcome | Dashboard");
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("loggedInUser", userService.getUserByEmail(principal.getName()));
        return "dashboard";
    }

}
