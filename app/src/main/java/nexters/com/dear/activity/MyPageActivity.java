package nexters.com.dear.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import nexters.com.dear.R;

public class MyPageActivity extends AppCompatActivity implements View.OnClickListener {
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
    @BindView(R.id.mypage_txt_toggle_status)
    TextView txtStatus;
    @BindView(R.id.mypage_toggle)
    Switch stNotification;
    @BindView(R.id.mypage_txt_nickname)
    EditText editNickname;
    @BindView(R.id.mypage_img_edit)
    ImageView imgEdit;
    @BindView(R.id.mypage_layout)
    View vLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        ButterKnife.bind(this);

        setToolbar();
        setClickListener();
    }

    private void setClickListener(){
        editNickname.setOnClickListener(this);
        imgEdit.setOnClickListener(this);
        vLayout.setOnClickListener(this);
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);

        btnBase.setVisibility(View.GONE);
        txtComplete.setVisibility(View.VISIBLE);
        txtTitle.setText("Setting");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mypage_txt_nickname:
            case R.id.mypage_img_edit:
                editNickname.setEnabled(true);
                InputMethodManager mgr = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.showSoftInput(editNickname, InputMethodManager.SHOW_IMPLICIT);
                break;
            default:
                editNickname.setEnabled(false);
        }
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
        Toast.makeText(getApplicationContext(), "Contact Clicked", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.mypage_dscrp_change_pw)
    void onChangePWClicked(){
        Intent i = new Intent(MyPageActivity.this, PWUpdateActivity.class);
        startActivity(i);
    }

    @OnCheckedChanged(R.id.mypage_toggle)
    void onCheckedChange(CompoundButton button, boolean checked){
        editNickname.setEnabled(false);
        if (checked)
            txtStatus.setText("ON");
        else
            txtStatus.setText("OFF");
    }
}
