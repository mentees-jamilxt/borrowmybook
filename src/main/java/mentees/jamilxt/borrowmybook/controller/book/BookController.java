package mentees.jamilxt.borrowmybook.controller.book;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.model.domain.Book;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateBookRequest;
import mentees.jamilxt.borrowmybook.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ModelAndView getBooks(@RequestParam(defaultValue = "0") int page) {
        Page<Book> books = bookService.getBooks(PageRequest.of(page, 1));
        var modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("books", books);
        modelAndView.addObject("pageTitle", "Book List");
        modelAndView.addObject("pagesForPagination", books);
        modelAndView.addObject("url", "/books");
        return modelAndView;
    }

    @GetMapping("/{id}/")
    public ModelAndView getBook(@PathVariable UUID id) {
        Book book = bookService.getBook(id);
        var modelAndView = new ModelAndView("/book/single");
        modelAndView.addObject("book", book);
        modelAndView.addObject("pageTitle", "Book Details");
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView createBookPage() {
        var modelAndView = new ModelAndView("book/new-book");
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
    public ModelAndView updateBookPage(@PathVariable UUID id) {
        var mav = new ModelAndView("book/update-book");
        var book = bookService.getBook(id);
        mav.addObject("book", book);
        mav.addObject("pageTitle", "Role Update");
        return mav;
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
