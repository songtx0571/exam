package com.howei.util;

public class Result<T> {

    private Integer code;
    private String msg;
    private T data;
    private Integer count = 0;


    public static <T> Result<T> success() {
        return Result.success(null, 0);
    }

    public static <T> Result<T> success(T data, Integer count) {
        Result<T> result = new Result<T>();
        result.code = 0;
        result.count = count;
        result.msg = "操作成功";
        result.data = data;
        return result;
    }


    public static <T> Result<T> fail(Integer code, String msg) {
        Result<T> result = new Result<T>();
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static <T> Result<T> fail() {
        return Result.fail(-1, "操作失败");
    }

    public static <T> Result<T> fail(String msg) {
        return Result.fail(-1, msg);
    }

    public static Result fail(ResultEnum resultEnum) {
        return Result.fail(resultEnum.getCode(), resultEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
