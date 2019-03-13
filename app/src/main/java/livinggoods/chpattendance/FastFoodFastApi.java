package livinggoods.chpattendance;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FastFoodFastApi {

    @GET("orders")
    Call<List<Post>> getOrders();
}
