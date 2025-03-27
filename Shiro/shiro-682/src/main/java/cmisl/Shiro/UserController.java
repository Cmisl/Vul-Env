package cmisl.Shiro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/")
    public String home() {
        return "redirect:/home.html";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "redirect:/login.html";
    }

    @GetMapping("/unauthorized")
    public String unauthorizedPage() {
        return "redirect:/unauthorized.html";
    }

    @GetMapping("/Shiro682")
    @ResponseBody
    public String Shiro682() {
        // 返回简单的 HTML 页面内容
        return "<html>\n" +
                "<head>\n" +
                "    <title>Shrio682 Page</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Welcome to Shrio682 Page</h1>\n" +
                "    <p>This is a simple HTML page served by Spring Boot.</p>\n" +
                "</body>\n" +
                "</html>";

    }

    @PostMapping("/toLogin")
    public String doLoginPage(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(name="rememberme", defaultValue="") String rememberMe){
        if(ShiroUtil.login(username, password)){
            if(ShiroUtil.hasRole("admin")){
                return "redirect:/admin.html";
            } else if (ShiroUtil.hasRole("user")) {
                return "redirect:/user.html";
            }
        }
        return "redirect:/unauthorized.html";
    }

}
