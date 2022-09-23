package com.decucin.articleservice.dao.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/19 10:53
 * @description：对应数据库表decucin_category
 * @modified By：
 * @version: 1.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String classifyName;
    private String introduction;
}
