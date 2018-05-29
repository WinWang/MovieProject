package com.lepoint.ljfmvp.widget.autolayout;

import android.content.Context;
import android.util.AttributeSet;

import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButtonDrawable;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by admin on 2018/1/23.
 */

public class AutoRoundLinearLayout extends AutoLinearLayout {
    public AutoRoundLinearLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public AutoRoundLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, com.qmuiteam.qmui.R.attr.QMUIButtonStyle);
    }

    public AutoRoundLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        QMUIRoundButtonDrawable bg = QMUIRoundButtonDrawable.fromAttributeSet(context, attrs, defStyleAttr);
        QMUIViewHelper.setBackgroundKeepingPadding(this, bg);
    }

}
