package REST;

import java.util.ArrayList;

import Models.Data;
import Models.ForureningsDataModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DataService {
    //http://10.28.0.241:3000/lpdv2k12_kbh_no2?select=id&lat=eq.55.6576197384&long=eq.12.5579398109
    @GET
    Call<ArrayList<Data>> SearchForLocation(@Url String url);

    @GET
    Call<ArrayList<Data>> GetAddresses(@Url String url);

    @GET
    Call<ArrayList<ForureningsDataModel>> GetForureningsData(@Url String url);
}
