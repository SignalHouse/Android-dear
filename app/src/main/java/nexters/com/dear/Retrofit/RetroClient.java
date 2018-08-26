package nexters.com.dear.Retrofit;

import android.content.Context;

import java.util.HashMap;

import nexters.com.dear.Retrofit.Request.RequestLogin;
import nexters.com.dear.Retrofit.Response.ResponseToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    private RetroApiService apiService;
    public static String baseUrl = RetroApiService.BAES_URL;
    private static Context mContext;
    private static Retrofit retrofit;

    private static class SingletonHolder{
        private static RetroClient INSTANCE = new RetroClient(mContext);
    }

    public static RetroClient getInstance(Context context){
        if (context != null)
            mContext = context;

        return  SingletonHolder.INSTANCE;
    }

    private RetroClient(Context context){
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl).build();
    }

    public RetroClient createBaseApi(){
        apiService = create(RetroApiService.class);
        return this;
    }

    public <T> T create(final Class<T> service){
        if (service == null)
            throw new RuntimeException("API service null");
        return  retrofit.create(service);
    }


    public void postLogin(HashMap<String, Object> parameters, final RetroCallBack callback){
        apiService.postLogin(parameters).enqueue(new Callback<ResponseToken>() {
            @Override
            public void onResponse(Call<ResponseToken> call, Response<ResponseToken> response) {
                if (response.isSuccessful()){
                    callback.onSuccess(response.code(), response.body());
                }
                else{
                    callback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseToken> call, Throwable t) {
                    callback.onError(t);
            }
        });
    }
}
