package com.lak.tools.map;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by lawrence on 2018/4/2.
 * <p>
 * intent,bundle中相应key对应的value
 */

public class MapUtils {

    /**
     * intent 中查找有效key值
     *
     * @param intent intent对象
     * @param key    key值
     * @return intent对象中是否还有次key，true-含有，false-没有
     */
    private static boolean hasKey(Intent intent, String key) {
        boolean result = false;
        if (intent != null)
            result = intent.hasExtra(key);
        return result;
    }

    public static String getString(Intent intent, String key, String defaultValue) {
        String result = defaultValue;
        if (hasKey(intent, key))
            result = intent.getStringExtra(key);
        return result;
    }

    public static boolean getBoolean(Intent intent, String key, boolean defaultValue) {
        boolean result = defaultValue;
        if (hasKey(intent, key))
            result = intent.getBooleanExtra(key, defaultValue);
        return result;
    }

    public static int getInt(Intent intent, String key, int defaultValue) {
        int result = defaultValue;
        if (hasKey(intent, key))
            result = intent.getIntExtra(key, defaultValue);
        return result;
    }

    public static double getDouble(Intent intent, String key, double defaultValue) {
        double result = defaultValue;
        if (hasKey(intent, key))
            result = intent.getDoubleExtra(key, defaultValue);
        return result;
    }

    public static long getLong(Intent intent, String key, Long defaultValue) {
        Long result = defaultValue;
        if (hasKey(intent, key))
            result = intent.getLongExtra(key, defaultValue);
        return result;
    }

    public static float getFloat(Intent intent, String key, float defaultValue) {
        float result = defaultValue;
        if (hasKey(intent, key))
            result = intent.getFloatExtra(key, defaultValue);
        return result;
    }

    public static char getCharset(Intent intent, String key, char defaultValue) {
        char result = defaultValue;
        if (hasKey(intent, key))
            result = intent.getCharExtra(key, defaultValue);
        return result;
    }

    public static @Nullable
    <T extends Serializable> T getString(Intent intent, String key) {
        T result = null;
        if (hasKey(intent, key))
            result = (T) intent.getSerializableExtra(key);
        return result;
    }

    public static @Nullable
    Bundle getBundle(Intent intent, String key) {
        Bundle result = null;
        if (hasKey(intent, key))
            result = intent.getBundleExtra(key);
        return result;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * bundle 中查找有效key值
     *
     * @param bundle bundle
     * @param key    key值
     * @return bundle对象中是否还有次key，true-含有，false-没有
     */
    private static boolean hasKey(Bundle bundle, String key) {
        boolean result = false;
        if (bundle != null)
            result = bundle.containsKey(key);
        return result;
    }

    public static String getString(Bundle bundle, String key, String defaultValue) {
        String result = defaultValue;
        if (hasKey(bundle, key))
            result = bundle.getString(key);
        return result;
    }

    public static boolean getBoolean(Bundle bundle, String key, boolean defaultValue) {
        boolean result = defaultValue;
        if (hasKey(bundle, key))
            result = bundle.getBoolean(key, defaultValue);
        return result;
    }

    public static int getInt(Bundle bundle, String key, int defaultValue) {
        int result = defaultValue;
        if (hasKey(bundle, key))
            result = bundle.getInt(key, defaultValue);
        return result;
    }

    public static double getDouble(Bundle bundle, String key, double defaultValue) {
        double result = defaultValue;
        if (hasKey(bundle, key))
            result = bundle.getDouble(key, defaultValue);
        return result;
    }

    public static long getLong(Bundle bundle, String key, Long defaultValue) {
        Long result = defaultValue;
        if (hasKey(bundle, key))
            result = bundle.getLong(key, defaultValue);
        return result;
    }

    public static float getFloat(Bundle bundle, String key, float defaultValue) {
        float result = defaultValue;
        if (hasKey(bundle, key))
            result = bundle.getFloat(key, defaultValue);
        return result;
    }

    public static char getCharset(Bundle bundle, String key, char defaultValue) {
        char result = defaultValue;
        if (hasKey(bundle, key))
            result = bundle.getChar(key, defaultValue);
        return result;
    }

    public static @Nullable
    <T extends Serializable> T getString(Bundle bundle, String key) {
        T result = null;
        if (hasKey(bundle, key))
            result = (T) bundle.getSerializable(key);
        return result;
    }

    public static @Nullable
    Bundle getBundle(Bundle bundle, String key) {
        Bundle result = null;
        if (hasKey(bundle, key))
            result = bundle.getBundle(key);
        return result;
    }

}
