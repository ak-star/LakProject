package com.lak.tools.keyboard;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by lawrence on 2018/4/2.
 * <p>
 * 键盘显示隐藏
 */

public class KeyboardUtils {

    /**
     * 隐藏软键盘
     */
    public static void hideInput(Activity activity) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                // 当前焦点view
                View nLocalView = activity.getCurrentFocus();
                if (nLocalView != null) {
                    IBinder nLocalIBinder = nLocalView.getWindowToken();
                    if (nLocalIBinder != null)
                        inputMethodManager.hideSoftInputFromWindow(nLocalIBinder, 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏软键盘
     */
    public static void hideInput(EditText editText) {
        editText.clearFocus();
        InputMethodManager inputMethodManager =
                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * 显示软键盘
     */
    public static void showInput(Context context) {
        if (context == null) {
            return;
        }
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            // 接受软键盘输入的编辑文本或其它视图
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示软键盘
     */
    public static void showInput(EditText editText) {
        editText.requestFocus();
        InputMethodManager inputMethodManager =
                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
    }

}
