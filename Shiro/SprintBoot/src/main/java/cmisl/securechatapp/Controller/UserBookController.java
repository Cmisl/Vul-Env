//package cmisl.securechatapp.Controller;
//
//import cmisl.securechatapp.model.Book;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/user/books")
//public class UserBookController {
//
//    private List<Book> books = AdminController.books;
//
//    // 显示图书列表
//    @GetMapping
//    public String listBooks(Model model) {
//        model.addAttribute("books", books);
//        return "user/book-list"; // 返回用户的图书列表页面
//    }
//
//    // 处理图书借阅
//    @GetMapping("/borrow/{id}")
//    public String borrowBook(@PathVariable Long id, Model model) {
//        Book bookToBorrow = books.stream()
//                .filter(book -> book.getId().equals(id) && !book.isBorrowed()) // 确保图书未被借出
//                .findFirst()
//                .orElse(null);
//
//        if (bookToBorrow != null) {
//            bookToBorrow.setBorrowed(true); // 设置该书为已借出
//            model.addAttribute("message", "成功借阅图书: " + bookToBorrow.getTitle());
//        } else {
//            model.addAttribute("message", "图书已被借出或未找到！");
//        }
//
//        return "user/book-borrow-result"; // 返回借阅结果页面
//    }
//}
package cmisl.securechatapp.Controller;

import cmisl.securechatapp.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/books")
public class UserBookController {

    private List<Book> books = AdminController.books;

    // 显示图书列表
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", books);
        return "user/book-list"; // 返回用户的图书列表页面
    }

    // 处理图书借阅
    @GetMapping("/borrow/{id}")
    public String borrowBook(@PathVariable Long id, Model model) {
        Book bookToBorrow = books.stream()
                .filter(book -> book.getId().equals(id) && !book.isBorrowed()) // 确保图书未被借出
                .findFirst()
                .orElse(null);

        if (bookToBorrow != null) {
            bookToBorrow.setBorrowed(true); // 设置该书为已借出
            model.addAttribute("message", "成功借阅图书: " + bookToBorrow.getTitle());
        } else {
            model.addAttribute("message", "图书已被借出或未找到！");
        }

        return "user/book-borrow-result"; // 返回借阅结果页面
    }

    // 处理图书还书
    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable Long id, Model model) {
        Book bookToReturn = books.stream()
                .filter(book -> book.getId().equals(id) && book.isBorrowed()) // 确保图书已被借出
                .findFirst()
                .orElse(null);

        if (bookToReturn != null) {
            bookToReturn.setBorrowed(false); // 设置该书为未借出
            model.addAttribute("message", "成功还书: " + bookToReturn.getTitle());
        } else {
            model.addAttribute("message", "图书未被借出或未找到！");
        }

        return "user/book-return-result"; // 返回还书结果页面
    }
}
