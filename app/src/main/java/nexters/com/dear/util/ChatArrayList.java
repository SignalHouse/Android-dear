package nexters.com.dear.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import nexters.com.dear.model.ChatMessage;

public class ChatArrayList extends ArrayList<ChatMessage> {
    //automatically add date divider
    @Override
    public boolean add(ChatMessage chatMessage) {
        if (size() == 0){
            ChatMessage message = new ChatMessage();
            message.setDateDivider(true);
            message.setDate(chatMessage.getDate());
            super.add(message);
        }
        else{
            int curIdx = size() - 1;
            int prevDay = TimeConversion.getDayOfMonth(get(curIdx).getDate());
            int curDay = TimeConversion.getDayOfMonth(chatMessage.getDate());

            if (prevDay != curDay){ //not on same day ==> diff day
                ChatMessage message = new ChatMessage();
                message.setDate(chatMessage.getDate());
                message.setDateDivider(true);
                super.add(message);
            }

        }

        return super.add(chatMessage);
    }
}
