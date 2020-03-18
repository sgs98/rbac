package com.song.mapper;

import com.song.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author song
 * @data 2020/3/15
 */
public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> loadAllMenuByUid(Integer id);

    List<Menu> getAllMenusWithRole();

    List<Menu> getAllMenus(Integer pid);

    List<Menu> loadAllOneMenu();

    Integer checkIsChild(Integer id);
}