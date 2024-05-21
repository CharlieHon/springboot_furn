package com.charlie.furn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.charlie.furn.bean.Furn;
import com.charlie.furn.mapper.FurnMapper;
import com.charlie.furn.service.FurnService;
import org.springframework.stereotype.Service;

/**
 * 1. 在实现类中直接进行 implements FurnService
 * 2. 在Mybatis-Plus中，开发Service实现类，需要继承 ServiceImpl类
 * 3. 观察到 ServiceImpl类 实现了 IService接口：public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T>
 * 4. FurnService 接口继承了 IService接口
 * 5. 这里 FurnServiceImpl 就可以认为是实现了 FurnService接口，就可以使用IService接口方法，也可以理解成使用FurnService方法
 * 6. 如果 FurnService 接口中，声明了其它的方法/自定义方法，那么依然需要在 MonsterServiceImpl类 中进行实现
 */
@Service
public class FurnServiceImpl extends ServiceImpl<FurnMapper, Furn> implements FurnService {

}
