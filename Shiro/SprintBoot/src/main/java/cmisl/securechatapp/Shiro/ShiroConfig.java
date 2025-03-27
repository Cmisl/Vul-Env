package cmisl.securechatapp.Shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public SecurityManager securityManager(ShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm); // 设置自定义的Realm
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 配置登录和未授权的页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        // 配置路径权限
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/admin.html", "authc, roles[admin]");
        filterChainDefinitionMap.put("/admin", "authc, roles[admin]");
        filterChainDefinitionMap.put("/admin/**", "authc, roles[admin]");
        filterChainDefinitionMap.put("/user.html", "authc, roles[user]");
        filterChainDefinitionMap.put("/user", "authc, roles[user]");
        filterChainDefinitionMap.put("/user/**", "authc, roles[user]");
        filterChainDefinitionMap.put("/logout", "anon");
        filterChainDefinitionMap.put("/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public ShiroRealm shiroRealm() {
        return new ShiroRealm();
    }
}

