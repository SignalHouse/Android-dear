package nexters.com.dear.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;
import nexters.com.dear.R;
import nexters.com.dear.Retrofit.Response.ResponseToken;
import nexters.com.dear.Retrofit.RetroCallBack;
import nexters.com.dear.Retrofit.RetroClient;
import nexters.com.dear.app.DearApp;

public class LoginActivity extends AppCompatActivity {

    EditText inputEmail, inputPassword;
    TextView tvToRegister, tvFindPassword;
    String email, password;
    Button btnToSignIn;

    RetroClient retroClient;
    boolean isOK = false;

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



//        btnToSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                email = inputEmail.getText().toString();
//                password = inputPassword.getText().toString();
////                new LoginActivity.login().execute(getString(R.string.auth_server_url)+"/api/user/login");
////                retroClient.postLogin();
//
//            }
//        });

        tvFindPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (LoginActivity.this, LostActivity.class);
                startActivity(i);

            }
        });

        tvToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (LoginActivity.this, RegisterActivity.class);
                startActivity(i);

            }
        });
        retroClient = RetroClient.getInstance(this).createBaseApi();
        ButterKnife.bind(this);
    }
    @OnClick(R.id.login_btn_sign_in)
    void onSignInClicked(){
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("email", inputEmail.getText().toString());
        parameters.put("password", inputPassword.getText().toString());

//        email = inputEmail.getText().toString();
//        password = inputPassword.getText().toString();
//        new LoginActivity.login().execute(getString(R.string.auth_server_url) + "/api/user/login");
        retroClient.postLogin(parameters, new RetroCallBack() {
            @Override
            public void onError(Throwable t) {
                Log.d("Login Result", "Error");
            }

            @Override
            public void onSuccess(int code, Object receivedData) {
                Log.d("Login Result", "Success");
                ResponseToken responseToken = (ResponseToken) receivedData;
                setToken(responseToken.token);

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(int code) {
                Log.d("Login Result", "Failed");
            }
        });
    }

    public class login extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("로그인 중...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {

            try {

                try {
                    for (int i = 0; i < 5; i++) {
                        progressDialog.setProgress(i * 30);
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //JSONObject를 만들고 key value 형식으로 값을 저장
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("email",email);
                jsonObject.accumulate("password",password);
                HttpURLConnection con = null;
                BufferedReader reader = null;

                try{

                    URL url = new URL(urls[0]);

                    //연결

                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");//POST방식으로 보냄
                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                    con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
                    con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
                    con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                    con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미

                    con.connect();

                    //서버로 보내기위해서 스트림 만듬
                    OutputStream outStream = con.getOutputStream();

                    //버퍼를 생성하고 넣음
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(jsonObject.toString());
                    writer.flush();
                    writer.close();//버퍼를 받아줌

                    //서버로 부터 데이터를 받음
                    InputStream stream = con.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while((line = reader.readLine()) != null){

                        buffer.append(line);

                    }
                    setToken(buffer.toString());
                    isOK = true;
                    return buffer.toString();

                } catch (MalformedURLException e){
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();

                } finally {

                    if(con != null){
                        con.disconnect();
                    }

                    try {

                        if(reader != null){
                            reader.close();//버퍼를 닫아줌
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if(isOK) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }
    }

    public void setToken(String token) {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        DearApp.getAppInstance().setToken(token);
        editor.putString("token", token);
        editor.commit();
    }
}
