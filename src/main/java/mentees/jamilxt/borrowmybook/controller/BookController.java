package mentees.jamilxt.borrowmybook.controller;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.model.domain.Book;
import mentees.jamilxt.borrowmybook.model.domain.BookCategory;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateBookRequest;
import mentees.jamilxt.borrowmybook.model.enums.BookStatus;
import mentees.jamilxt.borrowmybook.service.BookCategoryService;
import mentees.jamilxt.borrowmybook.service.BookService;
import mentees.jamilxt.borrowmybook.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

import static mentees.jamilxt.borrowmybook.constant.AppConstant.DEFAULT_PAGE_SIZE;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;
    private final UserService userService;
    private final BookCategoryService bookCategoryService;

    @GetMapping
    public ModelAndView getBooks(@RequestParam(defaultValue = "0") int page, Principal principal) {
        var modelAndView = new ModelAndView("book/list");
        Page<Book> books = bookService.getBooks(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        modelAndView.addObject("pageTitle", "Book List");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        modelAndView.addObject("books", books);
        modelAndView.addObject("pagesForPagination", books);
        modelAndView.addObject("url", "/books");
        return modelAndView;
    }

    @GetMapping("/{id}/")
    public ModelAndView getBook(@PathVariable UUID id, Principal principal) {
        var modelAndView = new ModelAndView("book/single");
        Book book = bookService.getBook(id);
        modelAndView.addObject("pageTitle", "Book Details");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createBookPage(Principal principal) {
        var modelAndView = new ModelAndView("book/new-book");
        var createBookRequest = new CreateBookRequest();
        Page<BookCategory> bookCategories = bookCategoryService.getBookCategories(Pageable.unpaged());
        modelAndView.addObject("categories", bookCategories);
        modelAndView.addObject("status", BookStatus.values());
        modelAndView.addObject("pageTitle", "Add Book");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        modelAndView.addObject("book", createBookRequest);
        return modelAndView;
    }

    @PostMapping
    public String createBook(@Valid @ModelAttribute("book") CreateBookRequest request, BindingResult bindingResult, Model model, Principal principal) {
        Page<BookCategory> bookCategories = bookCategoryService.getBookCategories(Pageable.unpaged());
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("pageTitle", "Add Book");
                model.addAttribute("loggedInUser", userService.getLoggedInUser(principal));
                model.addAttribute("book", request);
                model.addAttribute("categories", bookCategories);
                model.addAttribute("status", BookStatus.values());
                return "book/new-book";
            }
            bookService.createBook(request);
            return "redirect:/books";
        } catch (Exception exception) {
            return "redirect:/books/create";
        }
    }

    @GetMapping("/{id}/update")
    public ModelAndView updateBookPage(@PathVariable UUID id, Principal principal) {
        var modelAndView = new ModelAndView("book/update-book");
        var book = bookService.getBook(id);
        Page<BookCategory> bookCategories = bookCategoryService.getBookCategories(Pageable.unpaged());
        modelAndView.addObject("categories", bookCategories);
        modelAndView.addObject("status", BookStatus.values());
        modelAndView.addObject("pageTitle", "Update Book");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
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
