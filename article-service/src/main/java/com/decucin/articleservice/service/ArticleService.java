package com.decucin.articleservice.service;


import com.decucin.articleservice.vo.params.ArticleParam;
import com.decucin.articleservice.vo.params.PageParam;
import com.decucin.common.vo.Result;

import java.io.IOException;


public interface ArticleService {
    Result findAll(PageParam pageParam);

    Result findArticleByCategory(PageParam pageParam, Long categoryId);

    Result findArticleInfo(Long id);

    Result likeArticle(String token, Long articleId);

    Result notLikeArticle(String token, Long articleId);

    Result addCommentCount(Long articleId);

    Result deleteCommentCount(Long articleId);

    Result search(String key) throws IOException;

    Result addArticle(String token, ArticleParam articleParam);
}
