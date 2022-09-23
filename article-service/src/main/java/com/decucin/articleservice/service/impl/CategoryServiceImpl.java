package com.decucin.articleservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.decucin.articleservice.dao.mapper.CategoryMapper;
import com.decucin.articleservice.dao.pojo.Category;
import com.decucin.articleservice.service.CategotyService;
import com.decucin.common.vo.Result;
import com.decucin.common.vo.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/22 11:59
 * @description：这个类用于实现分类功能
 * @modified By：
 * @version: 1.0$
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategotyService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
    *  @param
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:21
    **/
    @Override
    public Result findAll() {
        /**
         *  TODO 找到所有的分类
         *  @author decucin
         *  @date 2021/10/20 18:36
         **/
        List<Category> categories = categoryMapper.selectList(new LambdaQueryWrapper<>());
        return new Result(ResultEnum.SUCCESS, categories);
    }
}
