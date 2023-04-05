package mentees.jamilxt.borrowmybook.controller.role;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.model.domain.Role;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateRoleRequest;
import mentees.jamilxt.borrowmybook.service.RoleService;
import mentees.jamilxt.borrowmybook.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.UUID;

import javax.validation.Valid;

import static mentees.jamilxt.borrowmybook.constant.AppConstant.DEFAULT_PAGE_SIZE;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/roles")
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;

    @GetMapping
    public ModelAndView getRoles(@RequestParam(defaultValue = "0") int page, Principal principal) {
        var modelAndView = new ModelAndView("role/list");
        modelAndView.addObject("pageTitle", "Role List");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        Page<Role> roles = roleService.getRoles(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("pagesForPagination", roles);
        modelAndView.addObject("url", "/roles");
        return modelAndView;
    }

    @GetMapping("/{id}/")
    public ModelAndView getRole(@PathVariable UUID id, Principal principal) {
    	var modelAndView = new ModelAndView("/role/single");
    	modelAndView.addObject("pageTitle", "Role Details");
    	modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        Role role = roleService.getRole(id);       
        modelAndView.addObject("role", role);        
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createRolePage(Principal principal) {
        var modelAndView = new ModelAndView("role/new-role");
        modelAndView.addObject("pageTitle", "Add Role");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        var createRoleRequest = new CreateRoleRequest();
        modelAndView.addObject("role", createRoleRequest);
        return modelAndView;
    }

    @PostMapping
    public String createRole(
    	@Valid @ModelAttribute("role") CreateRoleRequest request, 
    	BindingResult bindingResult,
    	Model model,
    	Principal principal
    ) {
    	try {
			if(bindingResult.hasErrors()) {
		    	model.addAttribute("pageTitle", "Add Role");
		    	model.addAttribute("loggedInUser", userService.getLoggedInUser(principal));
				model.addAttribute("role", request);
				return "role/new-role";
			}
			roleService.createRole(request);
	        return "redirect:/roles";
		} 
    	catch (Exception e) {
			return "redirect:/roles/create";
		}
    }

    @GetMapping("/{id}/update")
    public ModelAndView updateRolePage(@PathVariable UUID id, Principal principal) {
        var modelAndView = new ModelAndView("role/update-role");
        modelAndView.addObject("pageTitle", "Update Role");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        var role = roleService.getRole(id);
        modelAndView.addObject("role", role);      
        return modelAndView;
    }

    @PostMapping("/update")
    public String updateRole(
    	@Valid @ModelAttribute("role") CreateRoleRequest request,
    	BindingResult bindingResult,
    	Model model,
    	Principal principal
    ) {
    	try {
			if(bindingResult.hasErrors()) {
				model.addAttribute("pageTitle", "Update Role");
				model.addAttribute("loggedInUser", userService.getLoggedInUser(principal));
				model.addAttribute("role", request);
				return "role/update-role";
			}
			roleService.updateRole(request);
	        return "redirect:/roles";
		} 
    	catch (Exception e) {
			return "redirect:/roles";
		}    
    }

    @GetMapping("{id}/delete")
    public String deleteRole(@PathVariable UUID id) {
        roleService.deleteRole(id);
        return "redirect:/roles";
    }

}
