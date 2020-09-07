package REST;

public class ApiUtils {
    private ApiUtils() {
    }

    private static final String BASE_URL = "http://10.28.0.241:3000/";
    private static final String Auth_URL = "https://accounts.spotify.com/";

    public static DataService getTrackService() {

        return RetrofitClient.getClient(BASE_URL).create(DataService.class);
    }

}
