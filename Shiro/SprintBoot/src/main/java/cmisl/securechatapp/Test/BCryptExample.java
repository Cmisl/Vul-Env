package cmisl.securechatapp.Test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptExample {
    public static void main(String[] args) {
        // 创建 BCryptPasswordEncoder 实例
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//
        String rawPassword = "admin123";
//
        // 加密密码
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);

        // 验证密码
        String inputPassword = "admin123";
        boolean matches = passwordEncoder.matches(inputPassword, "$2a$10$JaBks6E2K.2UMlUjPUIyiO07944JPGvsOxrYRpCBgLtNpLqLdjEIy");
        System.out.println("Password matches: " + matches);


        String wrongPassword = "admin1231"; // 错误的密码
        boolean wrongMatches = passwordEncoder.matches(wrongPassword, "$2a$10$JaBks6E2K.2UMlUjPUIyiO07944JPGvsOxrYRpCBgLtNpLqLdjEIy");
        System.out.println("Password matches: " + wrongMatches); // 输出: false
    }
}
