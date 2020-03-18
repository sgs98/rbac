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
public class UserRole {
    private Integer id;

    private Integer uid;

    private Integer rid;
}