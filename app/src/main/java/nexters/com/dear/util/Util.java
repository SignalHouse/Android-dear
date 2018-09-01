package nexters.com.dear.util;

import android.util.Patterns;

import java.util.regex.Pattern;

import nexters.com.dear.Retrofit.RetroApiService;

public class Util {
    public static final String CHAT_SERVER_URL = "http://192.168.1.33:8080/";

    public static boolean validEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
