package cmisl.securechatapp.Controller;

import cmisl.securechatapp.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/admin/books")
public class AdminBookController {

    public static List<Book> books = AdminController.books;
    private Long bookIdCounter = AdminController.bookIdCounter; // 用于生成唯一 ID

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", books);
        return "admin/manage-books"; // 返回图书管理页面
    }

    @GetMapping("/add-book")
    public String showAddBookForm() {
        return "admin/add-book"; // 返回添加图书的页面
    }

    @PostMapping("/add-book")
    public String addBook(@RequestParam String title, @RequestParam String author, @RequestParam String isbn) {
        bookIdCounter = AdminController.bookIdCounter;
        books.add(new Book(AdminController.bookIdCounter++, title, author, isbn)); // 添加新书并自增 ID
        return "redirect:/admin/books"; // 添加后重定向到书籍列表
    }

    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id)); // 根据 ID 删除书籍
        return "redirect:/admin/books"; // 删除后重定向到书籍列表
    }
}
