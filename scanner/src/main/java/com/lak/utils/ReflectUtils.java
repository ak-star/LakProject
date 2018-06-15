package com.lak.utils;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;

/**
 * Created by lawrence on 2018/6/14.
 * <p>
 * 反射工具
 */

public final class ReflectUtils {

    private ReflectUtils() {
    }

    /**
     * 反射获得指定类中的属性
     *
     * @param instance 反射对象
     * @param fieldStr 反射对象中的属性
     * @param <T>      属性的类型
     * @return 返回所找到的指定属性，未找到返回null
     */
    public static <T> T getDeclaredField(@NonNull final Object instance, @NonNull final String fieldStr) {
        T result = null;
        if (instance != null) {
            Class tempClass = instance.getClass();
            while (tempClass != null) { //当父类为null的时候说明到达了最上层的父类(Object类).
                try {
                    Field field = tempClass.getDeclaredField(fieldStr);
                    field.setAccessible(true);
                    result = (T) field.get(instance);
                    break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                }
                tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
            }
        }
        return result;
    }

}
