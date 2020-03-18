package com.song.mapper;

import com.song.model.UserRole;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author song
 * @data 2020/3/15
 */
public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    Integer addUserRole(@Param("uid") Integer uid, @Param("rids") Integer[] rids);

    void deleteByUid(Integer uid);
}