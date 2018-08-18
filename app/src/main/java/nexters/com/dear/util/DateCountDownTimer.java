package nexters.com.dear.util;

import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.function.Function;

import javax.security.auth.callback.Callback;

public class DateCountDownTimer extends CountDownTimer {
    public final static int TYPE_HHMMSS = 1;
    public final static int TYPE_HHMM = 2;
    public final static int TIME_INTERVAL = 1000;

    private static final String format_HHMMSS = "남은시간 %02d:%02d:%02d";
    private static final String format_HHMM = "%02d";

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
        long diffMinutes = millisUntilFinished/(60 * 1000) % 60;
        long diffHrs = millisUntilFinished / (60 * 60 * 1000) % 24;

        if (type == TYPE_HHMMSS){
            long diffSeconds = millisUntilFinished/1000 % 60;
            textViews.get(0).setText(String.format(format_HHMMSS, diffHrs, diffMinutes, diffSeconds));
        }
        else if (type == TYPE_HHMM){
            textViews.get(0).setText(String.format(format_HHMM, diffHrs));
            textViews.get(1).setText(String.format(format_HHMM, diffMinutes));
        }
    }


    @Override
    public void onFinish() {
        DateCountDownTimer reTimer = new DateCountDownTimer(1000 * 60 * 60 * 24, DateCountDownTimer.TIME_INTERVAL); //24hrs
        reTimer.setType(this.type);
        for (TextView txtView : textViews)
            reTimer.setTxtView(txtView);
        reTimer.start();
    }
}
