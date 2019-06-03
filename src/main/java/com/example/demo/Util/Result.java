package com.example.demo.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 * 王国超 2019/06/02
 * 返回类对象
 */
public class Result<T>  {
    private int code;
    private String message;
    private JSON data;

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public JSON getData() {
        return data;
    }

    public Result setData(T data) {
        String s = JSON.toJSONString(data, new SimplePropertyFilter());
        boolean b = s.startsWith("[");
        if (b){
            this.data = JSONArray.parseArray(s);
        }else {
            this.data = JSONObject.parseObject(s);
        }
        return this;
    }
}
