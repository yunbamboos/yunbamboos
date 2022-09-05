package io.github.yunbamboos.dto.in;

import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.Model;
import io.github.yunbamboos.dto.DTO;
import io.github.yunbamboos.exception.AppException;

import java.lang.reflect.InvocationTargetException;

/**
 * 服务入参封装基类
 */
public abstract class InDTO implements DTO {

    @Override
    public void decode(JSONObject json) {
        // empty
    }

    @SuppressWarnings("unchecked")
    protected <T> T getRequiredObject(JSONObject json, String key, Class<? extends Model> clazz) {
        try {
            Model model = clazz.getDeclaredConstructor().newInstance();
            if (json.containsKey(key)) {
                model.decode(json.getJSONObject(key));
                return (T) model;
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new AppException("required " + key + " is empty");
    }

    protected String getRequiredString(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getString(key);
        }
        throw new AppException("required " + key + " is empty");
    }

    protected int getInteger(JSONObject json, String key, int defaultValue) {
        if (json.containsKey(key)) {
            return json.getInteger(key);
        }
        return defaultValue;
    }

    protected int getInteger(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getInteger(key);
        }
        return 0;
    }
}
