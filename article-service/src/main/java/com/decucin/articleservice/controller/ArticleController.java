package com.decucin.articleservice.controller;


import com.decucin.articleservice.service.ArticleService;
import com.decucin.articleservice.vo.params.ArticleParam;
import com.decucin.articleservice.vo.params.PageParam;
import com.decucin.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @author ：decucin
 * @date ：Created in 2021/10/20 11:22
 * @description：与文章有关功能
 * @modified By：
 * @version: 1.0$
 */
@RestController
@RequestMapping("article/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("all")
    public Result findAll(PageParam pageParam){
        return articleService.findAll(pageParam);
    }

    @GetMapping("category/{id}")
    public Result findArticleByCategory(PageParam pageParam, @PathVariable("id") Long categoryId){
        return articleService.findArticleByCategory(pageParam, categoryId);
    }

    @GetMapping("{id}")
    public Result findArticleInfo(@PathVariable("id") Long id){
        return articleService.findArticleInfo(id);
    }

    @PostMapping("{id}/like")
    public Result likeArticle(@RequestHeader("Authorization") String token, @PathVariable("id") Long articleId){
        return articleService.likeArticle(token, articleId);
    }

    @DeleteMapping("{id}/notLike")
    public Result notLikeArticle(@RequestHeader("Authorization") String token, @PathVariable("id") Long articleId){
        return articleService.notLikeArticle(token, articleId);
    }

//    @RequestMapping("key")
//    public Result search(@PathVariable("key") String key) throws IOException {
//        return articleService.search(key);
//
//    }

    @PostMapping("add")
    public Result addArticle(@RequestHeader("Authorization") String token, @RequestBody ArticleParam articleParam){
        return articleService.addArticle(token, articleParam);
    }
}
