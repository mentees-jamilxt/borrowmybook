package mentees.jamilxt.borrowmybook.controller.book;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.model.domain.Book;
import mentees.jamilxt.borrowmybook.model.domain.User;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateBookRequest;
import mentees.jamilxt.borrowmybook.service.BookService;
import mentees.jamilxt.borrowmybook.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.UUID;

import static mentees.jamilxt.borrowmybook.constant.AppConstant.DEFAULT_PAGE_SIZE;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;
    private final UserService userService;
    
    public void loadUserDetails(ModelAndView modelAndView, Principal principal) {
		String username = principal.getName();
		User loggedInUser = userService.getUserByUsername(username);
		modelAndView.addObject("loggedInUser", loggedInUser);
	}

    @GetMapping
    public ModelAndView getBooks(@RequestParam(defaultValue = "0") int page, Principal principal) {
        Page<Book> books = bookService.getBooks(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        var modelAndView = new ModelAndView("/book/list");
        loadUserDetails(modelAndView, principal);
        modelAndView.addObject("books", books);
        modelAndView.addObject("pageTitle", "Book List");
        modelAndView.addObject("pagesForPagination", books);
        modelAndView.addObject("url", "/books");
        return modelAndView;
    }

    @GetMapping("/{id}/")
    public ModelAndView getBook(@PathVariable UUID id, Principal principal) {
        Book book = bookService.getBook(id);
        var modelAndView = new ModelAndView("/book/single");
        loadUserDetails(modelAndView, principal);
        modelAndView.addObject("book", book);
        modelAndView.addObject("pageTitle", "Book Details");
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView createBookPage(Principal principal) {
        var modelAndView = new ModelAndView("book/new-book");
        loadUserDetails(modelAndView, principal);
        var createBookRequest = new CreateBookRequest();
        modelAndView.addObject("book", createBookRequest);
        modelAndView.addObject("pageTitle", "Book Add");
        return modelAndView;
    }

    @PostMapping
    public String createBook(@ModelAttribute CreateBookRequest request) {
        bookService.createBook(request);
        return "redirect:/books";
    }

    @GetMapping("{id}/update")
    public ModelAndView updateBookPage(@PathVariable UUID id, Principal principal) {
        var modelAndView = new ModelAndView("book/update-book");
        loadUserDetails(modelAndView, principal);
        var book = bookService.getBook(id);
        modelAndView.addObject("book", book);
        modelAndView.addObject("pageTitle", "Role Update");
        return modelAndView;
    }

    @PostMapping("update")
    public String updateBook(@ModelAttribute CreateBookRequest request) {
        bookService.updateBook(request);
        return "redirect:/books";
    }

    @GetMapping("{id}/delete")
    public String deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

}
