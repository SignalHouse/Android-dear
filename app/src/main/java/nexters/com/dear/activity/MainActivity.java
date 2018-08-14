package nexters.com.dear.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import nexters.com.dear.R;
import nexters.com.dear.adapter.LetterViewAdapter;
import nexters.com.dear.app.DearApp;
import nexters.com.dear.model.LetterItem;
import nexters.com.dear.util.DateCountDownTimer;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_letter_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_txt_time_hr)
    TextView txtHr;
    @BindView(R.id.main_txt_time_min)
    TextView txtMin;

    private LetterViewAdapter letterAdapter;
    private ArrayList<LetterItem> letterItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setLetterView();
        setTimer();
    }

    private void setLetterView(){
        Date curDate = Calendar.getInstance().getTime();
        //randomly generate dummy items
        for (int i  = 0; i < 100; i++) {
            LetterItem letter = new LetterItem("Title" + i, "Someone" + i, curDate);
            if (i % 5 == 0) letter.setNew(false);
            letterItems.add(letter);
        }
        letterAdapter = new LetterViewAdapter(letterItems);
        mRecyclerView.setAdapter(letterAdapter);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setTimer(){
        Date curTime = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, DearApp.getAppInstance().getSEND_HR());
        cal.set(Calendar.MINUTE, DearApp.getAppInstance().getSEND_MIN());
        cal.set(Calendar.SECOND, DearApp.getAppInstance().getSEND_SEC());

        DateCountDownTimer timer = new DateCountDownTimer(cal.getTimeInMillis() - curTime.getTime(), DateCountDownTimer.TIME_INTERVAL);
        timer.setType(DateCountDownTimer.TYPE_HHMM);
        timer.setTxtView(txtHr);
        timer.setTxtView(txtMin);
        timer.start();

    }
}
