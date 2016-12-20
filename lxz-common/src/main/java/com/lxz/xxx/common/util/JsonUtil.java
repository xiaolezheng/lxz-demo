package com.lxz.xxx.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

/**
 * Created by xiaolezheng on 16/9/8.
 */
@Slf4j
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //禁止把POJO中值为null的字段映射到json字符串中
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        //POJO为null不报错
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //有属性不能映射成POJO时不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String formatJSON(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("序列化json错误", e);
        }

        return null;
    }

    public static <T> T unformatJSON(String json, Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            log.error("反序列化json错误", e);
            return null;
        }
    }

    public static <T> T unformatJSON(Map<String, Object> json, Class<T> type) {
        return objectMapper.convertValue(json, type);
    }

    /**
     * 泛型反序列化接口
     *
     * @param json
     * @param type  泛型的Collection Type
     * @param type2 elementClasses 元素类
     * @return
     */
    public static <T> T unformatJSON(String json, Class type, Class<T> type2) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructParametricType(type, type2));
        } catch (IOException e) {
            log.error("反序列化json错误", e);
        }

        return null;
    }

    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return objectMapper.convertValue(fromValue, toValueType);
    }
}