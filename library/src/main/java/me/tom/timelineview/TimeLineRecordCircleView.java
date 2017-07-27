package me.tom.timelineview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TimeLineRecordCircleView extends View {

    private int mCircleRadius;
    private int mCircleColor;

    private int mHighlightCircleBorderWidth;
    private int mHighlightCircleBorderColor;

    private boolean mIsHighlight;

    private Paint mPaint;

    public TimeLineRecordCircleView(Context context) {
        this(context, null);
    }

    public TimeLineRecordCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeLineRecordCircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setCircleRadius(int circleRadius) {
        mCircleRadius = circleRadius;
        invalidate();
    }

    public void setCircleColor(int circleColor) {
        mCircleColor = circleColor;
        invalidate();
    }

    public void setHighlightCircleBorderWidth(int highlightCircleBorderWidth) {
        mHighlightCircleBorderWidth = highlightCircleBorderWidth;
        invalidate();
    }

    public void setHighlightCircleBorderColor(int highlightCircleBorderColor) {
        mHighlightCircleBorderColor = highlightCircleBorderColor;
        invalidate();
    }

    public void setIsHighlight(boolean isHighlight) {
        mIsHighlight = isHighlight;
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mIsHighlight) {
            mPaint.setColor(mHighlightCircleBorderColor);
            canvas.drawCircle(mCircleRadius, mCircleRadius, mCircleRadius, mPaint);
            mPaint.setColor(mCircleColor);
            canvas.drawCircle(mCircleRadius, mCircleRadius, mCircleRadius - mHighlightCircleBorderWidth / 2, mPaint);
        } else {
            mPaint.setColor(mCircleColor);
            canvas.drawCircle(mCircleRadius, mCircleRadius, mCircleRadius, mPaint);
        }
    }
}
