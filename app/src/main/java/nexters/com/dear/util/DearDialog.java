package nexters.com.dear.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nexters.com.dear.R;

public class DearDialog extends Dialog{
    public final static int DEARDIALOG_SINGLE_BUTTON = 1;
    public final static int DEARDIALOG_DOUBLE_BUTTON = 2;
    @BindView(R.id.dialog_layout_single_button)
    View layoutSingle;
    @BindView(R.id.dialog_layout_two_buttons)
    View layoutDouble;
    @BindView(R.id.dialog_btn_double_cancel)
    Button btnDCancel;
    @BindView(R.id.dialog_btn_double_ok)
    Button btnDOk;
    @BindView(R.id.dialog_btn_single_ok)
    Button btnSOK;
    @BindView(R.id.dialog_txt)
    TextView txtTitle;


    private String title;
    private int type;
    private DearDialogListener listener;
    public DearDialog(@NonNull Context context, DearDialogListener listener, String title, int type) {
        super(context);
        this.type = type;
        this.title = title;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dear_dialog);
        ButterKnife.bind(this);

        txtTitle.setText(title);
        if (this.type == DEARDIALOG_SINGLE_BUTTON){
            layoutSingle.setVisibility(View.VISIBLE);
            layoutDouble.setVisibility(View.GONE);
        }
        else if (this.type == DEARDIALOG_DOUBLE_BUTTON){
            layoutSingle.setVisibility(View.GONE);
            layoutDouble.setVisibility(View.VISIBLE);

        }
    }

    @OnClick({R.id.dialog_btn_double_ok, R.id.dialog_btn_single_ok})
    void OnOK(){
        listener.OKListener();
        dismiss();
    }

    @OnClick(R.id.dialog_btn_double_cancel)
    void OnCancel(){
        listener.CancelListener();
        dismiss();
    }


}
