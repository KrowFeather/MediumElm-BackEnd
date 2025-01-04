package com.krowfeather.mediumelm.common;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;
    public static Result success(Object data) {
        return new Result(200, "success", data);
    }
    public static Result success() {
        return new Result(200, "success", null);
    }
    public static Result error() {
        return new Result(500, "error", null);
    }
    public static Result error(Object data) {
        return new Result(500, "error", data);
    }
    public static Result unauthorized() {
        return new Result(401, "unauthorized", null);
    }
    public static Result unauthorized(Object data) {
        return new Result(401, "unauthorized", data);
    }
    public static Result forbidden() {
        return new Result(403, "forbidden", null);
    }
    public static Result forbidden(Object data) {
        return new Result(403, "forbidden", data);
    }
    public String toJson(){
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }
}
