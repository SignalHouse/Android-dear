package nexters.com.dear.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import nexters.com.dear.R;
import nexters.com.dear.model.ChatMessage;
import nexters.com.dear.util.TimeUtil;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.chatViewHolder > {
    ArrayList<ChatMessage> chatMessages;

    public ChatMessageAdapter(ArrayList<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public chatViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_message_item, parent, false);
        return new chatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chatViewHolder  holder, int position) {
        if (chatMessages.get(position).getDateDivider()) { //show date divider
            holder.viewDate.setVisibility(View.VISIBLE);
            holder.viewContents.setVisibility(View.GONE);

            holder.txtDateDivider.setText(TimeUtil.getDateInString(chatMessages.get(position).getDate(), "yyyy MMMM dd"));
        }
        else {
            holder.viewContents.setVisibility(View.VISIBLE);
            holder.viewDate.setVisibility(View.GONE);

            holder.txtContents.setText(chatMessages.get(position).getContents());

            if (chatMessages.get(position).getAlignment() == ChatMessage.ALIGNMENT_LEFT){
                holder.txtContents.setGravity(Gravity.LEFT);
                holder.txtTimeLeft.setText(TimeUtil.getDateInString(chatMessages.get(position).getDate(), "hh:mm a"));
                holder.txtSenderLeft.setText(chatMessages.get(position).getName());
                holder.viewLeft.setVisibility(View.VISIBLE);
                holder.viewRight.setVisibility(View.INVISIBLE);
            }
            else if (chatMessages.get(position).getAlignment() == ChatMessage.ALIGNMENT_RIGHT){
                holder.txtContents.setGravity(Gravity.RIGHT);
                holder.txtTimeRight.setText(TimeUtil.getDateInString(chatMessages.get(position).getDate(), "hh:mm a"));
                holder.txtSenderRight.setText(chatMessages.get(position).getName());
                holder.viewRight.setVisibility(View.VISIBLE);
                holder.viewLeft.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    class chatViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.template_message_layout_contents)
        View viewContents;
        @BindView(R.id.template_message_layout_date)
        View viewDate;
        @BindView(R.id.template_message_date)
        TextView txtDateDivider;
        @BindView(R.id.template_message_contents)
        TextView txtContents;
        @BindView(R.id.template_message_sender_left)
        TextView txtSenderLeft;
        @BindView(R.id.template_message_sender_right)
        TextView txtSenderRight;
        @BindView(R.id.template_message_time_left)
        TextView txtTimeLeft;
        @BindView(R.id.template_message_time_right)
        TextView txtTimeRight;
        @BindView(R.id.template_message_layout_left)
        View viewLeft;
        @BindView(R.id.template_message_layout_right)
        View viewRight;

        public chatViewHolder (View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
