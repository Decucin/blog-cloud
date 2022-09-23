package com.decucin.articleservice.controller;


import com.decucin.articleservice.service.TagService;
import com.decucin.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/20 20:29
 * @description：与标签有关功能
 * @modified By：
 * @version: 1.0$
 */
@RestController
@RequestMapping("article/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("all")
    public Result findAll(){
        return tagService.findAll();
    }

    @GetMapping("hot")
    public Result hotTags(){
        int limit = 6;
        return tagService.hotTags(limit);
    }

    @PostMapping("add")
    public Result addTags(@RequestBody String[] tagNames){
        return tagService.addTags(tagNames);
    }
}
