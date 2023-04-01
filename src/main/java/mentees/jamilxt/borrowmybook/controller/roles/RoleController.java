package mentees.jamilxt.borrowmybook.controller.roles;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.model.domain.Role;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateRoleRequest;
import mentees.jamilxt.borrowmybook.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

import static mentees.jamilxt.borrowmybook.constant.AppConstant.DEFAULT_PAGE_SIZE;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ModelAndView getRoles(@RequestParam(defaultValue = "0") int page) {
        Page<Role> roles = roleService.getRoles(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        var modelAndView = new ModelAndView("role/list");
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("pageTitle", "Role List");
        modelAndView.addObject("pagesForPagination", roles);
        modelAndView.addObject("url", "/roles");
        return modelAndView;
    }

    @GetMapping("/{id}/")
    public ModelAndView getRole(@PathVariable UUID id) {
        Role role = roleService.getRole(id);
        var modelAndView = new ModelAndView("/role/single");
        modelAndView.addObject("role", role);
        modelAndView.addObject("pageTitle", "Role Details");
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView createRolePage() {
        var modelAndView = new ModelAndView("role/new-role");
        var createRoleRequest = new CreateRoleRequest();
        modelAndView.addObject("role", createRoleRequest);
        modelAndView.addObject("pageTitle", "Role Add");
        return modelAndView;
    }

    @PostMapping
    public String createRole(@ModelAttribute CreateRoleRequest request) {
        roleService.createRole(request);
        return "redirect:/roles";
    }

    @GetMapping("{id}/update")
    public ModelAndView updateRolePage(@PathVariable UUID id) {
        var modelAndView = new ModelAndView("role/update-role");
        var role = roleService.getRole(id);
        modelAndView.addObject("role", role);
        modelAndView.addObject("pageTitle", "Role Update");
        return modelAndView;
    }

    @PostMapping("update")
    public String updateRole(@ModelAttribute CreateRoleRequest request) {
        roleService.updateRole(request);
        return "redirect:/roles";
    }

    @GetMapping("{id}/delete")
    public String deleteRole(@PathVariable UUID id) {
        roleService.deleteRole(id);
        return "redirect:/roles";
    }

}
