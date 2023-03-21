package com.flower.xin.common.util;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

public class JsonUtil {
    public static final Gson gson = new Gson();

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

}
