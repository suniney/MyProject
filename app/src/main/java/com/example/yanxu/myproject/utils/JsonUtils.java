package com.example.yanxu.myproject.utils;

import com.google.gson.Gson;


/**
 * json解析工具类
 *
 * @author yanxu
 *         Info info = JsonUtils.ToGson(result, Info.class);
 */
public class JsonUtils {
    /**
     * Gson解析-泛型
     *
     * @param json     json字符串
     * @param classOfT 泛型
     * @return
     */
    public static <T> T ToGson(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }

    public static String toJSonStr(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
