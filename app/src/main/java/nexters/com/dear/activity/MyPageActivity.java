package nexters.com.dear.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nexters.com.dear.R;

public class MyPageActivity extends AppCompatActivity {
    @BindView(R.id.mypage_toolbar)
    Toolbar toolbar;
    @BindView(R.id.tool_bar_base_btn)
    ImageView btnBase;
    @BindView(R.id.tool_bar_base_go_back)
    ImageView btnGoback;
    @BindView(R.id.tool_bar_base_title)
    TextView txtTitle;
    @BindView(R.id.tool_bar_base_txt)
    TextView txtComplete;
    @BindView(R.id.mypage_dscrp_change_pw)
    TextView txtChangePW;
    @BindView(R.id.mypage_dscrp_contact)
    TextView txtContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        ButterKnife.bind(this);
        setToolbar();
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);

        btnBase.setVisibility(View.GONE);
        txtComplete.setVisibility(View.VISIBLE);
        txtTitle.setText("Setting");

    }
    @OnClick(R.id.tool_bar_base_go_back)
    void onGoBackClicked(){
        finish();
    }

    @OnClick(R.id.tool_bar_base_btn)
    void onConfirmClicked(){
        finish();
    }

    @OnClick(R.id.mypage_dscrp_contact)
    void onContactClicked(){

    }

    @OnClick(R.id.mypage_dscrp_change_pw)
    void onChangePWClikced(){
        Intent i = new Intent(MyPageActivity.this, PWUpdateActivity.class);
        startActivity(i);
    }
}
