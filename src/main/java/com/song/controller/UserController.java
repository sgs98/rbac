package com.song.controller;

import com.song.model.RespBean;
import com.song.model.User;
import com.song.service.UserService;
import com.song.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author song
 * @data 2020/3/16
 */
@Api(description="用户管理")
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    UserService userService;
    @ApiOperation(value = "查询所有用户，按照用户名可模糊查询")
    @RequestMapping("/")
    public List<User> loadAllUserWithRole(String keywords){
        return userService.loadAllUserWithRole(keywords);
    }
    @PutMapping("/updateUserRole")
    public RespBean updateUserRole(Integer uid,Integer[] rids){
        if(userService.updateUserRole(uid,rids)){
            return RespBean.UPDATE_SUCCESS;
        }
        return RespBean.UPDATE_ERROR;
    }
    @ApiOperation(value = "按照ID删除用户")
    @DeleteMapping("/{id}")
    public RespBean deleteByUid(@PathVariable Integer id){
        if(userService.deleteByUid(id)==1){
            return RespBean.DELETE_SUCCESS;
        }
        return RespBean.DELETE_ERROR;
    }
    @ApiOperation(value = "修改用户")
    @PutMapping("/")
    public RespBean updateByUid(@RequestBody User user){
        if(userService.updateByUid(user)==1){
            return RespBean.UPDATE_SUCCESS;
        }
        return RespBean.UPDATE_ERROR;
    }
    @ApiOperation(value = "查询当前登陆用户")
    @GetMapping("/currentUser")
    public User currentUser(){
        User user=userService.currentUser();
        return user;
    }
    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
    @ApiOperation(value = "上传图片")
    @PostMapping("/uploadFile")
    public RespBean uploadPic(MultipartFile file,HttpServletRequest req) throws IOException {
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/upload") + format;
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(folder,newName));
        String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/upload" + format + newName;

        System.out.println(url);
        User user=new User();
        user.setId(UserUtils.getCurrentUser().getId());
        user.setUserface(url);
        userService.updatePic(user);
        return RespBean.UPDATE_SUCCESS;
    }
}
