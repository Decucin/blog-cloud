package com.decucin.articleservice.service;


import com.decucin.articleservice.vo.ArticleBodyVo;

public interface BodyService {

    ArticleBodyVo findBodyById(Long bodyId);
}
