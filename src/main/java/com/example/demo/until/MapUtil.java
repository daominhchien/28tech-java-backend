package com.example.demo.until;
import java.util.Map;

public class MapUtil {
    public static <T> T getObject (Map<String,Object> params, String key, Class<T> tClass){
        // cos thi gán cho nó kiểu dư liêu là obj , còn không thì mặc định là null
        Object obj = params.getOrDefault(key, null);
        if(obj !=null){
            if(tClass.getTypeName().equals("java.lang.Integer")){
                obj = obj != "" ? Integer.valueOf((String) obj) : null;
            }
            else if (tClass.getTypeName().equals("java.lang.Long")){
                obj = obj != "" ? Long.valueOf(obj.toString()) : null;
        
            }
            else if ( tClass.getTypeName().equals("java.lang.String")){
                obj = obj !="" ? obj.toString() : null;
            }
            return tClass.cast(obj) ;
        }
        return null;
    }

}
