package com.decucin.articleservice.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.decucin.articleservice.dao.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    List<Tag> findTagsByArticleId(Long id);

    List<Long> findHotTagIds(int limit);
}
