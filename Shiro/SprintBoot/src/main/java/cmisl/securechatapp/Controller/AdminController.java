package cmisl.securechatapp.Controller;

import cmisl.securechatapp.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    static List<Book> books = new ArrayList<>();
    static Long bookIdCounter = 1L;

    @PostConstruct
    public void init() {
        //初始化一些图书数据
        books.add(new Book(bookIdCounter++, "Java编程思想", "Bruce Eckel", "9787111494685"));
        books.add(new Book(bookIdCounter++, "Spring实战", "Craig Walls", "9787111498812"));
        books.add(new Book(bookIdCounter++, "计算机网络", "谢希仁", "9787111596764"));
    }

    // 管理图书页面
    @GetMapping("/admin/manage-books")
    public String manageBooks(Model model) {
        model.addAttribute("books", books);
        return "admin/manage-books";
    }
}
