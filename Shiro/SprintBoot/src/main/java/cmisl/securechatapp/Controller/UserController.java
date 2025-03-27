package cmisl.securechatapp.Controller;

import cmisl.securechatapp.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private List<Book> books = AdminController.books;

    @GetMapping("/user/book-list")
    public String BooksList(Model model) {
        model.addAttribute("books", books);
        return "user/book-list";
    }
}
