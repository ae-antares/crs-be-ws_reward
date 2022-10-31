package com.idr.dev.mapper;

import com.idr.dev.user.User;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from users")
    List<User> findAll();

    @Insert("insert into users(name, salary) values(#{name}, #{salary})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty =  "id",
        before = false, resultType = Integer.class)
    void insert(User user);

    @Update("update user set salary=#{salary} where name=#{name}")
    void update(User user);

    @Delete("delete from user where name=#{name}")
    void delete(User user);
}
