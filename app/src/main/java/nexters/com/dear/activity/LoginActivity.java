package nexters.com.dear.activity;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import nexters.com.dear.R;

public class LoginActivity extends AppCompatActivity {

    EditText inputEmail, inputPassword;
    TextView tvToRegister, tvFindPassword;
    Button btnToSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        inputEmail = (EditText) findViewById(R.id.login_txt_id);
        inputPassword = (EditText) findViewById(R.id.login_txt_pw);
        tvFindPassword = (TextView) findViewById(R.id.login_txt_find_pw);
        tvToRegister = (TextView) findViewById(R.id.login_txt_register);
        btnToSignIn = (Button) findViewById(R.id.login_btn_sign_in);

        btnToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvFindPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (LoginActivity.this, LostActivity.class);
                startActivity(i);
                finish();
            }
        });

        tvToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
