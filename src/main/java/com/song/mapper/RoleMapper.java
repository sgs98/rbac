package com.song.mapper;

import com.song.model.Role;

import java.util.List;

/**
 *
 * @author song
 * @data 2020/3/15
 */
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> loadAllRoles();

    List<Integer> getMidsByRid(Integer rid);
}