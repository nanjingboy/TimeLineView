package me.tom.timelineview.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import me.tom.timelineview.AbstractTimeLineViewAdapter;

public class TimeLineViewDemoAdapter extends AbstractTimeLineViewAdapter {

    private Context mContext;
    private LayoutInflater mInflater;

    public TimeLineViewDemoAdapter(Context context) {
        super(context);
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public boolean isRecordHighlight(int position) {
        return position == 0;
    }

    @Override
    public int getRecordCount() {
        return 20;
    }

    @Override
    public int getRecordBottomSize() {
        return mContext.getResources().getDimensionPixelSize(R.dimen.timeline_record_bottom_size);
    }

    @Override
    public View getRecordView(int position) {
        View view = mInflater.inflate(R.layout.time_line_record_view, null, false);
        ((TextView) view.findViewById(R.id.titleView)).setText("Test Title");
        ((TextView) view.findViewById(R.id.dateTimeView)).setText("2016-12-12 12:12");
        if (position % 2 == 0) {
            ((TextView) view.findViewById(R.id.contentView)).setText(
                    "Test Content"
            );
        } else {
            ((TextView) view.findViewById(R.id.contentView)).setText(
                    "Test Content1,Test Content2,Test Content3,Test Content4,Test Content5,Test Content6"
            );
        }
        return view;
    }
}
