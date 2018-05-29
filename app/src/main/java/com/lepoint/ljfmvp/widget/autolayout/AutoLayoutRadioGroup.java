package com.lepoint.ljfmvp.widget.autolayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.zhy.autolayout.AutoLayoutInfo;
import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * Created by admin on 2018/1/18.
 */

public class AutoLayoutRadioGroup extends RadioGroup {
    private AutoLayoutHelper mHelper = new AutoLayoutHelper(this);
    public AutoLayoutRadioGroup(Context context) {
        super(context);
    }
    public AutoLayoutRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public RadioGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode()) {
            mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    public static class LayoutParams extends RadioGroup.LayoutParams
            implements AutoLayoutHelper.AutoLayoutParams {

        private AutoLayoutInfo mAutoLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs);
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(int w, int h, float initWeight) {
            super(w, h, initWeight);
        }

        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);

        }

        public LayoutParams(LayoutParams source)
        {
            this((MarginLayoutParams) source);
            mAutoLayoutInfo = source.mAutoLayoutInfo;
        }
        @Override
        public AutoLayoutInfo getAutoLayoutInfo() {
            return mAutoLayoutInfo;
        }
    }
}
