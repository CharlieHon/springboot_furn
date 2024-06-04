package com.charlie.furn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charlie.furn.bean.Furn;
import com.charlie.furn.service.FurnService;
import com.charlie.furn.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

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
    public Result save(@Validated @RequestBody Furn furn, Errors errors) {
        // 使用 @Validated 注解标识参数，则会对请求的数据进行校验，springboot底层会将错误信息，封装到errors

        // 定义map，准备把errors中的校验错误放入到map。如果有错误信息，就不真正添加，
        // 并且将错误信息通过map返回给客户端，客户端就可以取出信息
        HashMap<Object, Object> map = new HashMap<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        // 遍历，将错误信息放入到map，也有可能没有错误
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        if (map.isEmpty()) {    // 说明没有校验错误
            log.info("furn={}", furn);
            furnService.save(furn);
            return Result.success();    // 返回成功信息，不携带数据
        } else {
            return Result.error("400", "后端校验失败", map);
        }

    }

    // 返回所有的家具信息，后面再考虑分页显示
    @RequestMapping("/furns")
    public Result listFurn() {
        List<Furn> furnList = furnService.list();
        return Result.success(furnList);
    }

    /**
     * 处理修改
     * 1. @PutMapping 使用 Rest风格，因为是修改的请求，所以使用put请求
     * 2. @RequestBody：表示前端/客户端发送的数据是以JSON格式发送的
     * @param furn
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Furn furn) {
        // updateById是mybatis-plus提供的
        furnService.updateById(furn);
        return Result.success();
    }

    /**
     * 删除家具
     * 1. 使用url占位符+@PathVariable路径变量使用
     */
    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable(name = "id") Integer ids) {
        // removeById是由 Mybatis-Plus提供
        furnService.removeById(ids);
        return Result.success();
    }

    /**
     * 查询：根据id返回家具信息
     */
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Integer id) {
        Furn furn = furnService.getById(id);
        // 返回成功信息，并携带查询到的furn信息
        return Result.success(furn);
    }

    /**
     * 分页查询
     * @param pageNum：显示第几页，默认为1
     * @param pageSize：每页显示多少条记录，默认为5
     * 底层sql：
     *         SELECT COUNT(*) FROM furn
     *         SELECT id,name,maker,price,sales,stock FROM furn LIMIT ?,?
     */
    @GetMapping("/furnByPage")
    public Result listFurnByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "5") Integer pageSize) {
        // Page类是MybatisPlus插件pagination中的类
        // 通过page方法，返回Page对象，对象中封装了分页数据
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize));
        // 注意观察，返回的page数据结构？这样才能指定在前端如何绑定返回的数据
        return Result.success(page);
    }

    /**
     * 带条件分页检索
     * @param pageNum：显示第几页，默认为1
     * @param pageSize：每页显示多少条记录，默认为5
     * @param search：检索条件，默认为空("")，即返回所有数据
     * sql语句：SELECT id,name,maker,price,sales,stock FROM furn WHERE (name LIKE ?) LIMIT ?,?
     */
    @GetMapping("/furnBySearchPage")
    public Result listFurnByConditionPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "5") Integer pageSize,
                                          @RequestParam(defaultValue = "") String search) {
        // 先创建QueryWrapper，将检索条件封装到QueryWrapper
        QueryWrapper<Furn> queryWrapper = Wrappers.query();
        // 判断条件是否为空
        if (StringUtils.hasText(search)) {  // str != null && !str.isEmpty() && containsText(str);
            // like参数：R column(表字段名), Object val(检索条件)
            queryWrapper.like("name", search);
        }
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(page);
    }

    /**
     * 编写方法，使用 LambdaQueryWrapper 封装查询条件，完成检索
     * 关于lambda表达式，这里使用的是 类名::实例方法
     * 是 lambda方法引用 中一个不太容易理解的知识点
     * 1. Furn::getName 通过lambda表达式引用实例方法 getName
     * 2. 这里就是把 Furn::getName 赋给 SFunction<T, R> 函数式接口
     * 3. 看看 SFunction<T, R> 源码
     *
     * @FunctionalInterface
     * public interface SFunction<T, R> extends Function<T, R>, Serializable {
     * }
     *
     * @FunctionalInterface
     * public interface Function<T, R> {
     *   R apply(T t);  // 这是一个抽象方法，表示根据类型T，获取类型R的结果
     *   // 后面还有默认实现方法
     * }
     *
     * 4. 传入 Furn::getName 就相当于，实现了 SFunction<T, R> 的 apply方法
     * 5. 底层会根据传入的方法 Furn::getName 去 **得到对应的属性所映射的表字段** "name"
     * 6. 回顾mybatis在 XxxMapper.xml 中有 ResultMap 将 属性(字段) 与 表字段 的映射关系
     * <resultMap id="IdenCardResultMap" type="IdenCard">
     *     <id property="id" column="id"/>
     */
    @GetMapping("/furnBySearchPage2")
    public Result listFurnByConditionPage2(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "5") Integer pageSize,
                                          @RequestParam(defaultValue = "") String search) {
        // 创建 LambdaQueryWrapper，封装查询条件
        LambdaQueryWrapper<Furn> lambdaQueryWrapper = Wrappers.<Furn>lambdaQuery();
        if (StringUtils.hasText(search)) {
            //lambdaQueryWrapper.like(Furn::getName, search);

            // 等价于
            SFunction<Furn, Object> sf = Furn::getName;
            //sf.apply(new Furn());
            lambdaQueryWrapper.like(sf, search);
        }
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize), lambdaQueryWrapper);
        log.info("page={}", page.getRecords());
        return Result.success(page);
    }

}
