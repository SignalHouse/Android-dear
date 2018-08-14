package nexters.com.dear.util;

import java.util.Calendar;
import java.util.Date;

public class TimeConversion {
    public static int getDayOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal.get(Calendar.DAY_OF_MONTH);
    }

}
