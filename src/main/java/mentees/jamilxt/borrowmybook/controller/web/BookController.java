package mentees.jamilxt.borrowmybook.controller.web;

import mentees.jamilxt.borrowmybook.model.domain.Book;
import mentees.jamilxt.borrowmybook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ModelAndView getAllBooks(){
        ModelAndView mav = new ModelAndView("dashboard/book/viewBooks");
        List<Book> books = bookService.fetchAllBooks();
        mav.addObject("books",books);
        return mav;
    }

    @GetMapping("/add-book-form")
    public ModelAndView addBookFrom(){
        ModelAndView mav = new ModelAndView("dashboard/book/addBook");
        Book newBook = new Book();
        mav.addObject("book",newBook);
        return mav;
    }

    @PostMapping("/save-book")
    public String saveBook(@ModelAttribute Book book){
        bookService.saveBook(book);
        return "redirect:/dashboard/book/viewBooks";
    }

    @GetMapping("/update-book-form")
    public ModelAndView showUpdateFrom(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("dashboard/book/addBook");
        Book thisBook = bookService.fetchBookById(id);
        mav.addObject("book",thisBook);
        return mav;
    }

    @GetMapping("/delete-book")
    public String deleteBook(@RequestParam Long id){
        bookService.deleteById(id);
        return "redirect:/dashboard/book/viewBooks";
    }

}
