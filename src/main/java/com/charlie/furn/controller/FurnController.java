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
    // 2. 使用 @RequestBody 将客户端提交的json数据，封装成JavaBean对象。
    //          前端提交数据时Content-Type: application/json
    // 3. 如果前端通过 表单(form-data) 的形式传入数据，则会报错：Content type 'multipart/form-data not supported
    //      前端请求数据格式必须与 http请求头(header) Content-Type 指定的格式相同，否则会报错：Status: 500服务器错误
    //      因为不一致导致传过来的数据信息全为空，所以服务器端报错 furn=Furn(id=null, name=null, maker=null, price=null, sales=null, stock=null)
    // 4. 如果前端是以 表单(multipart/form-data) 形式提交的数据，则不需要使用
    @PostMapping("/save")
    public Result save(@RequestBody Furn furn) {
        log.info("furn={}", furn);
        furnService.save(furn);
        return Result.success();    // 返回成功信息，不携带数据
    }

}
