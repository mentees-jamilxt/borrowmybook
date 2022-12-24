package mentees.jamilxt.borrowmybook.controllers.userrole;

import mentees.jamilxt.borrowmybook.entity.Role;
import mentees.jamilxt.borrowmybook.entity.User;
import mentees.jamilxt.borrowmybook.repositories.RoleRepository;
import mentees.jamilxt.borrowmybook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user-role")
public class RoleList {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    // Extra data go through model attribute before all other controller run.
    @ModelAttribute("loggedInUser")
    public User extraData(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

    // Role List
    @GetMapping("/list")
    public String roleList(Model model) {
        model.addAttribute("pageTitle", "Role List");
        model.addAttribute("role", roleRepository.findAll());
        return "userrole/role-list";
    }

    // Open form for new role
    @GetMapping("/new")
    public String newRoleForm(Model model) {
        model.addAttribute("pageTitle", "Add Role Form");
        model.addAttribute("role", new Role());
        return "userrole/role-new-form";
    }

    // Process new role form
    @PostMapping("/save")
    public String updateRoleForm(@ModelAttribute("role") Role role) {
        roleRepository.save(role);
        return "redirect:/user-role/list";
    }

    // Open form for role Update
    @GetMapping("/edit/{id}")
    public String editRoleForm(@PathVariable Integer id, Model model) {
        model.addAttribute("pageTitle", "Update  Role Information");
        model.addAttribute("role", roleRepository.findById(id).get());
        return "userrole/role-update-form";
    }

    // Process  updated role form
    @PostMapping("/update/{id}")
    public String updateRole(@PathVariable Integer id, @ModelAttribute("role") Role role) {
        Role existingRole = roleRepository.findById(id).get();
        existingRole.setId(id);
        existingRole.setName(role.getName());
        existingRole.setDescription(role.getDescription());
        roleRepository.save(existingRole);
        return "redirect:/user-role/list";
    }

    // Delete the Role information and confirm before delete.
    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Integer id) {
        roleRepository.deleteById(id);
        return "redirect:/user-role/list";
    }

}
