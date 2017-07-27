package me.tom.timelineview.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.tom.timelineview.TimeLineView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimeLineView timeLineView = (TimeLineView) findViewById(R.id.timeLineView);
        final TimeLineViewDemoAdapter adapter = new TimeLineViewDemoAdapter(this);
        timeLineView.setAdapter(adapter);
    }
}