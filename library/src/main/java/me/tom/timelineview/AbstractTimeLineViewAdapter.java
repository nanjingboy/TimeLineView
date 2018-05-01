package me.tom.timelineview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public abstract class AbstractTimeLineViewAdapter extends BaseAdapter {

    private int mLineWidth;
    private int mLineColor;
    private int mCircleRadius;
    private int mHighlightCircleColor;
    private int mHighlightCircleBorderWidth;
    private int mHighlightCircleBorderColor;

    private static class ViewHolder {
        TimeLineRecordCircleView circleView;
        View lineView;
        LinearLayout contentPanel;
        LinearLayout sliderPanel;
    }

    private LayoutInflater mInflater;

    public AbstractTimeLineViewAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    void setLineWidth(int lineWidth) {
        mLineWidth = lineWidth;
    }

    void setLineColor(int lineColor) {
        mLineColor = lineColor;
    }

    void setCircleRadius(int circleRadius) {
        mCircleRadius = circleRadius;
    }

    void setHighlightCircleColor(int highlightCircleColor) {
        mHighlightCircleColor = highlightCircleColor;
    }

    void setHighlightCircleBorderWidth(int highlightCircleBorderWidth) {
        mHighlightCircleBorderWidth = highlightCircleBorderWidth;
    }

    void setHighlightCircleBorderColor(int highlightCircleBorderColor) {
        mHighlightCircleBorderColor = highlightCircleBorderColor;
    }

    @Override
    public final int getCount() {
        return getRecordCount();
    }

    @Override
    public final Object getItem(int position) {
        return position;
    }

    @Override
    public final long getItemId(int position) {
        return position;
    }

    @Override
    public final View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.time_line_record_container, null, false);
            holder.circleView = view.findViewById(R.id.circleView);
            holder.lineView = view.findViewById(R.id.lineView);
            holder.sliderPanel = view.findViewById(R.id.sliderPanel);
            holder.contentPanel = view.findViewById(R.id.contentPanel);
            holder.contentPanel.addView(getRecordView(position));
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        int circleRadius;
        boolean isRecordHighlight = isRecordHighlight(position);
        if (isRecordHighlight) {
            circleRadius = mCircleRadius + mHighlightCircleBorderWidth;
        } else {
            circleRadius = mCircleRadius;
        }

        LinearLayout.LayoutParams sliderPanelParams = (LinearLayout.LayoutParams) holder.sliderPanel.getLayoutParams();
        sliderPanelParams.width = (mHighlightCircleBorderWidth + mCircleRadius) * 2;
        holder.sliderPanel.setLayoutParams(sliderPanelParams);

        LinearLayout.LayoutParams circleViewParams = (LinearLayout.LayoutParams) holder.circleView.getLayoutParams();
        circleViewParams.width = circleRadius * 2;
        circleViewParams.height = circleRadius * 2;
        if (isRecordHighlight) {
            circleViewParams.leftMargin = 0;
        } else {
            circleViewParams.leftMargin = mHighlightCircleBorderWidth;
        }
        holder.circleView.setLayoutParams(circleViewParams);
        holder.circleView.setCircleRadius(circleRadius);
        if (isRecordHighlight) {
            holder.circleView.setCircleColor(mHighlightCircleColor);
        } else {
            holder.circleView.setCircleColor(mLineColor);
        }
        holder.circleView.setHighlightCircleBorderWidth(mHighlightCircleBorderWidth);
        holder.circleView.setHighlightCircleBorderColor(mHighlightCircleBorderColor);
        holder.circleView.setIsHighlight(isRecordHighlight);

        LinearLayout.LayoutParams lineViewParams = (LinearLayout.LayoutParams) holder.lineView.getLayoutParams();
        lineViewParams.leftMargin = mCircleRadius + mHighlightCircleBorderWidth - mLineWidth / 2;
        lineViewParams.width = mLineWidth;

        holder.lineView.setBackgroundColor(mLineColor);
        holder.lineView.setLayoutParams(lineViewParams);

        LinearLayout.LayoutParams contentPanelParams = (LinearLayout.LayoutParams) holder.contentPanel.getLayoutParams();
        contentPanelParams.bottomMargin = getRecordBottomSize();
        holder.contentPanel.setLayoutParams(contentPanelParams);
        return view;
    }

    abstract public int getRecordCount();
    abstract public int getRecordBottomSize();
    abstract public boolean isRecordHighlight(int position);
    abstract public View getRecordView(int position);
}
