package cmisl.mapper;

import cmisl.pojo.User;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;

@Repository
@Mapper
public interface UserMapper {

    public User queryUserByName(String name);

}