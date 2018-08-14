package nexters.com.dear.util;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.ArrayList;

public class DateCountDownTimer extends CountDownTimer {
    public final static int TYPE_HHMMSS = 1;
    public final static int TYPE_HHMM = 2;
    public final static int TIME_INTERVAL = 1000;
    private static final String format_HHMMSS = "%02d:%02d:%02d";

    private int type = 1;
    private ArrayList<TextView> textViews = new ArrayList<>();

    public DateCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }
    public void setType(int type){
        this.type = type;
    }
    public void setTxtView(TextView txtView){
        this.textViews.add(txtView);
    }
    @Override
    public void onTick(long millisUntilFinished) {
        long diffSeconds = millisUntilFinished/1000 % 60;
        long diffMinutes = millisUntilFinished/(60 * 1000) % 60;
        long diffHrs = millisUntilFinished / (60 * 60 * 1000) % 24;
        if (type == TYPE_HHMMSS){
            textViews.get(0).setText(String.format(format_HHMMSS, diffHrs, diffMinutes, diffSeconds));
        }
    }

    @Override
    public void onFinish() {

    }
}
