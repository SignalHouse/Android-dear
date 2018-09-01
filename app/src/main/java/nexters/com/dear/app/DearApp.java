package nexters.com.dear.app;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import java.net.URISyntaxException;

import nexters.com.dear.util.Util;

public class DearApp extends Application {
    private static DearApp instance;
    private static int SEND_HR = 22;
    private static int SEND_MIN = 0;
    private static int SEND_SEC = 0;
    private static String token;

    public static DearApp getAppInstance(){return instance;}
    public int getSEND_HR() {
        return SEND_HR;
    }

    public int getSEND_SEC() {
        return SEND_SEC;
    }

    public int getSEND_MIN() {
        return SEND_MIN;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        DearApp.token = token;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
