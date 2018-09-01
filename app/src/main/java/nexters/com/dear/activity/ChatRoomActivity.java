package nexters.com.dear.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nexters.com.dear.R;
import nexters.com.dear.adapter.ChatMessageAdapter;
import nexters.com.dear.app.DearApp;
import nexters.com.dear.model.ChatMessage;
import nexters.com.dear.util.ChatArrayList;
import nexters.com.dear.util.DearDialog;
import nexters.com.dear.util.DearDialogListener;
import nexters.com.dear.util.Util;

public class ChatRoomActivity extends AppCompatActivity implements DearDialogListener{
    @BindView(R.id.chat_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.chat_tool_bar)
    Toolbar toolbar;
    @BindView(R.id.tool_bar_base_btn)
    ImageView btnBase;
    @BindView(R.id.tool_bar_base_go_back)
    ImageView btnGoback;
    @BindView(R.id.tool_bar_base_title)
    TextView txtTitle;
    @BindView(R.id.tool_bar_base_txt)
    TextView txtComplete;
    @BindView(R.id.chat_drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.drawer_exit)
    TextView btnExit;
    @BindView(R.id.drawer_block)
    TextView btnBlock;
    @BindView(R.id.drawer_report)
    TextView btnReport;
    @BindView(R.id.chat_edit_input)
    EditText editMessage;

    ChatArrayList chatMessages;
    private Socket mSocket;
    {
        try{
            mSocket = IO.socket(Util.CHAT_SERVER_URL);
        }catch (URISyntaxException e){}
    }
    private ChatMessageAdapter chatMessageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        ButterKnife.bind(this);

        setChatView();
        setToolbar();
        DearApp app = (DearApp) getApplication();



        mSocket.connect();
        mSocket.on("message", onNewMessage);
        mSocket.emit("connect", "hi");
    }

    private void setChatView(){
        Date curTime = Calendar.getInstance().getTime();
        chatMessages = new ChatArrayList();
        int tempMon, tempDay;
        tempMon = 1; tempDay = 1;
        for (int i = 0; i < 100; i++){
            Date tempDate = new Date();
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 10);
            cal.set(Calendar.MINUTE, 30);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            cal.set(Calendar.MONTH, tempMon);
            cal.set(Calendar.DAY_OF_MONTH, tempDay++);
            if (tempDay > 30) {
                tempDay = 1; tempMon++;
            }
            tempDate = cal.getTime();

            ChatMessage message = new ChatMessage("Name " + i, "adasdsadasdsads", tempDate);
            message.setDateDivider(false);

            if (i % 2 == 0)
                message.setAlignment(ChatMessage.ALIGNMENT_LEFT);
            else
                message.setAlignment(ChatMessage.ALIGNMENT_RIGHT);

            chatMessages.add(message);
        }
        chatMessageAdapter = new ChatMessageAdapter(chatMessages);
        mRecyclerView.setAdapter(chatMessageAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.scrollToPosition(chatMessages.size() - 1);
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        txtTitle.setText("닉네임");
        txtComplete.setVisibility(View.GONE);
        btnBase.setVisibility(View.VISIBLE);
        btnBase.setImageResource(R.drawable.img_menu);
    }

    @OnClick(R.id.tool_bar_base_go_back)
    void onGoBackClicked(){
        finish();
    }

    @OnClick(R.id.tool_bar_base_btn)
    void onBaseBtnClicked(){
        drawerLayout.openDrawer(Gravity.END);
    }

    @OnClick(R.id.drawer_exit)
    void onExitClick(){
        DearDialog dialog = new DearDialog(this, this, "정말 나가시겠어요?", DearDialog.DEARDIALOG_DOUBLE_BUTTON);
        dialog.show();
    }

    @Override
    public void OKListener() {
        finish();
    }

    @Override
    public void CancelListener() {

    }

    @OnClick({R.id.chat_btn_send, R.id.chat_layout_bottom})
    void onSendClicked(){
        ChatMessage message = new ChatMessage("Name", editMessage.getText().toString(), Calendar.getInstance().getTime());
        message.setDateDivider(false);
        chatMessages.add(message);

        editMessage.setText("");
        chatMessageAdapter.notifyItemInserted(chatMessages.size() - 1);
        mRecyclerView.scrollToPosition(chatMessages.size() - 1);

        mSocket.emit("message", message.getContents());
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    JSONObject data = (JSONObject) args[0];
//                    String message;
//                    try{
//                        message = data.getString("message");
//                    } catch (JSONException e){
//                        return;
//                    }
                    JSONObject data = null;
                    String message = null;
                    try{
                        data = (JSONObject) args[0];
                    } catch(ClassCastException e){
                        e.printStackTrace();
                        message = args[0].toString();
                    }
                    if (message == null){
                        try {
                            message = data.getString("content");
                            ChatMessage msg = new ChatMessage("Listened", message, Calendar.getInstance().getTime());
                            msg.setDateDivider(false);
                            msg.setAlignment(ChatMessage.ALIGNMENT_LEFT);
                            chatMessages.add(msg);
                            chatMessageAdapter.notifyItemInserted(chatMessages.size() - 1);
                            mRecyclerView.scrollToPosition(chatMessages.size() - 1);

                        }catch (JSONException e){return;}
                    }


                    Log.d("Message Listened", message);
                }
            });
        }
    };


}
