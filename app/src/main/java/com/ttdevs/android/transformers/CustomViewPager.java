package com.ttdevs.android.transformers;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.ttdevs.android.utils.LogUtils;

/**
 * @author ttdevs
 */
public class CustomViewPager extends ViewPager {

    private enum STATUS {DECREASE, NULL, INCREASE}

    private STATUS mStatus = STATUS.NULL;

    public CustomViewPager(Context context) {
        this(context, null);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        setClipToPadding(false);
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int n) {
        if (mStatus == STATUS.NULL) {
            if (n == 0) {
                mStatus = STATUS.INCREASE;
            } else {
                mStatus = STATUS.DECREASE;
            }
        }

        int result = n;

        switch (mStatus) {
            case INCREASE:
                if (n == childCount - 1) {
                    result = getCurrentItem();
                } else if (n >= getCurrentItem()) {
                    result = n + 1;
                }
                break;
            case DECREASE:
                if (n == 0) {
                    result = getCurrentItem();
                } else if (n <= getCurrentItem()) {
                    result = n - 1;
                }
                break;

            default:
                break;
        }

        if (n == 0 && mStatus == STATUS.DECREASE) {
            mStatus = STATUS.NULL;
        }
        if (n == childCount - 1 && mStatus == STATUS.INCREASE) {
            mStatus = STATUS.NULL;
        }

        LogUtils.debug(String.format("ChildCount:%d n:%d result:%d getCurrentItem:%d status:%s", childCount, n, result, getCurrentItem(), mStatus.toString()));

        return result < 0 ? 0 : result;
    }
}