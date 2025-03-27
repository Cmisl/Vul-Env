package cmisl.securechatapp.Shiro;

import cmisl.securechatapp.mapper.UserMapper;
import cmisl.securechatapp.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    // 认证方法，验证用户身份
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userMapper.findByUsername(username);

        if (user == null) {
            throw new UnknownAccountException("用户名不存在！");
        }

        // 验证密码
        if (!PasswordUtils.matches(new String((char [])token.getCredentials()), user.getPassword())) {
            throw new IncorrectCredentialsException("密码错误！");
        }


        // 使用 BCrypt 或其他加密算法来验证密码
        return new SimpleAuthenticationInfo(
                user.getUsername(),
                new String((char [])token.getCredentials()),
                getName()
//                ""
        );
    }

    // 授权方法，验证用户的角色和权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        User user = userMapper.findByUsername(username);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        if (user != null) {
            // 将用户的角色添加到授权信息
            String[] roles = user.getRoles().split(",");
            for (String role : roles) {
                authorizationInfo.addRole(role);
            }
        }

        return authorizationInfo;
    }
}
