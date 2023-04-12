package mentees.jamilxt.borrowmybook.controller.bookcategory;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.helper.ResponseMessage;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateBookCategoryRequest;
import mentees.jamilxt.borrowmybook.service.UserService;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/book-categories")
public class BookCategoryController {
	private final UserService userService;
	
	@GetMapping("/create")
	public ModelAndView viewCreateBookCategoryPage(Principal principal) {
		var modelAndView = new ModelAndView("/bookcategory/new-category");
		modelAndView.addObject("pageTitle", "Add Category");
		modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
		var createBookCategoryRequest = new CreateBookCategoryRequest(); 
		modelAndView.addObject("bookCategory", createBookCategoryRequest);
		return modelAndView;
	}
	
	@PostMapping
	public String createBookCategory(
		@Valid @ModelAttribute("bookCategory") CreateBookCategoryRequest request,
		BindingResult bindingResult,
		RedirectAttributes redirectAttributes,
		Model model,
		Principal principal
	) {
		try {
			if(bindingResult.hasErrors()) {
				model.addAttribute("pageTitle", "Add Category");
				model.addAttribute("loggedInUser", userService.getLoggedInUser(principal));
				model.addAttribute("bookCategory", request);
				return "bookCategory/new-category";
			}
			return "redirect:/book-categories";
		} 
		catch (Exception e) {
			redirectAttributes.addFlashAttribute("responseMessage", new ResponseMessage("alert-danger", "Something went wrong. "+e.getMessage()));
			return "redirect:/book-categories/create";
		}
	}
}
