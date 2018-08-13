package nexters.com.dear.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import nexters.com.dear.R;
import nexters.com.dear.adapter.WalkThroughAdapter;
import nexters.com.dear.model.WalkThroughInfo;

public class WalkThroughActivity extends AppCompatActivity {
    final String WALKTHROUGH_DESCRIPTION[] =
            {
                    "Dear에서 하루 세번, 익명의 누군가에게 편지를 통해 속마음을 털어놓을 수 있습니다.",
                    "작성한 편지는 밤 10시에 발송되며, 대화상대는 10명으로 제한됩니다.",
                    "과거에 편지와 문자를 받기위해 기다리던 소중한 시간을 기억하며 진심어린 편지를 나눠보세요."};
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_through);

        ArrayList<WalkThroughInfo> infoList = new ArrayList<>();
        for (int i = 0; i < WALKTHROUGH_DESCRIPTION.length; i++){
            WalkThroughInfo info = new WalkThroughInfo();
            info.setDescription(WALKTHROUGH_DESCRIPTION[i]);
            info.setPage("0" + (i + 1));
            info.setRes(null);
            infoList.add(info);
        }

        viewPager = findViewById(R.id.walk_through_viewpager);
        WalkThroughAdapter adapter = new WalkThroughAdapter(getApplicationContext(), infoList);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.walk_through_tab_selector);
        tabLayout.setupWithViewPager(viewPager, true);
    }
}
