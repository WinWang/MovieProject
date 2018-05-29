package com.lepoint.ljfmvp.utils;

import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

import com.lepoint.ljfmvp.base.MyApp;

/**
 * <Tosat消息工具>
 *
 * @author jinyb
 * @date 创建时间：2014年8月9日 下午11:34:04
 */
public class ToastUtil {
    private static final int TIME = 1000;
    private static Toast mToast;

    public static void showShort(Context context, int resId) {
        Toast toast = Toast.makeText(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showShort(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showLong(Context context, int resId) {
        Toast toast = Toast.makeText(context, context.getResources().getText(resId), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showLong(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showTime(Context context, int resId, int showTime) {
        Toast toast = Toast.makeText(context, context.getResources().getText(resId), showTime);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        ;
    }

    public static void showTime(Context context, String msg, int showTime) {
        Toast toast = Toast.makeText(context, msg, showTime);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 显示提示信息，需要在线程中显示Toast
     *
     * @param context
     * @param msg
     */
    public static void showShortOnThread(final Context context, final String msg) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Looper.prepare();
                Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Looper.loop();
            }
        }).start();
    }

    public static void showShortOnThread(final Context context, final int resId) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Looper.prepare();
                Toast toast = Toast.makeText(context, context.getResources().getText(resId), Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Looper.loop();
            }
        }).start();
    }

    /**
     * 显示提示信息，需要在线程中显示Toast
     *
     * @param context
     * @param msg
     */
    public static void showLongOnThread(final Context context, final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Looper.loop();
            }
        }).start();
    }

    public static void showLongOnThread(final Context context, final int resId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast toast = Toast.makeText(context, context.getResources().getText(resId),
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Looper.loop();
            }
        }).start();
    }


    /**
     * 显示对话框
     *
     * @param content 要显示的内容
     */
    public static void showToast(String content) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApp.getContext(), content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }
        mToast.show();
    }


}
