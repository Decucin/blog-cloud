package com.decucin.articleservice.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.decucin.articleservice.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    Boolean selectIfLike(Long userId, Long articleId);

    Long insertLike(Long userId, Long articleId);

    Long updateLike(Long userId, Long articleId);

    Long cancelLike(Long userId, Long articleId);

    void insertTag(Long articleId, Long tagId);
}
