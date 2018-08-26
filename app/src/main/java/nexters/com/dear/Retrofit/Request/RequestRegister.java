package nexters.com.dear.Retrofit.Request;

import java.util.HashMap;

public class RequestRegister {
    public final String name;
    public final String email;
    public final String password;

    public RequestRegister(HashMap<String, Object> parameters){
        this.name = (String) parameters.get("name");
        this.email = (String) parameters.get("email");
        this.password = (String) parameters.get("password");
    }
}
