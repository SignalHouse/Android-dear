package nexters.com.dear.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nexters.com.dear.R;
import nexters.com.dear.app.DearApp;
import nexters.com.dear.util.DateCountDownTimer;
import nexters.com.dear.widget.LinedEditText;

public class WriteLetterActivity extends AppCompatActivity {
    @BindView(R.id.writeletter_tool_bar)
    Toolbar toolbar;
    @BindView(R.id.tool_bar_base_go_back)
    ImageView btnGoBack;
    @BindView(R.id.tool_bar_base_btn)
    ImageView btnBase;
    @BindView(R.id.tool_bar_base_title)
    TextView txtTitle;
    @BindView(R.id.tool_bar_base_txt)
    TextView txtBase;
    @BindView(R.id.layout_write_fixed_edit_title)
    TextView txtLetterTitle;
    @BindView(R.id.layout_write_fixed_edit_contents)
    LinedEditText txtLetterContents;
    @BindView(R.id.writeletter_txt_remaining_time)
    TextView txtTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_letter);

        ButterKnife.bind(this);
        setToolbar();
        setTimer();
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        txtTitle.setText("");
        txtBase.setText("완료");
        btnBase.setVisibility(View.INVISIBLE);
        txtBase.setVisibility(View.VISIBLE);

        txtLetterContents.setFocusable(true);
        txtLetterTitle.setFocusable(true);
    }

    @OnClick({R.id.tool_bar_base_go_back, R.id.writeletter_dscrp_waiting, R.id.writeletter_img_left_arrow})
    void onGoBackClicked(){
        finish();
    }

    @OnClick(R.id.tool_bar_base_txt)
    void onBaseTxtClicked(){
        //save
    }

    @OnClick({R.id.layout_write_fixed_edit_contents, R.id.layout_write_fixed_edit_title})
    void onWriteLetterClicked(){
        txtBase.setVisibility(View.VISIBLE);
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
