package com.decucin.articleservice.dao.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author ：decucin
 * @date ：Created in 2021/10/19 10:36
 * @description：对应数据库表decucin_article
 * @modified By：
 * @version: 1.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    public static final int Article_TOP = 1;
    public static final int Article_Common = 0;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private Integer commentCount;
    private Date createDate;
    private String summary;
    private String title;
    private Integer viewCount;
    private Integer weight;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long authorId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long bodyId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;
    private Integer likeCount;

}
