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

    private int mLineWidth;
    private int mLineColor;
    private int mCircleRadius;
    private int mHighlightCircleColor;
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
        mLineWidth = typedArray.getDimensionPixelSize(
                R.styleable.TimeLineView_lineWidth,
                resources.getDimensionPixelSize(R.dimen.timeline_view_line_width)
        );
        mLineColor = typedArray.getColor(
                R.styleable.TimeLineView_lineColor,
                ContextCompat.getColor(context, R.color.colorTimeLineViewLineColor)
        );
        mCircleRadius = typedArray.getDimensionPixelSize(
                R.styleable.TimeLineView_circleRadius,
                resources.getDimensionPixelSize(R.dimen.timeline_view_circle_radius)
        );
        mHighlightCircleColor = typedArray.getColor(
                R.styleable.TimeLineView_highlightCircleColor,
                ContextCompat.getColor(context, R.color.colorTimeLineViewHighlightCircleColor)
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


    public void setLineWidth(int lineWidth) {
        mLineWidth = lineWidth;
        if (mAdapter != null) {
            mAdapter.setLineWidth(lineWidth);
        }
    }

    public void setLineColor(int lineColor) {
        mLineColor = lineColor;
        if (mAdapter != null) {
            mAdapter.setLineColor(lineColor);
        }
    }

    public void setCircleRadius(int circleRadius) {
        mCircleRadius = circleRadius;
        if (mAdapter != null) {
            mAdapter.setCircleRadius(circleRadius);
        }
    }

    public void setHighlightCircleColor(int highlightCircleColor) {
        mHighlightCircleColor = highlightCircleColor;
        if (mAdapter != null) {
            mAdapter.setHighlightCircleColor(highlightCircleColor);
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
            mAdapter.setLineWidth(mLineWidth);
            mAdapter.setLineColor(mLineColor);
            mAdapter.setCircleRadius(mCircleRadius);
            mAdapter.setHighlightCircleColor(mHighlightCircleColor);
            mAdapter.setHighlightCircleBorderWidth(mHighlightCircleBorderWidth);
            mAdapter.setHighlightCircleBorderColor(mHighlightCircleBorderColor);
        }
        refreshViews();
    }

    protected void refreshViews() {
        removeAllViews();
        if (mAdapter != null) {
            int count = mAdapter.getRecordCount();
            for (int position = 0; position < count; position++) {
                View view = mAdapter.getView(position, null, this);
                addView(view, position);
            }
        }
    }
}
