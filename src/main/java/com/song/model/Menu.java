package com.song.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author song
 * @data 2020/3/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconCls;

    private Boolean keepAlive;

    private Boolean requireAuth;

    private Integer parentId;

    private Boolean enabled;

    private Meta meta;

    private List<Role> roles;

    private List<Menu> children;

}