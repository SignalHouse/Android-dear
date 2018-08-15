package nexters.com.dear.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nexters.com.dear.R;

public class LostActivity extends AppCompatActivity {
    @BindView(R.id.lost_tool_bar)
    Toolbar toolbar;
    @BindView(R.id.tool_bar_base_btn)
    ImageView btnBase;
    @BindView(R.id.tool_bar_base_go_back)
    ImageView btnGoback;
    @BindView(R.id.tool_bar_base_title)
    TextView txtTitle;
    @BindView(R.id.tool_bar_base_txt)
    TextView txtComplete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        ButterKnife.bind(this);
        setToolbar();
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        txtTitle.setText("Reset Password");
        txtComplete.setVisibility(View.GONE);
        btnBase.setVisibility(View.GONE);
    }

    @OnClick(R.id.tool_bar_base_go_back)
    void onGoBackClicked(){
        finish();
    }
}
