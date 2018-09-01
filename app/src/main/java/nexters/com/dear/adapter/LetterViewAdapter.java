package nexters.com.dear.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import nexters.com.dear.R;
import nexters.com.dear.activity.ChatRoomActivity;
import nexters.com.dear.model.LetterItem;

public class LetterViewAdapter extends RecyclerView.Adapter<LetterViewAdapter.letterViewHolder>{
    private ArrayList<LetterItem> letterItems;
    private Context mContext;
    public LetterViewAdapter(Context mContext,ArrayList<LetterItem> letterItems) {
        this.mContext = mContext;
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
        final LetterItem letter = letterItems.get(position);

        holder.txtSender.setText(letterItems.get(position).getSender());
        holder.txtTitle.setText(letterItems.get(position).getTitle());

        if (letterItems.get(position).isCheckVisible())
            holder.letterCheck.animate().alpha(1);
        else
            holder.letterCheck.animate().alpha(0);
        holder.letterCheck.setChecked(letterItems.get(position).isSelected());

        if (letterItems.get(position).isNew()) {
            holder.openedLetter.setVisibility(View.VISIBLE);
            holder.notopenedLetter.setVisibility(View.GONE);
        }
        else {
            holder.notopenedLetter.setVisibility(View.VISIBLE);
            holder.openedLetter.setVisibility(View.GONE);
        }

        holder.letterCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                letter.setSelected(isChecked);
            }
        });
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
        @BindView(R.id.template_letter_check)
        CheckBox letterCheck;

        public letterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick({R.id.template_opened_letter, R.id.template_not_openend_letter})
        void onOpenedClicked(){
            Intent i = new Intent(itemView.getContext(), ChatRoomActivity.class);
            letterItems.get(getLayoutPosition()).setNew(true);
            notifyItemChanged(getLayoutPosition());
            mContext.startActivity(i);
        }

    }
}
