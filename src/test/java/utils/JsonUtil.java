package utils;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    //1. Method: This method will convert Json Data to Java Object (De-Serialization)

    public static <T> T convertJsonToJavaObject(String json, Class<T> cls){//Generic Method

        T javaResult = null;

        try {
            javaResult = mapper.readValue(json,cls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return javaResult;

    }


    //2. Method: This method will convert Java Object to Json Data (Serialization)

    public static String convertJavaObjectToJson(Object obj){
        String jsonResult = null;

        try {
            jsonResult = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return jsonResult;
    }
}