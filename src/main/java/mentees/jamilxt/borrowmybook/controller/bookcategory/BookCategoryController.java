package mentees.jamilxt.borrowmybook.controller.bookcategory;

import java.security.Principal;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.constant.AppConstant;
import mentees.jamilxt.borrowmybook.helper.ResponseMessage;
import mentees.jamilxt.borrowmybook.model.domain.BookCategory;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateBookCategoryRequest;
import mentees.jamilxt.borrowmybook.service.BookCategoryService;
import mentees.jamilxt.borrowmybook.service.UserService;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/book-categories")
public class BookCategoryController {
	private final UserService userService;
	private final BookCategoryService bookCategoryService;
	
	@GetMapping
	public ModelAndView getBookCategories(@RequestParam(defaultValue = "0") int page, Principal principal) {
		var modelAndView = new ModelAndView("/bookcategory/list");
		modelAndView.addObject("pageTitle", "View Book Categories");
		modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
		Page<BookCategory> bookCategories = bookCategoryService.getBookCategories(PageRequest.of(page, AppConstant.DEFAULT_PAGE_SIZE));
		modelAndView.addObject("bookCategories", bookCategories);
		modelAndView.addObject("pagesForPagination", bookCategories);
		modelAndView.addObject("url", "/book-categories");
		return modelAndView;
	}
	
	@GetMapping("/{id}")
	public ModelAndView getBookCategory(@PathVariable UUID id, Principal principal) {
		var modelAndView = new ModelAndView("/bookcategory/single");
		modelAndView.addObject("pageTitle", "Book Category Details");
		modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
		BookCategory bookCategory = bookCategoryService.getBookCategory(id);
		modelAndView.addObject("bookCategory", bookCategory);
		return modelAndView;
	}
	
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
			
			bookCategoryService.createBookCategory(request);
			return "redirect:/book-categories";
		} 
		catch (Exception e) {
			redirectAttributes.addFlashAttribute("responseMessage", new ResponseMessage("alert-danger", "Something went wrong. "+e.getMessage()));
			return "redirect:/book-categories/create";
		}
	}
	
	@GetMapping("/{id}/update")
	public ModelAndView viewUpdateBookCategoryPage(@PathVariable UUID id, Principal principal) {
		var modelAndView = new ModelAndView("/bookcategory/update-category");
		modelAndView.addObject("pageTitle", "Update Book Category");
		modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
		BookCategory bookCategory = bookCategoryService.getBookCategory(id);
		modelAndView.addObject("bookCategory", bookCategory);
		return modelAndView;
	}
	

	@PostMapping("/update")
	public String updateBookCategory(
		@Valid @ModelAttribute("bookCategory") CreateBookCategoryRequest request,
		BindingResult bindingResult,
		RedirectAttributes redirectAttributes,
		Model model,
		Principal principal
	) {
		try {
			if(bindingResult.hasErrors()) {
				model.addAttribute("pageTitle", "Update Book Category");
				model.addAttribute("loggedInUser", userService.getLoggedInUser(principal));
				model.addAttribute("bookCategory", request);
				return "bookcategory/update-category";
			}
			
			bookCategoryService.updateBookCategory(request);
			return "redirect:/book-categories";
		} 
		catch (Exception e) {
			redirectAttributes.addFlashAttribute("responseMessage", new ResponseMessage("alert-danger", "Something went wrong. " + e.getMessage()));
			return "redirect:/book-categories/" + request.getId() + "/update";
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteBookCategory(@PathVariable UUID id) {
		bookCategoryService.deleteBook(id);
		return "redirect:/book-categories";
	}
}
