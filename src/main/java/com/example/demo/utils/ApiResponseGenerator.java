package com.example.demo.utils;

import java.util.HashMap;
import java.util.Map;
public class ApiResponseGenerator {
    public static Map<String, Object> createResponse(boolean ok, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("ok", ok);
        response.put("message", message);
        response.put("data", data);
        return response;
    }
}
