package com.song.utils;

import com.song.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author song
 * @data 2020/3/15
 */
public class UserUtils {

    public static User getCurrentUser(){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
