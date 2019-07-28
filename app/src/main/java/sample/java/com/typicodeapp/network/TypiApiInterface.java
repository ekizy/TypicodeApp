package sample.java.com.typicodeapp.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface TypiApiInterface {

    @GET
    Call<ResponseBody> getJSONData(@Url String url);

}
