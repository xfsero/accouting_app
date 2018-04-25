package com.stupidwind.myaccounting.util;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.widget.EditText;

import com.stupidwind.myaccounting.R;

/**
 * 软键盘工具类
 * Created by 蠢风 on 2018/4/25.
 */
public class KeyboardUtil {

    private Context context;
    private Activity activity;

    private KeyboardView keyboardView;
    private Keyboard keyboard;

    private EditText editText;

    public KeyboardUtil(Context context, Activity activity, EditText editText) {
        this.context = context;
        this.activity = activity;
        this.editText = editText;

        keyboard = new Keyboard(context, R.layout.keyboard_view);
        keyboardView = (KeyboardView) activity.findViewById(R.id.account_keyboard_view);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(true);
    }

    /**
     * 显示键盘
     * @author StupidWind
     * created at 2018/4/25 19:42
     */
    public void showKeyBoard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏键盘
     * @author StupidWind
     * created at 2018/4/25 19:43
     */
    public void hideKeyBoard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            keyboardView.setVisibility(View.INVISIBLE);
        }
    }

}
