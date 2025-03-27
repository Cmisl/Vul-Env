package cmisl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String pwd;
    private List<String> perms; // 添加权限字段

    public String getPerms() {
        if (perms == null) {
            return ""; // 如果perms为null，返回空字符串
        }
        return String.join(",", perms); // 将权限列表转换为逗号分隔的字符串
    }
}