package com.lepoint.ljfmvp.widget.autolayout;

import android.content.Context;
import android.util.AttributeSet;

import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButtonDrawable;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * Created by admin on 2018/1/23.
 */

public class AutoRoundRelativielayout extends AutoRelativeLayout {
    public AutoRoundRelativielayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public AutoRoundRelativielayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, com.qmuiteam.qmui.R.attr.QMUIButtonStyle);
    }

    public AutoRoundRelativielayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        QMUIRoundButtonDrawable bg = QMUIRoundButtonDrawable.fromAttributeSet(context, attrs, defStyleAttr);
        QMUIViewHelper.setBackgroundKeepingPadding(this, bg);
    }
}
