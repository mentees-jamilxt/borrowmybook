package mentees.jamilxt.borrowmybook.controller.roles;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.model.domain.Role;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateRoleRequest;
import mentees.jamilxt.borrowmybook.service.RoleService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ModelAndView getRoles() {
        var modelAndView = new ModelAndView("rolelist/role-list");
        Page<Role> roles = roleService.getRoles(Pageable.unpaged());
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView createRolePage() {
        var modelAndView = new ModelAndView("rolelist/role-new-form");
        var createRoleRequest = new CreateRoleRequest();
        modelAndView.addObject("role", createRoleRequest);
        return modelAndView;
    }

    @PostMapping
    public String createRole(@ModelAttribute CreateRoleRequest request) {
        roleService.createRole(request);
        return "redirect:/roles";
    }

    @GetMapping("{id}/update")
    public ModelAndView updateRolePage(@PathVariable UUID id) {
        var mav = new ModelAndView("rolelist/role-update-form");
        var role = roleService.getRole(id);
        mav.addObject("role", role);
        return mav;
    }

    @PostMapping("{id}/update")
    public ModelAndView updateRole(@ModelAttribute CreateRoleRequest request, @PathVariable UUID id) {
        var mav = new ModelAndView("rolelist/role-update-form");
        var role = roleService.getRole(id);
        //role.setId(id);
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        roleService.updateRole(request);
        return mav;
    }

    @GetMapping("{id}/delete")
    public String deleteRole(@PathVariable UUID id) {
        roleService.deleteRole(id);
        return "redirect:/roles";
    }

}
