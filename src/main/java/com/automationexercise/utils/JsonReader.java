package com.automationexercise.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonReader {
    private static final ObjectMapper mapper= new ObjectMapper();

    public static <T>T read(String resourcePath, Class<T> clazz){
        try(InputStream is = JsonReader.class.getResourceAsStream(resourcePath)){
            if(is==null){
                throw new IllegalArgumentException("Test data file not found: "+ resourcePath);
            }
            return mapper.readValue(is, clazz);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to read:" + resourcePath, e);
        }
    }
}
