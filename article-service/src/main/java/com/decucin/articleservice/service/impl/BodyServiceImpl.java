package com.decucin.articleservice.service.impl;


import com.decucin.articleservice.dao.mapper.BodyMapper;
import com.decucin.articleservice.dao.pojo.Body;
import com.decucin.articleservice.service.BodyService;
import com.decucin.articleservice.vo.ArticleBodyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/22 12:35
 * @description：这个类用于实现文章主体功能
 * @modified By：
 * @version: 1.0$
 */
@Service
@Transactional
public class BodyServiceImpl implements BodyService {

    @Autowired
    private BodyMapper bodyMapper;


    /**
    *  @param bodyId
    *  @return com.decucin.blog.vo.ArticleBodyVo
    *  @author decucin
    *  @date 2021/10/25 12:20
    **/
    @Override
    public ArticleBodyVo findBodyById(Long bodyId) {
        /**
         *  TODO 根据文章id查询到文章主体
         *  @author decucin
         *  @date 2021/10/20 18:36
         **/
        Body body = bodyMapper.selectById(bodyId);
        ArticleBodyVo bodyVo = new ArticleBodyVo();
        bodyVo.setId(body.getId());
        bodyVo.setBody(body.getContentHtml());
        return bodyVo;
    }
}
