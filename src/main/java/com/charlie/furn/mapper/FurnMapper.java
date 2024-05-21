package com.charlie.furn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.charlie.furn.bean.Furn;
import org.apache.ibatis.annotations.Mapper;

/**
 * 1. 如果使用mybatis-plus，Mapper接口可以通过继承extends 接口BaseMapper
 * 2. 如果不想在每个Mapper接口上都使用注解 @Mapper标识，可以在 Application.java 主程序中
 *      使用注解 @MapperScan(basePackage = {"com.charlie.furn.mapper"}) 指定扫描mapper所在包
 */
//@Mapper
public interface FurnMapper extends BaseMapper<Furn> {
    // 如果有其它方法，可以在该接口声明，并在对应的Mapper.xml文件进行配置
}
