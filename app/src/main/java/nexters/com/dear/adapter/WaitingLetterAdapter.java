package nexters.com.dear.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import nexters.com.dear.R;
import nexters.com.dear.model.LetterItem;

public class WaitingLetterAdapter extends RecyclerView.Adapter<WaitingLetterAdapter.waitingViewHolder> {
    ArrayList<LetterItem> letterItems;

    public WaitingLetterAdapter(ArrayList<LetterItem> letterItems) {
        this.letterItems = letterItems;
    }

    @NonNull
    @Override
    public waitingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_waiting_message_item, parent, false);
        return new waitingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull waitingViewHolder holder, int position) {
        Date writtenTime =  letterItems.get(position).getDate();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String date = formatter.format(writtenTime);

        holder.txtTime.setText(date);
        holder.txtTitle.setText(letterItems.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return letterItems.size();
    }

    class waitingViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.template_waiting_btn_delete)
        ImageView btnDelete;
        @BindView(R.id.template_waiting_btn_edit)
        ImageView btnEdit;
        @BindView(R.id.template_waiting_time)
        TextView txtTime;
        @BindView(R.id.template_waiting_txt_title)
        TextView txtTitle;
        public waitingViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
