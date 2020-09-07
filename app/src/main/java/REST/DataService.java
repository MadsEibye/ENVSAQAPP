package REST;

import Models.Data;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataService {
    //http://10.28.0.241:3000/lpdv2k12_kbh_no2?select=id&lat=eq.55.6576197384&long=eq.12.5579398109
    @GET("lpdv2k12")
    Call<Data> SearchForLocation(@Query("query") String query);

}
