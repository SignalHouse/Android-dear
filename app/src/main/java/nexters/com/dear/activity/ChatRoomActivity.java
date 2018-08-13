package nexters.com.dear.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import nexters.com.dear.R;
import nexters.com.dear.adapter.ChatMessageAdapter;
import nexters.com.dear.model.ChatMessage;

public class ChatRoomActivity extends AppCompatActivity {
    @BindView(R.id.chat_recycler_view)
    RecyclerView mRecyclerView;

    ArrayList<ChatMessage> chatMessages;
    private ChatMessageAdapter chatMessageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        ButterKnife.bind(this);

        setChatView();
    }

    private void setChatView(){
        Date curTime = Calendar.getInstance().getTime();
        chatMessages = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            ChatMessage message = new ChatMessage("Name " + i, "adasdsadasdsads", curTime);
            message.setDateDivider(false);

            chatMessages.add(message);
        }
        chatMessageAdapter = new ChatMessageAdapter(chatMessages);
        mRecyclerView.setAdapter(chatMessageAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
