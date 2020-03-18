package com.song.service;

import com.song.mapper.MenuMapper;
import com.song.model.Menu;
import com.song.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author song
 * @data 2020/3/15
 */
@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> loadAllMenuByUid() {
        return menuMapper.loadAllMenuByUid(UserUtils.getCurrentUser().getId());
    }

    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus(1);
    }

    public List<Menu> loadAllOneMenu() {
        return menuMapper.loadAllOneMenu();
    }

    public Integer addMenu(Menu menu) {
        menu.setRequireAuth(true);
        return menuMapper.insertSelective(menu);
    }

    public Integer updateMenu(Menu menu) {
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    public Integer deleteMenuById(Integer id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    public Integer checkIsChild(Integer id) {
        return menuMapper.checkIsChild(id);
    }
}
