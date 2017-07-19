package com.imoosen.util;

/**
 * Created by [mengsen] on 2017/7/18 0018.
 *
 * @Description: [一句话描述该类的功能]
 * @UpdateUser: [mengsen] on 2017/7/18 0018.
 */
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.type.TypeReference;

public class JSONUtils {
    private static ObjectMapper mapper = null;

    static {
        if(mapper != null) {
            mapper.getSerializationConfig().set(Feature.WRITE_NULL_PROPERTIES, false);
            mapper.disable(new org.codehaus.jackson.map.DeserializationConfig.Feature[]{org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES});
        } else {
            mapper = new ObjectMapper();
            mapper.getSerializationConfig().set(Feature.WRITE_NULL_PROPERTIES, false);
            mapper.disable(new org.codehaus.jackson.map.DeserializationConfig.Feature[]{org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES});
        }

    }

    public JSONUtils() {
    }

    public static <T> T parseJson(String json, Class<T> valueType) throws JsonParseException, JsonMappingException, IOException {
        Object t = null;
        if(!StringUtils.isBlank(json) && valueType != null) {
            t = mapper.readValue(json, valueType);
        }

        return (T)t;
    }

    public static <T> T parseJson(String json, TypeReference<T> typeRef) throws JsonParseException, JsonMappingException, IOException {
        Object t = null;
        if(!StringUtils.isBlank(json) && typeRef != null) {
            t = mapper.readValue(json, typeRef);
        }

        return (T)t;
    }

    public static <T> String writeJson(List<T> list) throws JsonGenerationException, JsonMappingException, IOException {
        return mapper.writeValueAsString(list);
    }

    public static <T> String writeJson(T t) throws JsonGenerationException, JsonMappingException, IOException {
        return mapper.writeValueAsString(t);
    }
}
