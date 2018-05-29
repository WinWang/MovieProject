package com.lepoint.ljfmvp.utils;

import android.content.Context;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import cn.droidlover.xdroidmvp.log.XLog;

/**
 * Created by admin on 2018/3/23.
 */

public class DialogUtil {

    /**
     * 加载对话框
     * @param context
     * @return
     */
    public static QMUITipDialog showDialog(Context context) {
        QMUITipDialog tipDialog = new QMUITipDialog.Builder(context)
                .setTipWord("加载中")
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .create();
        tipDialog.show();
        tipDialog.setCancelable(true);
        return tipDialog;
    }
}
