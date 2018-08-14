package nexters.com.dear.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import nexters.com.dear.R;
import nexters.com.dear.adapter.LetterViewAdapter;
import nexters.com.dear.model.LetterItem;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_letter_recycler_view)
    RecyclerView mRecyclerView;
    private LetterViewAdapter letterAdapter;
    private ArrayList<LetterItem> letterItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setLetterView();
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
}
