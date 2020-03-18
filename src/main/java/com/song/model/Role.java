package com.song.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author song
 * @data 2020/3/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Integer id;

    private String name;

    /**
    * 角色名称
    */
    private String nameZh;
}