package nexters.com.dear.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import nexters.com.dear.R;
import nexters.com.dear.adapter.ChatMessageAdapter;
import nexters.com.dear.model.ChatMessage;
import nexters.com.dear.util.ChatArrayList;

public class ChatRoomActivity extends AppCompatActivity {
    @BindView(R.id.chat_recycler_view)
    RecyclerView mRecyclerView;

    ChatArrayList chatMessages;
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
        chatMessages = new ChatArrayList();
        int tempMon, tempDay;
        tempMon = 7; tempDay = 1;
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
    }
}
