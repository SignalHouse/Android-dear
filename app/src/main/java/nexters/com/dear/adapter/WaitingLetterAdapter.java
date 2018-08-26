package nexters.com.dear.adapter;

import android.content.Context;
import android.content.res.Resources;
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
import butterknife.OnClick;
import nexters.com.dear.R;
import nexters.com.dear.app.DearApp;
import nexters.com.dear.model.LetterItem;
import nexters.com.dear.util.DearDialog;
import nexters.com.dear.util.DearDialogListener;
import nexters.com.dear.util.DearToast;
import nexters.com.dear.util.TimeUtil;

public class WaitingLetterAdapter extends RecyclerView.Adapter<WaitingLetterAdapter.waitingViewHolder> {
    ArrayList<LetterItem> letterItems;
    Context mContext;
    public WaitingLetterAdapter(Context context, ArrayList<LetterItem> letterItems) {
        this.letterItems = letterItems;
        this.mContext = context;
    }

    @NonNull
    @Override
    public waitingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_waiting_message_item, parent, false);
        return new waitingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull waitingViewHolder holder, int position) {

        holder.txtTime.setText(TimeUtil.getDateInString(letterItems.get(position).getDate(), "HH:mm"));
        holder.txtTitle.setText(letterItems.get(position).getTitle());
        RecyclerView.LayoutParams curParams = (RecyclerView.LayoutParams) holder.viewLayout.getLayoutParams();
        if (letterItems.get(position).isWidthMatchParent()){
            curParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            holder.viewLayout.setLayoutParams(curParams);
        }
        else{
            curParams.rightMargin = 20;
            holder.viewLayout.setLayoutParams(curParams);
        }
    }

    @Override
    public int getItemCount() {
        return letterItems.size();
    }

    public static int pxToDp(int px){
        return (int) (px/ Resources.getSystem().getDisplayMetrics().density);
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
        @BindView(R.id.layout_waiting_message_item)
        View viewLayout;
        public waitingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
        @OnClick(R.id.template_waiting_btn_delete)
        void onDeleteClicked(){
            DearDialog dialog = new DearDialog(mContext, new DearDialogListener() {
                @Override
                public void OKListener() {
                    letterItems.remove(getLayoutPosition());
                    notifyItemRemoved(getLayoutPosition());
                    DearToast.makeText(mContext, "편지가 삭제됐습니다.").show();
                }

                @Override
                public void CancelListener() {

                }
            }, "정말 삭제하시겠어요?", DearDialog.DEARDIALOG_DOUBLE_BUTTON);
            dialog.show();
        }
        @OnClick(R.id.template_waiting_btn_edit)
        void onEditClicked(){
            DearToast.makeText(DearApp.getAppInstance().getApplicationContext(), "Edit clicked").show();
        }
    }
}
