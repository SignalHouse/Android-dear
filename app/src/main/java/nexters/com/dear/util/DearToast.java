package nexters.com.dear.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import nexters.com.dear.R;

public class DearToast extends Toast {

    TextView txtMessage;
    String message;
    public DearToast(Context context, String message) {
        super(context);
        this.message = message;
        View view = LayoutInflater.from(context).inflate(R.layout.dear_toast, null);
        setView(view);

        setGravity(Gravity.BOTTOM, 0, 0);
        setDuration(Toast.LENGTH_LONG);
        txtMessage = view.findViewById(R.id.toast_message);
        txtMessage.setText(message);
    }

    public static DearToast makeText(Context context, String message){
        DearToast result = new DearToast(context, message);
        return  result;
    }
}
