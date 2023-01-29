package mentees.jamilxt.borrowmybook.controller.web;

import mentees.jamilxt.borrowmybook.model.domain.Book;
import mentees.jamilxt.borrowmybook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public ModelAndView getAllBooks(){
        ModelAndView mav = new ModelAndView("dashboard/book/view-books");
        List<Book> books = bookService.fetchAllBooks();
        mav.addObject("books",books);
        return mav;
    }

    @GetMapping("/add-book")
    public ModelAndView addBookFrom(){
        ModelAndView mav = new ModelAndView("dashboard/book/add-book");
        Book newBook = new Book();
        mav.addObject("book",newBook);
        return mav;
    }

    @PostMapping("/save-book")
    public String saveBook(@ModelAttribute Book book){
        bookService.saveBook(book);
        return "redirect:/books/all";
    }

    @GetMapping("/update-book")
    public ModelAndView showUpdateFrom(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("dashboard/book/add-book");
        Book thisBook = bookService.fetchBookById(id);
        mav.addObject("book",thisBook);
        return mav;
    }

    @GetMapping("/delete-book")
    public String deleteBook(@RequestParam Long id){
        bookService.deleteById(id);
        return "redirect:/books/all";
    }

}
