package me.tom.timelineview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class TimeLineView extends LinearLayout {

    private int mTitleFontSize;
    private int mContentFontSize;
    private int mDateTimeFontSize;

    private int mTextColor;
    private int mHighlightTextColor;

    private int mLineWidth;
    private int mCircleRadius;
    private int mHighlightCircleBorderWidth;
    private int mHighlightCircleBorderColor;

    private AbstractTimeLineViewAdapter mAdapter;

    private final DataSetObserver mObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            refreshViews();
        }

        @Override
        public void onInvalidated() {
            removeAllViews();
        }
    };

    public TimeLineView(Context context) {
        this(context, null);
    }

    public TimeLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeLineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Resources resources = getResources();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimeLineView);
        mTitleFontSize = typedArray.getDimensionPixelSize(
                R.styleable.TimeLineView_titleFontSize,
                resources.getDimensionPixelOffset(R.dimen.timeline_view_title_font_size)
        );
        mContentFontSize = typedArray.getDimensionPixelSize(
                R.styleable.TimeLineView_contentFontSize,
                resources.getDimensionPixelSize(R.dimen.timeline_view_content_font_size)
        );
        mDateTimeFontSize = typedArray.getDimensionPixelSize(
                R.styleable.TimeLineView_dateTimeFontSize,
                resources.getDimensionPixelSize(R.dimen.timeline_view_datetime_font_size)
        );
        mTextColor = typedArray.getColor(
                R.styleable.TimeLineView_textColor,
                ContextCompat.getColor(context, R.color.colorTimeLineViewTextColor)
        );
        mHighlightTextColor = typedArray.getColor(
                R.styleable.TimeLineView_highlightTextColor,
                ContextCompat.getColor(context, R.color.colorTimeLineViewHighlightTextColor)
        );
        mLineWidth = typedArray.getDimensionPixelSize(
                R.styleable.TimeLineView_lineWidth,
                resources.getDimensionPixelSize(R.dimen.timeline_view_line_width)
        );
        mCircleRadius = typedArray.getDimensionPixelSize(
                R.styleable.TimeLineView_circleRadius,
                resources.getDimensionPixelSize(R.dimen.timeline_view_circle_radius)
        );
        mHighlightCircleBorderWidth = typedArray.getDimensionPixelSize(
                R.styleable.TimeLineView_highlightCircleBorderWidth,
                resources.getDimensionPixelSize(R.dimen.timeline_view_highlight_circle_border_width)
        );
        mHighlightCircleBorderColor = typedArray.getColor(
                R.styleable.TimeLineView_highlightCircleBorderColor,
                ContextCompat.getColor(context, R.color.colorTimeLineViewHighlightCircleBorderColor)
        );
        typedArray.recycle();
        setOrientation(VERTICAL);
    }

    public void setTitleFontSize(int titleFontSize) {
        mTitleFontSize = titleFontSize;
        if (mAdapter != null) {
            mAdapter.setTitleFontSize(titleFontSize);
        }
    }

    public void setContentFontSize(int contentFontSize) {
        mContentFontSize = contentFontSize;
        if (mAdapter != null) {
            mAdapter.setContentFontSize(contentFontSize);
        }
    }

    public void setDateTimeFontSize(int dateTimeFontSize) {
        mDateTimeFontSize = dateTimeFontSize;
        if (mAdapter != null) {
            mAdapter.setDateTimeFontSize(dateTimeFontSize);
        }
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
        if (mAdapter != null) {
            mAdapter.setTextColor(textColor);
        }
    }

    public void setHighlightTextColor(int highlightTextColor) {
        mHighlightTextColor = highlightTextColor;
        if (mAdapter != null) {
            mAdapter.setHighlightTextColor(highlightTextColor);
        }
    }

    public void setLineWidth(int lineWidth) {
        mLineWidth = lineWidth;
        if (mAdapter != null) {
            mAdapter.setLineWidth(lineWidth);
        }
    }

    public void setCircleRadius(int circleRadius) {
        mCircleRadius = circleRadius;
        if (mAdapter != null) {
            mAdapter.setCircleRadius(circleRadius);
        }
    }

    public void setHighlightCircleBorderWidth(int highlightCircleBorderWidth) {
        mHighlightCircleBorderWidth = highlightCircleBorderWidth;
        if (mAdapter != null) {
            mAdapter.setHighlightCircleBorderWidth(highlightCircleBorderWidth);
        }
    }

    public void setHighlightCircleBorderColor(int highlightCircleBorderColor) {
        mHighlightCircleBorderColor = highlightCircleBorderColor;
        if (mAdapter != null) {
            mAdapter.setHighlightCircleBorderColor(highlightCircleBorderColor);
        }
    }

    public void setAdapter(AbstractTimeLineViewAdapter adapter) {
        if (mAdapter != null) {
            mAdapter.unregisterDataSetObserver(mObserver);
        }
        mAdapter = adapter;
        if (mAdapter != null) {
            mAdapter.registerDataSetObserver(mObserver);
            mAdapter.setTitleFontSize(mTitleFontSize);
            mAdapter.setContentFontSize(mContentFontSize);
            mAdapter.setDateTimeFontSize(mDateTimeFontSize);
            mAdapter.setTextColor(mTextColor);
            mAdapter.setHighlightTextColor(mHighlightTextColor);
            mAdapter.setLineWidth(mLineWidth);
            mAdapter.setCircleRadius(mCircleRadius);
            mAdapter.setHighlightCircleBorderWidth(mHighlightCircleBorderWidth);
            mAdapter.setHighlightCircleBorderColor(mHighlightCircleBorderColor);
        }
        refreshViews();
    }

    protected void refreshViews() {
        removeAllViews();
        if (mAdapter != null) {
            int count = mAdapter.getRecordsCount();
            for (int position = 0; position < count; position++) {
                View view = mAdapter.getView(position, null, this);
                addView(view, position);
            }
        }
    }
}
