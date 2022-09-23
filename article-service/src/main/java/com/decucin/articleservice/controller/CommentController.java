package com.decucin.articleservice.controller;


import com.decucin.articleservice.service.CommentService;
import com.decucin.articleservice.vo.params.CommentParam;
import com.decucin.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/22 10:55
 * @description：与评论有关功能
 * @modified By：
 * @version: 1.0$
 */
@RestController
@RequestMapping("article/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("{id}")
    public Result findByArticleId(@PathVariable("id") Long articleId){
        return commentService.findByArticleId(articleId);
    }

    @PostMapping("like/{id}")
    public Result likeComment(@RequestHeader("Authorization") String token,@PathVariable("id") Long commentId){
        return commentService.likeComment(token, commentId);
    }

    @DeleteMapping("notLike/{id}")
    public Result notLikeComment(@RequestHeader("Authorization") String token, @PathVariable("id") Long commentId) {
        return commentService.notLikeComment(token, commentId);
    }

    @PostMapping("add/{articleId}")
    public Result addCommentToArticle(@RequestHeader("Authorization") String token, @RequestBody CommentParam commentParam, @PathVariable("articleId") Long articleId){
        return commentService.addCommentToArticle(token, commentParam, articleId);
    }

    @DeleteMapping("delete/{articleId}/{categoryId}")
    public Result deleteCommentToArticle(@RequestHeader("Authorization") String token,@PathVariable("categoryId") Long commentId, @PathVariable("articleId") Long articleId){
        return commentService.deleteCommentToArticle(token, commentId, articleId);
    }
}
