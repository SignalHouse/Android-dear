package nexters.com.dear.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import nexters.com.dear.R;
import nexters.com.dear.adapter.WaitingLetterAdapter;
import nexters.com.dear.model.LetterItem;

public class WriteActivity extends AppCompatActivity {
    @BindView(R.id.write_recycler_waiting_letters)
    RecyclerView mRecyclerView;

    ArrayList<LetterItem> letterItems;
    WaitingLetterAdapter waitAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        ButterKnife.bind(this);
        setWaitingLetters();
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
}
