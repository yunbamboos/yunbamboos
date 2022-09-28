package io.github.yunbamboos.dto.in;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.Model;
import io.github.yunbamboos.dto.DTO;
import io.github.yunbamboos.exception.AppException;

import java.lang.reflect.InvocationTargetException;

/**
 * 服务入参封装基类
 */
public abstract class InDTO implements DTO {

    private static final String ERROR_REQUIRED = "required %s is empty";

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
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    /**JSONObject*/
    protected JSONObject getRequiredJSONObject(JSONObject json, String key){
        if(json.containsKey(key)){
            return json.getJSONObject(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    /**JSONArray*/
    protected JSONArray getRequiredJSONArray(JSONObject json, String key){
        if(json.containsKey(key)){
            return json.getJSONArray(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    /**String*/
    protected String getRequiredString(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getString(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected String getString(JSONObject json, String key, String defaultValue) {
        if (json.containsKey(key)) {
            return json.getString(key);
        }
        return defaultValue;
    }

    protected String getString(JSONObject json, String key) {
        return json.getString(key);
    }

    /**Integer*/
    protected Integer getRequiredInteger(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getInteger(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected Integer getInteger(JSONObject json, String key, Integer defaultValue) {
        if (json.containsKey(key)) {
            return json.getInteger(key);
        }
        return defaultValue;
    }

    protected Integer getInteger(JSONObject json, String key) {
        return json.getInteger(key);
    }

    /**int*/
    protected int getRequiredIntValue(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getIntValue(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected int getIntValue(JSONObject json, String key, int defaultValue) {
        if (json.containsKey(key)) {
            return json.getInteger(key);
        }
        return defaultValue;
    }

    protected int getIntValue(JSONObject json, String key) {
        return json.getInteger(key);
    }

    /**Long*/
    protected Long getRequiredLong(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getLong(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected Long getLong(JSONObject json, String key, Long defaultValue) {
        if (json.containsKey(key)) {
            return json.getLong(key);
        }
        return defaultValue;
    }

    protected Long getLong(JSONObject json, String key) {
        return json.getLong(key);
    }

    /**long*/
    protected long getRequiredLongValue(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getLongValue(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected long getLongValue(JSONObject json, String key, long defaultValue) {
        if (json.containsKey(key)) {
            return json.getLongValue(key);
        }
        return defaultValue;
    }

    protected long getLongValue(JSONObject json, String key) {
        return json.getLongValue(key);
    }

    /**Float*/
    protected Float getRequiredFloat(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getFloat(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected Float getFloat(JSONObject json, String key, Float defaultValue) {
        if (json.containsKey(key)) {
            return json.getFloat(key);
        }
        return defaultValue;
    }

    protected Float getFloat(JSONObject json, String key) {
        return json.getFloat(key);
    }

    /**float*/
    protected float getRequiredFloatValue(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getFloatValue(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected float getFloatValue(JSONObject json, String key, float defaultValue) {
        if (json.containsKey(key)) {
            return json.getFloatValue(key);
        }
        return defaultValue;
    }

    protected float getFloatValue(JSONObject json, String key) {
        return json.getFloat(key);
    }

    /**Boolean*/
    protected Boolean getRequiredBoolean(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getBoolean(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected Boolean getBoolean(JSONObject json, String key, Boolean defaultValue) {
        if (json.containsKey(key)) {
            return json.getBoolean(key);
        }
        return defaultValue;
    }

    protected Boolean getBoolean(JSONObject json, String key) {
        return json.getBoolean(key);
    }

    /**boolean*/
    protected boolean getRequiredBooleanValue(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getBooleanValue(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected boolean getBooleanValue(JSONObject json, String key, boolean defaultValue) {
        if (json.containsKey(key)) {
            return json.getBooleanValue(key);
        }
        return defaultValue;
    }

    protected boolean getBooleanValue(JSONObject json, String key) {
        return json.getBooleanValue(key);
    }

    /**Double*/
    protected Double getRequiredDouble(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getDouble(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected Double getDouble(JSONObject json, String key, Double defaultValue) {
        if (json.containsKey(key)) {
            return json.getDouble(key);
        }
        return defaultValue;
    }

    protected Double getDouble(JSONObject json, String key) {
        return json.getDouble(key);
    }

    /**double*/
    protected double getRequiredDoubleValue(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getDoubleValue(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected double getDoubleValue(JSONObject json, String key, double defaultValue) {
        if (json.containsKey(key)) {
            return json.getDoubleValue(key);
        }
        return defaultValue;
    }

    protected double getDoubleValue(JSONObject json, String key) {
        return json.getDoubleValue(key);
    }

    /**Short*/
    protected Short getRequiredShort(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getShort(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected Short getShort(JSONObject json, String key, Short defaultValue) {
        if (json.containsKey(key)) {
            return json.getShort(key);
        }
        return defaultValue;
    }

    protected Short getShort(JSONObject json, String key) {
        return json.getShort(key);
    }

    /**short*/
    protected short getRequiredShortValue(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getShortValue(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected short getShortValue(JSONObject json, String key, short defaultValue) {
        if (json.containsKey(key)) {
            return json.getShortValue(key);
        }
        return defaultValue;
    }

    protected short getShortValue(JSONObject json, String key) {
        return json.getShortValue(key);
    }

    /**Byte*/
    protected Byte getRequiredByte(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getByte(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected Byte getByte(JSONObject json, String key, Byte defaultValue) {
        if (json.containsKey(key)) {
            return json.getByte(key);
        }
        return defaultValue;
    }

    protected Byte getByte(JSONObject json, String key) {
        return json.getByte(key);
    }

    /**byte*/
    protected byte getRequiredByteValue(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getByteValue(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }

    protected byte getByteValue(JSONObject json, String key, byte defaultValue) {
        if (json.containsKey(key)) {
            return json.getByteValue(key);
        }
        return defaultValue;
    }

    protected byte getByteValue(JSONObject json, String key) {
        return json.getByteValue(key);
    }

    protected byte[] getBytes(JSONObject json, String key) {
        if (json.containsKey(key)) {
            return json.getBytes(key);
        }
        throw new AppException(String.format(ERROR_REQUIRED, key));
    }


}
