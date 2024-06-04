package com.charlie.furn.lambda;

import sun.security.krb5.internal.crypto.Des;

public class Test {
    public static void main(String[] args) {
        // 传统的方式来实现 HspFunction 接口，得到一个实现接口的对象，可以使用匿名内部类
        //HspFunction<Desk, String> hf = new HspFunction<Desk, String>() {
        //    @Override
        //    public String apply(Desk desk) {
        //        return "hello, desk";
        //    }
        //};
        //String val = hf.apply(new Desk());
        //System.out.println("val=" + val);   // val=hello, desk

        // 实现2
        HspFunction<Desk, String> hf2 = Desk::getName;  // Desk::getBrand;
        hf2.ok();   // OK
        // class com.charlie.furn.lambda.Test$$Lambda$1/1598924227
        // System.out.println("hf2.getClass()=" + hf2.getClass());
        String val2 = hf2.apply(new Desk());
        System.out.println("val2=" + val2);     // val2=hello desk
    }
}

// 定义一个函数式接口：有且只有一个抽象方法的接口
// 可以使用 @FunctionInterface 来标识一个函数式接口
@FunctionalInterface
interface HspFunction<T, R> {   // 自定义泛型接口
    R apply(T t);   // 抽象方法：表示根据类型T的参数，获取类型R的结构

    // public void hi();    // 错误：必须只有一个抽象方法(×)

    // 函数式接口，可以有多个默认(default)实现(√)
    default void ok() {
        System.out.println("OK");
    }
}

@FunctionalInterface
interface HspInterface {
    public void hi();
}

class Desk {

    private String name = "hello desk";
    private String brand = "老北京";
    private Integer id = 10;

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getId() {
        return id;
    }
}
