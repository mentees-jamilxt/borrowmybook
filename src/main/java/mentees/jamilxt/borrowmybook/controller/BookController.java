package mentees.jamilxt.borrowmybook.controller;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.model.domain.Book;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateBookRequest;
import mentees.jamilxt.borrowmybook.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ModelAndView getBooks() {
        var modelAndView = new ModelAndView("book/view-books");
        Page<Book> books = bookService.getBooks(Pageable.unpaged());
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView createBookPage() {
        var modelAndView = new ModelAndView("book/add-book");
        var createBookRequest = new CreateBookRequest();
        modelAndView.addObject("book", createBookRequest);
        return modelAndView;
    }

    @PostMapping
    public String createBook(@ModelAttribute CreateBookRequest request) {
        bookService.createBook(request);
        return "redirect:/books";
    }

    @GetMapping("{id}/update")
    public ModelAndView updateBookPage(@PathVariable UUID id) {
        var mav = new ModelAndView("book/add-book");
        var book = bookService.getBook(id);
        mav.addObject("book", book);
        return mav;
    }

//    @PostMapping
//    public String updateBook(@ModelAttribute CreateBookRequest request) {
//        bookService.updateBook(request);
//        return "redirect:/books";
//    }

    @GetMapping("{id}/delete")
    public String deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

}
