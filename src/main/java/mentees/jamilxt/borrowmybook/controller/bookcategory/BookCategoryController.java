package mentees.jamilxt.borrowmybook.controller.bookcategory;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
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
}
