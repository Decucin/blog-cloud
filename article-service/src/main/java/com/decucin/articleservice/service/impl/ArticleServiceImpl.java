package com.decucin.articleservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.decucin.articleservice.dao.mapper.ArticleMapper;
import com.decucin.articleservice.dao.mapper.BodyMapper;
import com.decucin.articleservice.dao.mapper.CategoryMapper;
import com.decucin.articleservice.dao.pojo.Article;
import com.decucin.articleservice.dao.pojo.Body;
import com.decucin.articleservice.dao.pojo.Tag;
import com.decucin.articleservice.feign.UserFeign;
import com.decucin.articleservice.service.ArticleService;
import com.decucin.articleservice.service.BodyService;
import com.decucin.articleservice.service.TagService;
import com.decucin.articleservice.vo.ArticleVo;
import com.decucin.articleservice.vo.params.ArticleParam;
import com.decucin.articleservice.vo.params.PageParam;
import com.decucin.common.JWTTokenUtils;
import com.decucin.common.vo.Result;
import com.decucin.common.vo.ResultEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/22 13:46
 * @description：这个类用于实现文章功能
 * @modified By：
 * @version: 1.0$
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {


    @Autowired
    private UserFeign userFeign;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BodyMapper bodyMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleMapper articleMapper;


    @Autowired
    private BodyService bodyService;


    /**
    *  @param pageParam
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:07
    **/
    @Override
    public Result findAll(PageParam pageParam) {
        /**
         *  TODO 找到首页全部文章并以List<ArticleVo>形式返回(不含文章内容)
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        Page<Article> page = new Page<>(pageParam.getPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper= new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        //  这里已经获取到了List，但需要再将其转化为vo对象即可
        List<Article> articleList = articlePage.getRecords();
        //  将其转化为vo对象
        List<ArticleVo> articles = copyList(articleList, true, true, false);
        return Result.success(articles);
    }


    /**
    *  @param categoryId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/20 16:05
    **/
    @Override
    public Result findArticleByCategory(PageParam pageParam, Long categoryId) {
        /**
        *  TODO 根据标签id找到对应的文章列表并以List<ArticleVo>形式返回(不含文章内容)
        *  @author decucin
        *  @date 2021/10/20 16:07
        **/
        Page<Article> page = new Page<>(pageParam.getPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getCategoryId, categoryId);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> articleList = articlePage.getRecords();
        List<ArticleVo> articles = copyList(articleList, true, true,false);
        return Result.success(articles);
    }

    /**
    *  @param id
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:10
    **/
    @Override
    public Result findArticleInfo(Long id) {
        /**
         *  TODO 根据文章id找到对应的文章的详细信息并以ArticleVo形式返回(含文章内容)
         *  @author decucin
         *  @date 2021/10/20 16:35
         **/
        Article article = articleMapper.selectById(id);
        if(article != null) {
//            threadService.updateArticleViewCount(articleMapper, article);
            return Result.success(copy(article, true, true, true));
        }
        return Result.fail(ResultEnum.NOT_FOUND);
    }

    /**
    *  @param articleId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:11
    **/
    @Override
    public Result likeArticle(String token, Long articleId) {
        /**
         *  TODO 根据token确定的用户id以及文章id使该用户对此篇文章进行点赞
         *  @author decucin
         *  @date 2021/10/20 17:13
         **/
        Long userId = JWTTokenUtils.getIdFromToken(token);
        if(userId == null){
            return Result.fail(ResultEnum.ILLEGAL_TOKEN);
        }
        Article article = articleMapper.selectById(articleId);
        if(article == null){
            return Result.fail(ResultEnum.NOT_FOUND);
        }
        // 这里已经确定文章存在同时token合法

//        Boolean ifLike = articleMapper.selectIfLike(userId, articleId);
//        if(ifLike == null || !ifLike) {
//            if(ifLike == null){
//                // 进入这里表示还没有点赞（不存在点赞后又取消）
//                // like_article表插入信息
//                articleMapper.insertLike(userId, articleId);
//            }else{
//                // 这里表示表中已有信息但if_like为0（点赞后又取消）
//                // like_article表修改信息
//                articleMapper.updateLike(userId, articleId);
//            }
//            // 更新文章点赞数
//            LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
//            updateWrapper.eq(Article::getId, articleId).set(Article::getLikeCount, article.getLikeCount() + 1);
//
//            return Result.success(articleMapper.update(null, updateWrapper));
//        }
        return Result.fail(ResultEnum.ALREADY_OPERATE);
    }

    /**
    *  @param articleId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:13
    **/
    @Override
    public Result notLikeArticle(String token, Long articleId) {
        Long userId;
        try {
            userId =(Long) JWTTokenUtils.getTokenBody(token).get("id");
        }catch (Exception e){
            return Result.fail(ResultEnum.ILLEGAL_TOKEN);
        }
        if(userId == null){
            return Result.fail(ResultEnum.ILLEGAL_TOKEN);
        }
        Article article = articleMapper.selectById(articleId);
        if(article == null){
            return Result.fail(ResultEnum.NOT_FOUND);
        }
        Boolean ifLike = articleMapper.selectIfLike(userId, articleId);
        if(ifLike){
            // 这里表示用户确实已经点赞了
            // 首先将like_comment表中的ifLiKe置为0
            articleMapper.cancelLike(userId, articleId);
            // 写到这里忽然想到取消点赞如果设置为删除对应记录，那之前点赞的判断部分就不需要判断isLike是否为false（不过我这里还是使用的是更新记录）
            // 更新文章点赞数
            LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Article::getId, articleId).set(Article::getLikeCount, article.getLikeCount() - 1);
            return Result.success(articleMapper.update(null, updateWrapper));
        }
        // 这里表示用户还没有点赞
        return Result.fail(ResultEnum.ALREADY_OPERATE);
    }

    /**
    *  @param articleId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:14
    **/
    @Override
    public Result addCommentCount(Long articleId) {
        /**
         *  TODO 增加文章对应的评论数，与addComment共同使用
         *  @author decucin
         *  @date 2021/10/20 17:39
         **/
        Article article = articleMapper.selectById(articleId);
        if(article == null){
            return Result.fail(ResultEnum.NOT_FOUND);
        }
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, articleId).set(Article::getCommentCount, article.getCommentCount() + 1);
        return Result.success(articleMapper.update(null, updateWrapper));
    }

    /**
    *  @param articleId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:17
    **/
    @Override
    public Result deleteCommentCount(Long articleId) {
        /**
         *  TODO 减少文章对应的评论数，与deleteComment共同使用
         *  @author decucin
         *  @date 2021/10/20 17:48
         **/
        Article article = articleMapper.selectById(articleId);
        if(article == null){
            return Result.fail(ResultEnum.NOT_FOUND);
        }
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, articleId).set(Article::getCommentCount, article.getCommentCount() - 1);
        return Result.success(articleMapper.update(null, updateWrapper));
    }

    /***
     * @description: elasticsearch搜索功能实现
     * @param key
     * @return: com.decucin.blog.vo.Result
     * @author: decucin
     * @date: 2021/12/9 20:27
     */
    @Override
    public Result search(String key) throws IOException {
        return new Result();
    }

    /**
     * @description: 文章发布功能实现
     * @param token
     * @param articleParam
     * @return: com.decucin.blog.vo.Result
     * @author: decucin
     * @date: 2021/12/20 19:31
     */
    @Override
    public Result addArticle(String token, ArticleParam articleParam) {
        Long userId;
        try {
            userId =(Long) JWTTokenUtils.getTokenBody(token).get("id");
        }catch (Exception e){
            return Result.fail(ResultEnum.ILLEGAL_TOKEN);
        }
        if(userId == null){
            return Result.fail(ResultEnum.ILLEGAL_TOKEN);
        }
        Body body = new Body();
        body.setContent(articleParam.getContent());
        body.setContentHtml(articleParam.getContentHtml());
        bodyMapper.insert(body);
        Long bodyId = body.getId();
        Article article = new Article();
        article.setAuthorId(userId);
        article.setBodyId(bodyId);
        article.setCreateDate(new Date());
        article.setLikeCount(0);
        article.setCategoryId(articleParam.getCategoryId());
        article.setCommentCount(0);
        article.setSummary(articleParam.getSummary());
        article.setTitle(articleParam.getTitle());
        article.setViewCount(0);
        article.setWeight(0);
        articleMapper.insert(article);
        Long articleId = article.getId();

        List<String> tags = articleParam.getTags();
        ArrayList<Long> tagIds = new ArrayList<>();
        for (String tagName : tags){
            Tag tag = tagService.findTagByTagName(tagName);
            Long tagId;
            if (tag == null) {
                tagId = tagService.insertTag(tagName);
            }else {
                tagId = tag.getId();
            }
            tagIds.add(tagId);
        }

        for(Long tagId : tagIds){
            articleMapper.insertTag(articleId, tagId);
        }
        return Result.success("文章发布成功！");
    }

    /**
    *  @param articleList
    *  @param isTags
    *  @param isAuthor
    *  @param isBody
    *  @return java.util.List<com.decucin.blog.vo.ArticleVo>
    *  @author decucin
    *  @date 2021/10/25 12:17
    **/
    private List<ArticleVo> copyList(List<Article> articleList, boolean isTags, boolean isAuthor, boolean isBody) {
        /**
         *  TODO 根据是否具有标签信息、作者信息以及文章主体信息将articleList转化为articleVoList
         *  @author decucin
         *  @date 2021/10/20 17:56
         **/
        List<ArticleVo> articleVos = new ArrayList<>();
        for (Article article : articleList) {
            articleVos.add(copy(article, isTags, isAuthor, isBody));
        }
        return articleVos;
    }

    /**
    *  @param article
    *  @param isTags
    *  @param isAuthor
    *  @param isBody
    *  @return com.decucin.blog.vo.ArticleVo
    *  @author decucin
    *  @date 2021/10/25 12:19
    **/
    private ArticleVo copy(Article article, boolean isTags, boolean isAuthor, boolean isBody){
        /**
         *  TODO 根据是否具有标签信息、作者信息以及文章主体信息将article转化为articleVo
         *  @author decucin
         *  @date 2021/10/20 18:23
         **/
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setCategory(categoryMapper.selectById(article.getCategoryId()).getClassifyName());
        if(isTags){
            Long articleId = article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }
        if(isAuthor){
            Long authorId = article.getAuthorId();
            articleVo.setAuthor((String) userFeign.findAuthorNameById(authorId).getData());
        }
        if(isBody){
            Long bodyId = article.getBodyId();
            articleVo.setBody(bodyService.findBodyById(bodyId));
        }
        return articleVo;
    }
}
