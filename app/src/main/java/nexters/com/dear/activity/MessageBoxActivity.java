package nexters.com.dear.activity;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nexters.com.dear.R;
import nexters.com.dear.adapter.WaitingLetterAdapter;
import nexters.com.dear.app.DearApp;
import nexters.com.dear.model.LetterItem;
import nexters.com.dear.util.DateCountDownTimer;

public class MessageBoxActivity extends AppCompatActivity {
    @BindView(R.id.messagebox_recycler_view_letters)
    RecyclerView mRecyclerView;
    @BindView(R.id.messagebox_tool_bar)
    Toolbar toolbar;
    @BindView(R.id.tool_bar_base_title)
    TextView txtTitle;
    @BindView(R.id.tool_bar_base_btn)
    ImageView btnBase;
    @BindView(R.id.tool_bar_base_txt)
    TextView txtBase;
    @BindView(R.id.tool_bar_base_go_back)
    ImageView btnGoBack;
    @BindView(R.id.messagebox_txt_remaining_time)
    TextView txtTimer;

    WaitingLetterAdapter waitAdapter;
    private ArrayList<LetterItem> letterItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_box);
        ButterKnife.bind(this);

        setToolbar();
        setTimer();
        setWaitingView();
    }

    private void setWaitingView(){
        Date curDate = Calendar.getInstance().getTime();
        //dummy data
        for (int i = 0; i < 100; i++){
            LetterItem letter = new LetterItem("Title" + i, "Someone" + i, curDate, i );
            letter.setDate(Calendar.getInstance().getTime());
            letter.setWidthMatchParent(true);
            letterItems.add(letter);
        }

        waitAdapter = new WaitingLetterAdapter(MessageBoxActivity.this,letterItems);
        mRecyclerView.setAdapter(waitAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.scrollToPosition(0);
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        txtTitle.setText("");
        txtBase.setVisibility(View.INVISIBLE);
        btnBase.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.tool_bar_base_go_back)
    void onGoBackClicked(){
        finish();
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

}
