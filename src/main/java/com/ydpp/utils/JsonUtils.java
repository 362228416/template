package com.ydpp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by 16 on 2015/4/22.
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            return "{}";
        }
    }

}
