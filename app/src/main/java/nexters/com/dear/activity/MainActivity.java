package nexters.com.dear.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nexters.com.dear.R;
import nexters.com.dear.adapter.LetterViewAdapter;
import nexters.com.dear.app.DearApp;
import nexters.com.dear.model.LetterItem;
import nexters.com.dear.util.DateCountDownTimer;
import nexters.com.dear.util.DearDialog;
import nexters.com.dear.util.DearDialogListener;

public class MainActivity extends AppCompatActivity implements DearDialogListener{
    @BindView(R.id.main_letter_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_txt_time_hr)
    TextView txtHr;
    @BindView(R.id.main_txt_time_min)
    TextView txtMin;
    @BindView(R.id.tool_bar_main_logo)
    ImageView btnLogo;
    @BindView(R.id.tool_bar_main_setting)
    ImageView btnSetting;
    @BindView(R.id.tool_bar_main)
    Toolbar toolbar;
    @BindView(R.id.main_fab)
    FloatingActionButton fab;
    @BindView(R.id.main_txt_edit)
    TextView txtEdit;

    private LetterViewAdapter letterAdapter;
    private ArrayList<LetterItem> letterItems = new ArrayList<>();
    private ArrayList<Integer> letterIDs = new ArrayList<>();
    private boolean isEditMode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);

        setToolbar();
        setLetterView();
        setTimer();
    }

    private void setLetterView(){
        Date curDate = Calendar.getInstance().getTime();
        //randomly generate dummy items
        for (int i  = 0; i < 100; i++) {
            LetterItem letter = new LetterItem("Title" + i, "Someone" + i, curDate, i);
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
        if (!curTime.before(cal.getTime())){ //if before the time
            cal.add(Calendar.DATE, 1);
        }
        DateCountDownTimer timer = new DateCountDownTimer(cal.getTimeInMillis() - curTime.getTime(), DateCountDownTimer.TIME_INTERVAL);
        timer.setType(DateCountDownTimer.TYPE_HHMM);
        timer.setTxtView(txtHr);
        timer.setTxtView(txtMin);
        timer.start();
    }

   private void setToolbar(){
        setSupportActionBar(toolbar);
   }

    @OnClick(R.id.tool_bar_main_setting)
    void onSettingClicked(){
        Intent i = new Intent(MainActivity.this, MyPageActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.tool_bar_main_logo)
    void onLogoClicked(){
        Toast.makeText(getApplicationContext(), "Logo clicked", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.main_fab)
    void onFabClicked(){
        Intent i = new Intent(MainActivity.this, WriteActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.main_txt_edit)
    void onEditClicked(){
        if (isEditMode){
            txtEdit.setText("편집");

            for (LetterItem letter : letterItems){
                if (letter.isSelected()){
                    letterIDs.add(letter.getLetterID());
                }
            }

            if(letterIDs.size() > 0) {
                final DearDialog dialog = new DearDialog(this, this, "정말 삭제하시겠어요?", DearDialog.DEARDIALOG_DOUBLE_BUTTON);
                dialog.show();
            }
            else {
                setCheckbox(false);
                isEditMode = false;
            }
        }
        else{
            txtEdit.setText("삭제");
            setCheckbox(true);
            isEditMode = true;
        }
    }

    private void setCheckbox(boolean checkVisibility){
        for(LetterItem letter : letterItems){
           letter.setCheckVisible(checkVisibility);
        }
        letterAdapter.notifyDataSetChanged();
    }

    @Override
    public void OKListener() {
        for (Integer id : letterIDs){
            for (LetterItem letter : letterItems){
                if(letter.getLetterID() == id) {
                    letterItems.remove(letter);
                    break;
                }
            }
        }
        letterIDs.clear();
        letterAdapter.notifyDataSetChanged();
        setCheckbox(false);
        isEditMode = false;
    }

    @Override
    public void CancelListener() {
        setCheckbox(false);
        isEditMode = false;

    }
}
