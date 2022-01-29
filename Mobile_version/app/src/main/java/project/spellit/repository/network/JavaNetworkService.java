package project.spellit.repository.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static project.spellit.activities.MainActivityKt.SERVER_URL;

public class JavaNetworkService {
    private static JavaNetworkService mInstance;
    private static final String BASE_URL = SERVER_URL;
    private Retrofit mRetrofit;

    private JavaNetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static JavaNetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new JavaNetworkService();
        }
        return mInstance;
    }

    public JsonPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JsonPlaceHolderApi.class);
    }
}
