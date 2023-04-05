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
import org.springframework.ui.Model;
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

    @GetMapping
    public ModelAndView getBooks(@RequestParam(defaultValue = "0") int page, Principal principal) {
        var modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("pageTitle", "Book List");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        Page<Book> books = bookService.getBooks(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        modelAndView.addObject("books", books);
        modelAndView.addObject("pagesForPagination", books);
        modelAndView.addObject("url", "/books");
        return modelAndView;
    }

    @GetMapping("/{id}/")
    public ModelAndView getBook(@PathVariable UUID id, Principal principal) {
        var modelAndView = new ModelAndView("/book/single");
        modelAndView.addObject("pageTitle", "Book Details");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        Book book = bookService.getBook(id);
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createBookPage(Principal principal) {
        var modelAndView = new ModelAndView("book/new-book");
        modelAndView.addObject("pageTitle", "Book Add");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        var createBookRequest = new CreateBookRequest();
        modelAndView.addObject("book", createBookRequest);
        return modelAndView;
    }

    @PostMapping
    public String createBook(@ModelAttribute CreateBookRequest request) {
        bookService.createBook(request);
        return "redirect:/books";
    }

    @GetMapping("/{id}/update")
    public ModelAndView updateBookPage(@PathVariable UUID id, Principal principal) {
        var modelAndView = new ModelAndView("book/update-book");
        modelAndView.addObject("pageTitle", "Role Update");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        var book = bookService.getBook(id);
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @PostMapping("/update")
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
