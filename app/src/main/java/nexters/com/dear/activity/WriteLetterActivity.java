package nexters.com.dear.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
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
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nexters.com.dear.R;
import nexters.com.dear.app.DearApp;
import nexters.com.dear.util.DateCountDownTimer;
import nexters.com.dear.widget.LinedEditText;

public class WriteLetterActivity extends AppCompatActivity {
    @BindView(R.id.writeletter_tool_bar)
    Toolbar toolbar;
    @BindView(R.id.tool_bar_base_go_back)
    ImageView btnGoBack;
    @BindView(R.id.tool_bar_base_btn)
    ImageView btnBase;
    @BindView(R.id.tool_bar_base_title)
    TextView txtTitle;
    @BindView(R.id.tool_bar_base_txt)
    TextView txtBase;
    @BindView(R.id.layout_write_fixed_edit_title)
    TextView txtLetterTitle;
    @BindView(R.id.layout_write_fixed_edit_contents)
    LinedEditText txtLetterContents;
    @BindView(R.id.writeletter_txt_remaining_time)
    TextView txtTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_letter);

        ButterKnife.bind(this);
        setToolbar();
        setTimer();
        sendMessage send = new sendMessage();
//        send.execute(getString(R.string.message_server_url) + "/api/message?access_token=" + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MiwiZW1haWwiOiJhYmNAYWJjIiwibmFtZSI6Iu2VmOuKmOyDiSDtmLjrnpHsnbQiLCJpYXQiOjE1MzUxNzA5MjUsImV4cCI6MTUzNTM0MzcyNX0.FZr_hL1tWwVsltfpIKhWxwDsuSn-Sgk1C2h3zq3MDUY");
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        txtTitle.setText("");
        txtBase.setText("완료");
        btnBase.setVisibility(View.INVISIBLE);
        txtBase.setVisibility(View.VISIBLE);

        txtLetterContents.setFocusable(true);
        txtLetterTitle.setFocusable(true);
    }

    @OnClick({R.id.tool_bar_base_go_back, R.id.writeletter_dscrp_waiting, R.id.writeletter_img_left_arrow})
    void onGoBackClicked(){
        finish();
    }

    @OnClick(R.id.tool_bar_base_txt)
    void onBaseTxtClicked(){
        //save
    }

    @OnClick({R.id.layout_write_fixed_edit_contents, R.id.layout_write_fixed_edit_title})
    void onWriteLetterClicked(){
        txtBase.setVisibility(View.VISIBLE);
    }
    private void setTimer(){
        Date curTime = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, DearApp.getAppInstance().getSEND_HR());
        cal.set(Calendar.MINUTE, DearApp.getAppInstance().getSEND_MIN());
        cal.set(Calendar.SECOND, DearApp.getAppInstance().getSEND_SEC());

        if (!curTime.before(cal.getTime())){ //if before the time
            cal.add(Calendar.DATE, 1);
        }
        DateCountDownTimer timer = new DateCountDownTimer(cal.getTimeInMillis() - curTime.getTime(), DateCountDownTimer.TIME_INTERVAL);
        timer.setType(DateCountDownTimer.TYPE_HHMMSS);
        timer.setTxtView(txtTimer);
        timer.start();
    }

    class sendMessage extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(WriteLetterActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("로그인 중...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {
            Calendar cal = Calendar.getInstance();

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

                jsonObject.accumulate("description", "hello");

//                jsonObject.accumulate("email",email);
//                jsonObject.accumulate("password",password);
                HttpURLConnection con = null;
                BufferedReader reader = null;

                try{

                    URL url = new URL(urls[0]);

                    //연결

                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");//POST방식으로 보냄
                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                    con.setRequestProperty("Content-Type", "application/json");//applicati on JSON 형식으로 전송
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
//                    setToken(buffer.toString());
//                    isOK = true;
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

        }
    }

}
