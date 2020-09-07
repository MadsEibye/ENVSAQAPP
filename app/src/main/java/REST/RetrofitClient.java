package REST;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    // https://futurestud.io/tutorials/retrofit-2-adding-customizing-the-gson-converter
                    // Gson is no longer the default converter
                    .build();
        }
        return retrofit;
    }
}
