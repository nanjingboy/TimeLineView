package me.tom.timelineview;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class AbstractTimeLineViewAdapter extends BaseAdapter {

    private int mTitleFontSize;
    private int mContentFontSize;
    private int mDateTimeFontSize;

    private int mTextColor;
    private int mHighlightTextColor;

    private int mLineWidth;
    private int mCircleRadius;
    private int mHighlightCircleBorderWidth;
    private int mHighlightCircleBorderColor;

    private int mContentPanelBottomSpaceSize;

    private static class ViewHolder {
        TimeLineRecordCircleView circleView;
        View lineView;
        LinearLayout contentPanel;
        TextView titleView;
        TextView contentView;
        TextView dateTimeView;
    }

    private LayoutInflater mInflater;

    public AbstractTimeLineViewAdapter(Context context) {
        Resources resources = context.getResources();
        mContentPanelBottomSpaceSize = resources.getDimensionPixelSize(
                R.dimen.timeline_view_record_content_panel_bottom_space_size
        );
        mInflater = LayoutInflater.from(context);
    }

    void setTitleFontSize(int titleFontSize) {
        mTitleFontSize = titleFontSize;
    }

    void setContentFontSize(int contentFontSize) {
        mContentFontSize = contentFontSize;
    }

    void setDateTimeFontSize(int dateTimeFontSize) {
        mDateTimeFontSize = dateTimeFontSize;
    }

    void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    void setHighlightTextColor(int highlightTextColor) {
        mHighlightTextColor = highlightTextColor;
    }

    void setLineWidth(int lineWidth) {
        mLineWidth = lineWidth;
    }

    void setCircleRadius(int circleRadius) {
        mCircleRadius = circleRadius;
    }

    void setHighlightCircleBorderWidth(int highlightCircleBorderWidth) {
        mHighlightCircleBorderWidth = highlightCircleBorderWidth;
    }

    void setHighlightCircleBorderColor(int highlightCircleBorderColor) {
        mHighlightCircleBorderColor = highlightCircleBorderColor;
    }

    @Override
    public final int getCount() {
        return getRecordsCount();
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
            view = mInflater.inflate(R.layout.timeline_record, null, false);
            holder.circleView = (TimeLineRecordCircleView) view.findViewById(R.id.circleView);
            holder.lineView = view.findViewById(R.id.lineView);
            holder.contentPanel = (LinearLayout) view.findViewById(R.id.contentPanel);
            holder.titleView = (TextView) view.findViewById(R.id.titleView);
            holder.contentView = (TextView) view.findViewById(R.id.contentView);
            holder.dateTimeView = (TextView) view.findViewById(R.id.dateTimeView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        int textColor;
        boolean isRecordHighlight = isRecordHighlight(position);
        if (isRecordHighlight) {
            textColor = mHighlightTextColor;
        } else {
            textColor = mTextColor;
        }

        LinearLayout.LayoutParams circleViewParams = (LinearLayout.LayoutParams) holder.circleView.getLayoutParams();
        circleViewParams.width = mCircleRadius * 2;
        circleViewParams.height = mCircleRadius * 2;
        holder.circleView.setLayoutParams(circleViewParams);
        holder.circleView.setCircleRadius(mCircleRadius);
        holder.circleView.setCircleColor(textColor);
        holder.circleView.setHighlightCircleBorderWidth(mHighlightCircleBorderWidth);
        holder.circleView.setHighlightCircleBorderColor(mHighlightCircleBorderColor);
        holder.circleView.setIsHighlight(isRecordHighlight);

        LinearLayout.LayoutParams lineViewParams = (LinearLayout.LayoutParams) holder.lineView.getLayoutParams();
        lineViewParams.leftMargin = mCircleRadius - mLineWidth / 2;
        lineViewParams.width = mLineWidth;

        int recordsCount = getRecordsCount();
        holder.lineView.setBackgroundColor(mTextColor);
        holder.lineView.setLayoutParams(lineViewParams);
        if (recordsCount == 1 || position < recordsCount - 1) {
            holder.lineView.setVisibility(View.VISIBLE);
        } else {
            holder.lineView.setVisibility(View.INVISIBLE);
        }

        LinearLayout.LayoutParams contentPanelParams = (LinearLayout.LayoutParams) holder.contentPanel.getLayoutParams();
        if (position == recordsCount - 1) {
            contentPanelParams.bottomMargin = 0;
        }  else {
            contentPanelParams.bottomMargin = mContentPanelBottomSpaceSize;
        }
        holder.contentPanel.setLayoutParams(contentPanelParams);

        holder.titleView.setText(getRecordTitle(position));
        holder.titleView.setTextColor(textColor);
        holder.titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleFontSize);

        holder.contentView.setText(getRecordContent(position));
        holder.contentView.setTextColor(textColor);
        holder.contentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContentFontSize);

        holder.dateTimeView.setText(getRecordDateTime(position));
        holder.dateTimeView.setTextColor(textColor);
        holder.dateTimeView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mDateTimeFontSize);
        return view;
    }

    abstract public int getRecordsCount();
    abstract public String getRecordTitle(int position);
    abstract public String getRecordContent(int position);
    abstract public String getRecordDateTime(int position);
    abstract public boolean isRecordHighlight(int position);
}
