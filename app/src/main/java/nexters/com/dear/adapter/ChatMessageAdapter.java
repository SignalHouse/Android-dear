package nexters.com.dear.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import nexters.com.dear.R;
import nexters.com.dear.model.ChatMessage;

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
        SimpleDateFormat formatter;
        if (chatMessages.get(position).getDateDivider()) { //show date divider
            holder.viewDate.setVisibility(View.VISIBLE);
            holder.viewContents.setVisibility(View.GONE);

            Date divDate = chatMessages.get(position).getDate();
            formatter = new SimpleDateFormat("yyyy MMMM dd");
            String date = formatter.format(divDate);
            holder.txtDateDivder.setText(date);
        }
        else {
            holder.viewContents.setVisibility(View.VISIBLE);
            holder.viewDate.setVisibility(View.GONE);

            holder.txtContents.setText(chatMessages.get(position).getContents());
            holder.txtName.setText(chatMessages.get(position).getName());

            Date sentTime = chatMessages.get(position).getDate();
            formatter = new SimpleDateFormat("hh:mm a");
            String date = formatter.format(sentTime);
            holder.txtTime.setText(date);
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
        TextView txtDateDivder;
        @BindView(R.id.template_message_contents)
        TextView txtContents;
        @BindView(R.id.template_message_time)
        TextView txtTime;
        @BindView(R.id.template_message_name)
        TextView txtName;
        public chatViewHolder (View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
