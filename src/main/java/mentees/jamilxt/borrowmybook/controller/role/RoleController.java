package mentees.jamilxt.borrowmybook.controller.role;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.model.domain.Role;
import mentees.jamilxt.borrowmybook.model.domain.User;
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
    
    public void loadUserDetails(Model model, Principal principal) {
		String username = principal.getName();
		User loggedInUser = userService.getUserByUsername(username);
		model.addAttribute("loggedInUser", loggedInUser);
	}

    @GetMapping
    public ModelAndView getRoles(@RequestParam(defaultValue = "0") int page, Model model, Principal principal) {
        Page<Role> roles = roleService.getRoles(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        var modelAndView = new ModelAndView("role/list");
        loadUserDetails(model, principal);
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("pageTitle", "Role List");
        modelAndView.addObject("pagesForPagination", roles);
        modelAndView.addObject("url", "/roles");
        return modelAndView;
    }

    @GetMapping("/{id}/")
    public ModelAndView getRole(@PathVariable UUID id, Model model, Principal principal) {
    	var modelAndView = new ModelAndView("/role/single");
    	loadUserDetails(model, principal);
        Role role = roleService.getRole(id);       
        modelAndView.addObject("role", role);
        modelAndView.addObject("pageTitle", "Role Details");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createRolePage(Model model, Principal principal) {
        var modelAndView = new ModelAndView("role/new-role");
        loadUserDetails(model, principal);
        var createRoleRequest = new CreateRoleRequest();
        modelAndView.addObject("role", createRoleRequest);
        modelAndView.addObject("pageTitle", "Add Role");
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
				loadUserDetails(model, principal);
		    	model.addAttribute("pageTitle", "Add Role");
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
    public ModelAndView updateRolePage(@PathVariable UUID id, Model model, Principal principal) {
        var modelAndView = new ModelAndView("role/update-role");
        loadUserDetails(model, principal);
        var role = roleService.getRole(id);
        modelAndView.addObject("role", role);
        modelAndView.addObject("pageTitle", "Update Update");
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
				System.out.println(bindingResult.toString());
				loadUserDetails(model, principal);
		    	model.addAttribute("pageTitle", "Update Role");
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
