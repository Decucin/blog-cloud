package com.decucin.articleservice.service;


import com.decucin.articleservice.dao.pojo.Tag;
import com.decucin.common.vo.Result;

import java.util.List;

public interface TagService {
    Result findAll();

    List<Tag> findTagsByArticleId(Long articleId);


    Result hotTags(int limit);

    Result addTags(String[] tagNames);

    Tag findTagByTagName(String tagName);

    Long insertTag(String tagName);
}
