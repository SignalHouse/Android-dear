package nexters.com.dear.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nexters.com.dear.R;
import nexters.com.dear.util.DearDialog;
import nexters.com.dear.util.DearDialogListener;
import nexters.com.dear.util.DearToast;
import nexters.com.dear.util.MailSender;

public class LostActivity extends AppCompatActivity implements DearDialogListener{
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
    @BindView(R.id.lost_btn_send)
    Button btnSend;
    @BindView(R.id.lost_edit_email)
    TextView txtEmail;

    private  ProgressDialog dialog;
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

    @OnClick(R.id.lost_btn_send)
    void onSendClicked(){
        if (txtEmail.getText().toString().equals("") || !txtEmail.getText().toString().contains("@")){
            DearDialog dialog = new DearDialog(this, this, "이메일을 확인해 주세요.", DearDialog.DEARDIALOG_SINGLE_BUTTON);
            dialog.show();
        }
        else {

            dialog = new ProgressDialog(this);
            this.dialog.setMessage("이메일 전송중...");
            dialog.show();
            SendPWAsyncTask send = new SendPWAsyncTask("kuk941025@gmail.com", "1234", "kuk941025@gmail.com");
            send.execute();
        }
    }

    @Override
    public void OKListener() {

    }

    @Override
    public void CancelListener() {

    }

    class SendPWAsyncTask extends AsyncTask<Void, Void, Void>{
        String user, password, recipient;
        MailSender sender;
        boolean error;
        public SendPWAsyncTask(String user, String password, String recipient) {
            this.user = user;
            this.password = password;
            this.recipient = recipient;
            error = false;
        }

        @Override
        protected void onPreExecute() {
            sender = new MailSender(user, password);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                sender.sendMail("Test Title", "Test Body", user, recipient);
            }catch (Exception e){
                e.printStackTrace();
                error = true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
            if (error)
                DearToast.makeText(getApplicationContext(), "Error").show();
            else
                DearToast.makeText(getApplicationContext(), "Success").show();
        }
    }
}
