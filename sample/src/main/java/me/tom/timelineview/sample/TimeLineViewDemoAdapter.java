package me.tom.timelineview.sample;

import android.content.Context;

import me.tom.timelineview.AbstractTimeLineViewAdapter;

public class TimeLineViewDemoAdapter extends AbstractTimeLineViewAdapter {

    public TimeLineViewDemoAdapter(Context context) {
        super(context);
    }

    @Override
    public int getRecordsCount() {
        return 20;
    }

    @Override
    public String getRecordTitle(int position) {
        return "Test Title";
    }

    @Override
    public String getRecordContent(int position) {
        if (position % 2 == 0) {
            return "Test Content";
        }
        return "Test Content1,Test Content2,Test Content3,Test Content4,Test Content5,Test Content6";
    }

    @Override
    public String getRecordDateTime(int position) {
        return "2016-12-12 12:12";
    }

    @Override
    public boolean isRecordHighlight(int position) {
        return position == 0;
    }
}
