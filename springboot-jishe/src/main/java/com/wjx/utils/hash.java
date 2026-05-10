package com.wjx.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;



public class hash {

    public static HashMap<String, Object> myHashMap(String path) throws IOException {
        String jsonStr;
        ClassPathResource classpathResource = new ClassPathResource(path);
        InputStream inputStream = classpathResource.getInputStream();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            jsonStr = sb.toString();
        }
        return (HashMap<String, Object>) JSON.parseObject(jsonStr.toString(), HashMap.class);
    }


}
