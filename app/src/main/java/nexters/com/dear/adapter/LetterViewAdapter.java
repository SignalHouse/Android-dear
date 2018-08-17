package nexters.com.dear.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import nexters.com.dear.R;
import nexters.com.dear.model.LetterItem;

public class LetterViewAdapter extends RecyclerView.Adapter<LetterViewAdapter.letterViewHolder>{
    private ArrayList<LetterItem> letterItems;

    public LetterViewAdapter(ArrayList<LetterItem> letterItems) {
        this.letterItems = letterItems;
    }

    @NonNull
    @Override
    public letterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_letter_item, parent, false);
        return new letterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull letterViewHolder holder, int position) {
        holder.txtSender.setText(letterItems.get(position).getSender());
        holder.txtTitle.setText(letterItems.get(position).getTitle());
        if (letterItems.get(position).isNew()) {
            holder.openedLetter.setVisibility(View.VISIBLE);
            holder.notopenedLetter.setVisibility(View.GONE);
        }
        else {
            holder.notopenedLetter.setVisibility(View.VISIBLE);
            holder.openedLetter.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return letterItems.size();
    }

    class letterViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.template_letter_title)
        TextView txtTitle;
        @BindView(R.id.template_letter_stamp)
        ImageView imgStamp;
        @BindView(R.id.template_letter_dcrp_recent)
        TextView txtSender;
        @BindView(R.id.template_opened_letter)
        View openedLetter;
        @BindView(R.id.template_not_openend_letter)
        View notopenedLetter;

        public letterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
