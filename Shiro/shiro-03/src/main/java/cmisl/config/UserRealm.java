package cmisl.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

// 自定义Realm
public class UserRealm extends AuthorizingRealm {
    // 执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行 => 授权逻辑PrincipalCollection");
        return null;
    }

    // 执行认证逻辑
// 执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了 => 认证逻辑AuthenticationToken");

        // 数据库的用户名和密码
        String name = "shiro_env";
        String password = "shiro_env";
        // 1.判断用户名
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        if (!userToken.getUsername().equals(name)){
            // 用户名不存在
            // shiro底层就会抛出 UnknownAccountException
            return null;
        }
        // 2. 验证密码,我们可以使用一个AuthenticationInfo实现类SimpleAuthenticationInfo
        //  shiro会自动帮我们验证！重点是第二个参数就是要验证的密码！
        return new SimpleAuthenticationInfo("", password, "");

    }
}