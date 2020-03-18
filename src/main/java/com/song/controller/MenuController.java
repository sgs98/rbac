package com.song.controller;

import com.song.model.Menu;
import com.song.model.RespBean;
import com.song.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author song
 * @data 2020/3/15
 */
@Api(description="菜单管理")
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    /**
     * 查按照id查询菜单
     * @return
     */
    @ApiOperation(value = "按照登陆用户ID查询所有可用菜单")
    @GetMapping("/")
    public List<Menu> loadAllMenuByUid(){
        return menuService.loadAllMenuByUid();
    }

    /**
     * 查询所有菜单
     * @return
     */
    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/getAllMenus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }
    /**
     * 查询一级菜单
     */
    @ApiOperation(value = "查询一级菜单")
    @GetMapping("/loadAllOneMenu")
    public List<Menu> loadAllOneMenu(){
        return menuService.loadAllOneMenu();
    }

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    @ApiOperation(value = "添加菜单")
    @PostMapping("/")
    public RespBean addMenu(@RequestBody Menu menu){
        if(menuService.addMenu(menu)==1){
          return RespBean.ADD_SUCCESS;
        }
        return RespBean.ADD_ERROR;
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @ApiOperation(value = "修改菜单")
    @PutMapping("/")
    public RespBean updateMenu(@RequestBody Menu menu){
        if(menuService.updateMenu(menu)==1){
            return RespBean.UPDATE_SUCCESS;
        }
        return RespBean.UPDATE_ERROR;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation(value = "按照ID撒删除菜单")
    @DeleteMapping("/{id}")
    public RespBean deleteMenuById(@PathVariable Integer id){
        if(menuService.deleteMenuById(id)==1){
            return RespBean.DELETE_SUCCESS;
        }
        return RespBean.DELETE_ERROR;
    }

    /**
     * 检查是否存在子级
     * @param id
     * @return
     */
    @ApiOperation(value = "检查是否存在子级")
    @GetMapping("/checkIsChild/{id}")
    public Integer checkIsChild(@PathVariable Integer id){
        return menuService.checkIsChild(id);
    }

}

