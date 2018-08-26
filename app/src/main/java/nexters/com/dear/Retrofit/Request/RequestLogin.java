package nexters.com.dear.Retrofit.Request;

import java.util.HashMap;

public class RequestLogin {
    public final String email;
    public final String password;

    public RequestLogin(HashMap<String, Object> parameters){
        this.email = (String) parameters.get("email");
        this.password = (String) parameters.get("password");
    }
}
