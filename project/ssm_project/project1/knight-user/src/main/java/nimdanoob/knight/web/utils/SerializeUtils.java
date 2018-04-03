package nimdanoob.knight.web.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang.SerializationUtils;
import org.apache.shiro.codec.Base64;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class SerializeUtils extends SerializationUtils {
    public static String serializaToString(Serializable obj){
        try{
            byte[] value = serialize(obj);
            return Base64.encodeToString(value);
        }catch (Exception e){
            throw new RuntimeException("serialize session error");
        }
    }

    public static <T> T deserializeFromString(String base64){
        try{
            byte[] objectData = Base64.decode(base64);
            return (T) deserialize(objectData);
        } catch (Exception e){
            throw new RuntimeException("deserialize session error");
        }
    }

    public static <T> Collection<T> deserializeFromStrings(Collection<String> base64s){
        try{
            List<T> list = Lists.newLinkedList();
            for(String base64 : base64s){
                byte[] objectData = Base64.decode(base64);
                T t = (T) deserialize(objectData);
                list.add(t);
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException("deserialize session error");
        }
    }
}