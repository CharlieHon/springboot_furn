package com.charlie.furn.util;

/**
 * 1. Result<T> 自定义泛型
 * 2. Result<T> 对象是后端返回给前端的数据，是以JSON格式形式
 */
public class Result<T> {
    private String code;    // 状态码
    private String msg;     // 状态说明
    private T data;         // 返回时携带的数据，为了扩展性好，使用泛型

    public Result() {}

    // 带参构造器-指定返回的data
    public Result(T data) {
        this.data = data;
    }

    // 编写方法，返回表示成功的Result对象
    public static Result success() {
        Result<Object> result = new Result<>();
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    // 编写方法，返回表示成功的Result对象，同时携带数据
    // 如果需要在 static方法中使用泛型，需要在 static 后加个 <T>
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    // 编写方法，返回表示失败的Result对象
    public static Result error(String code, String msg) {
        Result<Object> result = new Result<>();
        result.setData(code);
        result.setMsg(msg);
        return result;
    }

    // 编写方法，返回表示失败的Result对象，同时携带数据
    public static <T> Result<T> error(String code, String msg, T data) {
        Result<T> result = new Result<>(data);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
