package com.song.controller;

import com.song.model.RespBean;
import com.song.model.Role;
import com.song.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author song
 * @data 2020/3/16
 */
@Api(description="角色管理")
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @ApiOperation(value = "查询所有角色")
    @GetMapping("/")
    public List<Role> loadAllRoles(){
        return roleService.loadAllRoles();
    }
    @ApiOperation(value = "添加角色")
    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role){
        if(roleService.addRole(role)==1){
            return RespBean.ADD_SUCCESS;
        }
        return RespBean.ADD_ERROR;
    }
    @ApiOperation(value = "修改角色")
    @PutMapping("/")
    public RespBean updateRole(@RequestBody Role role){
        if(roleService.updateRole(role)==1){
            return RespBean.UPDATE_SUCCESS;
        }
        return RespBean.UPDATE_ERROR;
    }
    @ApiOperation(value = "按照ID删除角色")
    @DeleteMapping("/{id}")
    public RespBean deleteRoleById(@PathVariable Integer id){
        if(roleService.deleteRoleById(id)==1){
            return RespBean.DELETE_SUCCESS;
        }
        return RespBean.DELETE_ERROR;
    }
    @ApiOperation(value = "按照ID查询该用户已有的角色")
    @GetMapping("/getMidsByRid/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid){
        return roleService.getMidsByRid(rid);
    }
    @ApiOperation(value = "保存用户角色与菜单的关系")
    @PutMapping("/updateMenuRole")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        if(roleService.updateMenuRole(rid,mids)){
            return RespBean.UPDATE_SUCCESS;
        }
        return RespBean.UPDATE_ERROR;
    }
}
