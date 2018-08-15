package nexters.com.dear.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nexters.com.dear.R;
import nexters.com.dear.adapter.WaitingLetterAdapter;
import nexters.com.dear.app.DearApp;
import nexters.com.dear.model.LetterItem;
import nexters.com.dear.util.DateCountDownTimer;

public class WriteActivity extends AppCompatActivity {
    @BindView(R.id.write_recycler_waiting_letters)
    RecyclerView mRecyclerView;
    @BindView(R.id.write_txt_remaining_time)
    TextView txtTimer;
    @BindView(R.id.write_txt_waiting_num)
    TextView txtWaitings;
    @BindView(R.id.write_txt_remaining_letters)
    TextView txtRemainings;
//    @BindView(R.id.mypage_toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.tool_bar_base_btn)
//    ImageView btnBase;
//    @BindView(R.id.tool_bar_base_go_back)
//    ImageView btnGoback;
//    @BindView(R.id.tool_bar_base_title)
//    TextView txtTitle;
//    @BindView(R.id.tool_bar_base_txt)
//    TextView txtComplete;

    ArrayList<LetterItem> letterItems;
    WaitingLetterAdapter waitAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        ButterKnife.bind(this);
        setWaitingLetters();
        setTimer();
//        setToolbar();
    }

    private void setWaitingLetters(){
        Date curDate = Calendar.getInstance().getTime();
        letterItems = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            LetterItem letterItem = new LetterItem("Title" + i, "Someone" + i, curDate);
            letterItems.add(letterItem);
        }
        waitAdapter = new WaitingLetterAdapter(letterItems);
        mRecyclerView.setAdapter(waitAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));
    }

    private void setTimer(){
        Date curTime = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, DearApp.getAppInstance().getSEND_HR());
        cal.set(Calendar.MINUTE, DearApp.getAppInstance().getSEND_MIN());
        cal.set(Calendar.SECOND, DearApp.getAppInstance().getSEND_SEC());

        if (!curTime.before(cal.getTime())){ //if before the time
            cal.add(Calendar.DATE, 1);
        }
        DateCountDownTimer timer = new DateCountDownTimer(cal.getTimeInMillis() - curTime.getTime(), DateCountDownTimer.TIME_INTERVAL);
        timer.setType(DateCountDownTimer.TYPE_HHMMSS);
        timer.setTxtView(txtTimer);
        timer.start();
    }

//    private void setToolbar(){
//        setSupportActionBar(toolbar);
//        txtTitle.setText("Write");
//        txtComplete.setVisibility(View.GONE);
//        btnBase.setVisibility(View.VISIBLE);
//    }
//
//    @OnClick(R.id.tool_bar_base_go_back)
//    void onGoBackClicked(){
//        finish();
//    }
}
