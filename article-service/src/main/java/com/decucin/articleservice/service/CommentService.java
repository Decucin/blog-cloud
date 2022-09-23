package com.decucin.articleservice.service;


import com.decucin.articleservice.vo.params.CommentParam;
import com.decucin.common.vo.Result;

public interface CommentService {

    Result findByArticleId(Long articleId);

    Result likeComment(String token, Long commentId);

    Result notLikeComment(String token, Long commentId);

    Result addCommentToArticle(String token, CommentParam commentParam, Long articleId);

    Result deleteCommentToArticle(String token, Long commentId, Long articleId);
}
