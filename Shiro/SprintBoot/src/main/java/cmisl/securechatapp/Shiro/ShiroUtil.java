package cmisl.securechatapp.Shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {

    //shiro的认证、授权操作都需要先将User包装为Subject，通过Subject来操作
    public static boolean login(String username,String password) {
        Subject subject = SecurityUtils.getSubject();
        //用token封装用户信息
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);  //将token与Realm中的安全数据对比。返回值是void，如果找不到匹配，直接抛出异常
        } catch (AuthenticationException e) {  //Realm中没有匹配的用户
            return false;
        }
        return true;
    }

    public static boolean hasRole(String role) {
        Subject subject = SecurityUtils.getSubject();
        return subject.hasRole(role);
    }

}