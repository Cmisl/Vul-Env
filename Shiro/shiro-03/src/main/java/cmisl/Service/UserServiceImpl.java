package cmisl.Service;

import cmisl.mapper.UserMapper;
import cmisl.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper mapper;


    @Override
    public User queryUserByName(String name) {
        return mapper.queryUserByName(name);
    }
}