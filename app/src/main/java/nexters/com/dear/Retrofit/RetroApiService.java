package nexters.com.dear.Retrofit;


import java.util.HashMap;


import nexters.com.dear.Retrofit.Response.ResponseToken;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetroApiService {
    final String BAES_URL =  "http://172.30.1.55";

    @FormUrlEncoded
    @POST("/api/user")
    Call<ResponseToken> postRegister(@FieldMap HashMap<String, Object> parameters);

    @FormUrlEncoded
    @POST("/api/user/login")
    Call<ResponseToken> postLogin(@FieldMap HashMap<String, Object> parameters);

}
