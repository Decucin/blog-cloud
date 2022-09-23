package com.decucin.articleservice.vo;

import com.decucin.articleservice.dao.pojo.Article;
import com.decucin.articleservice.dao.pojo.Tag;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/21 21:35
 * @description：这个类用来保存前端显示的文章信息
 * @modified By：
 * @version: 1.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private Integer commentCount;
    private Date createDate;
    private String summary;
    private String title;
    private Integer viewCount;
    private Integer weight;
    private String author;
    private ArticleBodyVo body;
    private String category;
    private Integer likeCount;
    private List<Tag> tags;

}
