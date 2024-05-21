package com.charlie.furn.controller;

import com.charlie.furn.bean.Furn;
import com.charlie.furn.service.FurnService;
import com.charlie.furn.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @RestController = @ResponseBody + @Controller
 * 1. 因为当前项目是前后端分离的，在默认情况下，前端发送请求
 * 2. 后端返回 json数据，为了方便，在类上使用 @RestController 这样就相当于类内所有方法都加上了@ResponseBody注解
 */
@Slf4j
@RestController
public class FurnController {

    // 装配service
    @Resource
    private FurnService furnService;

    // 编写方法完成添加
    // 1. 当前前端是以 json格式 来发送添加信息furn，那么需要使用 @RequestBody注解
    // 2. 使用 @RequestBody 将客户端提交的json数据，封装成JavaBean对象
    // 3. 如果前端是以 表单形式 提交的数据，则不需要使用
    @PostMapping("/save")
    public Result save(@RequestBody Furn furn) {
        log.info("furn={}", furn);
        furnService.save(furn);
        return Result.success();    // 返回成功信息，不携带数据
    }

}
