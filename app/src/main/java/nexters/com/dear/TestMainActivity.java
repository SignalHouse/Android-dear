package nexters.com.dear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import nexters.com.dear.activity.LoginActivity;
import nexters.com.dear.activity.LostActivity;
import nexters.com.dear.activity.MainActivity;
import nexters.com.dear.activity.RegisterActivity;
import nexters.com.dear.activity.SplashActivity;
import nexters.com.dear.activity.WalkThroughActivity;

public class TestMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<Class> listActivities  = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);

        listActivities.add(MainActivity.class);
        listActivities.add(LoginActivity.class);
        listActivities.add(LostActivity.class);
        listActivities.add(RegisterActivity.class);
        listActivities.add(SplashActivity.class);
        listActivities.add(WalkThroughActivity.class);

        ListView lv = findViewById(R.id.test_main_listview);
        ArrayAdapter<Class> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listActivities);

        lv.setAdapter(Adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), listActivities.get(position));
                startActivity(i);
            }
        });

    }
}
