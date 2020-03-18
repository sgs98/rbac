package com.song.mapper;

import com.song.model.Role;
import com.song.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author song
 * @data 2020/3/15
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User loadUserByUsername(String username);

    List<Role> getHrRolesById(Integer id);

    List<User> loadAllUserWithRole(@Param("keywords") String keywords, @Param("id") Integer id);

    User currentUser(@Param("id") Integer id);

    void updatePic(User user);

}