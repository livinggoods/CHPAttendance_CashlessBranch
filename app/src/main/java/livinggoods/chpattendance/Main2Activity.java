package livinggoods.chpattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {
    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewResult = (TextView)findViewById(R.id.textview);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fast-food-fast-restaurant.herokuapp.com/fast-food-fast/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FastFoodFastApi fastFoodFastApi = retrofit.create(FastFoodFastApi.class);

        Call<List<Post>> call = fastFoodFastApi.getOrders();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("code:"+ response.code());
                    return;
                }

                List<Post> orders = response.body();

                for(Post order : orders){
                    String content ="";
                    content += "ID: "+ order.getId() + "\n";
                    content += "Price: "+ order.getPrice() + "\n";
                    content += "Dish : " + order.getDish() + "\n\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });


    }
}
