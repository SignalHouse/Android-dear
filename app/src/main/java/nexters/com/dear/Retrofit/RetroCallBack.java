package nexters.com.dear.Retrofit;

public interface RetroCallBack<T> {
    void onError(Throwable t);
    void onSuccess(int code, T receivedData);
    void onFailure(int code);
}
