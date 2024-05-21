package com.charlie.furn;

import com.charlie.furn.bean.Furn;
import com.charlie.furn.mapper.FurnMapper;
import com.charlie.furn.service.FurnService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ApplicationTest {

    // 装配一个mapper对象
    @Resource
    private FurnMapper furnMapper;

    // 装配一个FurnService对象(按照接口方式装配)
    @Resource
    private FurnService furnService;

    @Test
    public void testFurnMapper() {
        // furnMapper=class com.sun.proxy.$Proxy71
        System.out.println("furnMapper=" + furnMapper.getClass());
        Furn furn = furnMapper.selectById(4);
        // furn=Furn(id=4, name=典雅风格小台灯, maker=蚂蚁家具, price=56.0, sales=1023, stock=7)
        System.out.println("furn=" + furn);
    }

    @Test
    public void testFurnService() {
        List<Furn> list = furnService.list();
        for (Furn furn : list) {
            System.out.println("furn=" + furn);
        }
    }

}
