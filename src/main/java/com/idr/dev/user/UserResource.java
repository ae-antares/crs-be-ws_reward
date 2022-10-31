   package com.idr.dev.user;

import com.idr.dev.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserResource {
    private UserMapper userMapper;
    public UserResource(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/all")
    public List<User> getAll(){
        return userMapper.findAll();
    }

    @GetMapping("/update")
    private List<User> update() {

        User user = new User();
        user.setName("Youtube");
        user.setSalary(2333L);

        userMapper.insert(user);

        return userMapper.findAll();
    }

    @GetMapping("/updateUsers")
    private List<User> updateUsers() {

        User user = new User();
        user.setName("Youtube");
        user.setSalary(555L);

        userMapper.update(user);
        return userMapper.findAll();

    }

    @GetMapping("/delete")
    private List<User> delete() {

        User users = new User();
        users.setName("Youtube");

        userMapper.delete(users);
        return userMapper.findAll();

    }
}
