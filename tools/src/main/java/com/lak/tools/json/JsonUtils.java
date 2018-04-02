package com.lak.tools.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lawrence on 2018/4/2.
 * <p>
 * Json辅助工具
 */

public class JsonUtils {

    public static boolean hasKey(JSONObject jsonObject, String key) {
        return jsonObject.has(key) && !jsonObject.isNull(key);
    }

    public static String getString(JSONObject jsonObject,
                                   String key, String defaultValue) throws JSONException {
        String result = defaultValue;
        if (hasKey(jsonObject, key))
            result = jsonObject.getString(key);
        return result;
    }

    public static boolean getBoolean(JSONObject jsonObject,
                                     String key, boolean defaultValue) throws JSONException {
        boolean result = defaultValue;
        if (hasKey(jsonObject, key)) {
            try {
                result = jsonObject.getBoolean(key);
            } catch (JSONException e) {
                e.printStackTrace();
                result = jsonObject.getInt(key) != 0;
            }
        }
        return result;
    }

    public static double getDouble(JSONObject jsonObject,
                                   String key, double defaultValue) throws JSONException {
        double result = defaultValue;
        if (hasKey(jsonObject, key))
            result = jsonObject.getDouble(key);
        return result;
    }

    public static int getInt(JSONObject jsonObject,
                             String key, int defaultValue) throws JSONException {
        int result = defaultValue;
        if (hasKey(jsonObject, key))
            result = jsonObject.getInt(key);
        return result;
    }

    public static long getLong(JSONObject jsonObject,
                               String key, long defaultValue) throws JSONException {
        long result = defaultValue;
        if (hasKey(jsonObject, key))
            result = jsonObject.getLong(key);
        return result;
    }

    public static JSONObject getJSONObject(JSONObject jsonObject,
                                           String key, JSONObject defaultValue) throws JSONException {
        JSONObject result = defaultValue;
        if (hasKey(jsonObject, key))
            result = jsonObject.getJSONObject(key);
        return result;
    }

    public static JSONArray getJSONArray(JSONObject jsonObject,
                                         String key, JSONArray defaultValue) throws JSONException {
        JSONArray result = defaultValue;
        if (hasKey(jsonObject, key))
            result = jsonObject.getJSONArray(key);
        return result;
    }

}
