package io.swagger.api.utils;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.text.SimpleDateFormat;

public class JSONAPIUtils {

    public interface View {

        public interface Rest extends View {}

    }

    public static ObjectMapper defaultMapper = null;
    public static ObjectMapper viewMapper = null;
    public static ObjectMapper indentedMapper = null;

    static {
        defaultMapper = new ObjectMapper();
        defaultMapper.setVisibility(defaultMapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE)
        );
        defaultMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        defaultMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        defaultMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        defaultMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // mapper used to serialize using views.
        viewMapper = defaultMapper.copy();
        viewMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        // mapper used to print pretty json
        indentedMapper = defaultMapper.copy();
        indentedMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static <T> T stringToJson(String value, Class<T> t) {
        try { return defaultMapper.readValue(value.getBytes(), t);  }
        catch (Exception err) { throw new RuntimeException(err); }
    }

    public static <T> T fileToJson(File file, Class<T> t) {
        try { return defaultMapper.readValue(file, t); }
        catch (Exception err) { throw new RuntimeException(err); }
    }

    public static <T> T fileToJson(String path, Class<T> t) {
        try { return defaultMapper.readValue(new File(path), t); }
        catch (Exception err) { throw new RuntimeException(err); }
    }

    public static String jsonToString(Object obj) {
        try { return indentedMapper.writeValueAsString(obj); }
        catch (Exception err) { throw new RuntimeException(err); }
    }

    public static <T> T jsonToObject(String jsonInString, Class<T> type) {
        try { return defaultMapper.readValue(jsonInString, type); }
        catch (Exception err) { throw new RuntimeException(err); }
    }

    public static void jsonToFile(Object obj, String file) {
        try { indentedMapper.writeValue(new File(file), obj); }
        catch (Exception err) { throw new RuntimeException(err); }
    }

    public static String toJson(Object value, Class<? extends View> view) {
        try { return viewMapper.writerWithView(view).writeValueAsString(value); }
        catch (Exception err) { throw new RuntimeException(err); }
    }
}

