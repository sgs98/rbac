package com.song.service;

import com.song.mapper.UserMapper;
import com.song.mapper.UserRoleMapper;
import com.song.model.User;
import com.song.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author song
 * @data 2020/3/15
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userMapper.loadUserByUsername(username);
        if(null==user){
            throw  new UsernameNotFoundException("该用户不存在");
        }
        user.setRoles(userMapper.getHrRolesById(user.getId()));
        return user;
    }

    public List<User> loadAllUserWithRole(String keywords) {
        return userMapper.loadAllUserWithRole(keywords, UserUtils.getCurrentUser().getId());
    }


    public boolean updateUserRole(Integer uid, Integer[] rids) {
        userRoleMapper.deleteByUid(uid);
        if (rids == null || rids.length == 0) {
            return true;
        }
        Integer result=userRoleMapper.addUserRole(uid,rids);
        return result==rids.length;
    }

    public Integer deleteByUid(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public Integer updateByUid(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public User currentUser() {
        return userMapper.currentUser(UserUtils.getCurrentUser().getId());
    }

    public void updatePic(User user) {
        userMapper.updatePic(user);
    }
}
