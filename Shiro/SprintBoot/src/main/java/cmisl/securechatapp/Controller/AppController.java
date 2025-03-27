package cmisl.securechatapp.Controller;

import cmisl.securechatapp.Shiro.ShiroUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AppController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/unauthorized")
    public String unauthorizedPage() {
        return "unauthorized";
    }

    @PostMapping("/toLogin")
    public String doLoginPage(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(name="rememberme", defaultValue="") String rememberMe){
        if(ShiroUtil.login(username, password)){
            if(ShiroUtil.hasRole("admin")){
                return "redirect:/admin";
            } else if (ShiroUtil.hasRole("user")) {
                return "redirect:/user";
            }
        }
        return "unauthorized";
    }


    @GetMapping("/logout")
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            currentUser.logout();  // 注销用户
        }
        return "redirect:/login";  // 注销后重定向到登录页面
    }
}
