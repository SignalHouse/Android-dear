package nexters.com.dear.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nexters.com.dear.R;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.register_tool_bar)
    Toolbar toolbar;
    @BindView(R.id.tool_bar_base_btn)
    ImageView btnBase;
    @BindView(R.id.tool_bar_base_go_back)
    ImageView btnGoback;
    @BindView(R.id.tool_bar_base_title)
    TextView txtTitle;
    @BindView(R.id.tool_bar_base_txt)
    TextView txtComplete;


    EditText inputEmail, inputNickname, inputPassword;
    String email, nickname, password;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        inputEmail = (EditText) findViewById(R.id.register_edit_email);
        inputNickname = (EditText) findViewById(R.id.register_edit_name);
        inputPassword = (EditText) findViewById(R.id.register_edit_pw);

        btnRegister = (Button) findViewById(R.id.register_btn_sign_in);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = inputEmail.getText().toString();
                nickname = inputNickname.getText().toString();
                password = inputPassword.getText().toString();

                new registDB().execute("http://192.168.43.31:8000/api/user");
            }
        });

        setToolbar();
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        txtTitle.setText("Sign Up");
        txtComplete.setVisibility(View.GONE);
        btnBase.setVisibility(View.GONE);
    }

    @OnClick(R.id.tool_bar_base_go_back)
    void onGoBackClicked(){
        finish();
    }

    public class registDB extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {

            try {

                //JSONObject를 만들고 key value 형식으로 값을 저장
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("id","test");
                jsonObject.accumulate("name", nickname);
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
    }
}
