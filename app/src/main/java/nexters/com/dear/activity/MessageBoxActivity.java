package nexters.com.dear.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import nexters.com.dear.R;
import nexters.com.dear.adapter.WaitingLetterAdapter;
import nexters.com.dear.model.LetterItem;

public class MessageBoxActivity extends AppCompatActivity {
    @BindView(R.id.messagebox_recycler_view_letters)
    RecyclerView mRecyclerView;

    WaitingLetterAdapter waitAdapter;
    private ArrayList<LetterItem> letterItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_box);
        ButterKnife.bind(this);


        setWaitingView();
    }

    private void setWaitingView(){
        Date curDate = Calendar.getInstance().getTime();
        //dummy data
        for (int i = 0; i < 100; i++){
            LetterItem letter = new LetterItem("Title" + i, "Someone" + i, curDate, i );
            letter.setDate(Calendar.getInstance().getTime());
            letterItems.add(letter);
        }

        waitAdapter = new WaitingLetterAdapter(letterItems);
        mRecyclerView.setAdapter(waitAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
