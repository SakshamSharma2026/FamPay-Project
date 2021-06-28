package com.codewithshadow.fampay.activites;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.codewithshadow.fampay.R;
import com.codewithshadow.fampay.adapter.ParentAdapter;
import com.codewithshadow.fampay.models.CardModel;
import com.codewithshadow.fampay.network.FamPayApi;
import com.todkars.shimmer.ShimmerRecyclerView;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiScreenActivity extends AppCompatActivity {

    private final static String url = "https://run.mocky.io/v3/";
    ShimmerRecyclerView recyclerView;
    LinearLayoutManager manager;
    SwipeRefreshLayout swipeRefreshLayout;
    List<CardModel> HC1List;
    List<CardModel> HC3List;
    List<CardModel> HC5List;
    List<CardModel> HC6List;
    List<CardModel> HC9List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_screen);

        //RecyclerView
        recyclerView = findViewById(R.id.recyclerView_HS3);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        recyclerView.showShimmer();  // to start showing shimmer


        //All List
        HC1List = new ArrayList<>();
        HC3List = new ArrayList<>();
        HC5List = new ArrayList<>();
        HC6List = new ArrayList<>();
        HC9List = new ArrayList<>();


        //Set Manager
        manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        manager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(manager);

        //Function Call
        callApi();


        //SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(() -> {
            recyclerView.showShimmer();     // to start showing shimmer
            callApi();
            new Handler().postDelayed(() -> swipeRefreshLayout.setRefreshing(false), 2000);
        });

    }



    private void callApi() {

        //Retrofit class
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //FamPay Api
        FamPayApi api = retrofit.create(FamPayApi.class);
        Call<ResponseBody> bodyCall = api.getModel();

        //Call response body
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                if (response.code() == 200) {
                    //Clear list
                    HC1List.clear();
                    HC3List.clear();
                    HC5List.clear();
                    HC6List.clear();
                    HC9List.clear();
                    String s = null;
                    try {
                        assert response.body() != null;
                        s = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        recyclerView.hideShimmer(); // to hide shimmer
                        assert s != null;
                        JSONObject jsonObject = new JSONObject(s);

                        //Card_Groups Array
                        JSONArray cardGroupsArray = jsonObject.getJSONArray("card_groups");

                        //Get All CardGroups Design
                        for (int i = 0; i < cardGroupsArray.length(); i++) {
                            JSONObject object = cardGroupsArray.getJSONObject(i);
                            switch (object.getString("design_type")) {
                                case "HC1":
                                    HC1List.addAll(fetchData(object)) ;
                                    break;
                                case "HC3":
                                    HC3List.addAll(fetchData(object)) ;
                                    break;
                                case "HC5":
                                    HC5List.addAll(fetchData(object)) ;
                                    break;
                                case "HC6":
                                    HC6List.addAll(fetchData(object)) ;
                                    break;
                                case "HC9":
                                    HC9List.addAll(fetchData(object)) ;
                                    break;
                            }

                        }

                        //Add the data in the list
                        List<List<CardModel>> listList = new ArrayList<>();
                        listList.add(HC3List);
                        listList.add(HC6List);
                        listList.add(HC5List);
                        listList.add(HC9List);
                        listList.add(HC1List);


                        //Set the Parent Adapter
                        ParentAdapter adapter = new ParentAdapter(ApiScreenActivity.this, listList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();


                        //Close responseBody
                        response.body().close();


                    } catch (JSONException e) {
                        e.printStackTrace();
                        response.body().close();
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                Log.d("failure", "fail  " + t.getMessage());
            }
        });
    }

    private List<CardModel> fetchData(JSONObject object) throws JSONException {


        List<CardModel> cardModelList = new ArrayList<>();

        //Cards Array
        JSONArray cardsArray = object.getJSONArray("cards");

        for (int j = 0; j < cardsArray.length(); j++) {

            //CardModel model
            CardModel cardModel = new CardModel();
            cardModel.setDesign_type(object.getString("design_type"));
            cardModel.setIs_scrollable(object.getBoolean("is_scrollable"));

            JSONObject object2 = cardsArray.getJSONObject(j);
            cardModel.setName(object2.optString("name"));
            cardModel.setUrl(object2.optString("url"));
            cardModel.setTitle(object2.optString("title"));
            cardModel.setBg_color(object2.optString("bg_color"));


            //Check if the formatted_title is null or not
            if (object2.optJSONObject("formatted_title") != null) {
                JSONArray jsonArray4 = Objects.requireNonNull(object2.optJSONObject("formatted_title")).optJSONArray("entities");
                cardModel.setFormatted_title(Objects.requireNonNull(object2.optJSONObject("formatted_title")).optString("text"));
                if (jsonArray4 != null) {
                    List<CardModel.EntitiesModel> entitiesModel = new ArrayList<>();
                    for (int k = 0; k < jsonArray4.length(); k++) {
                        CardModel.EntitiesModel entitiesModel1 = new CardModel.EntitiesModel();
                        JSONObject object4 = jsonArray4.optJSONObject(k);
                        entitiesModel1.setText(object4.optString("text"));
                        entitiesModel1.setColor(object4.optString("color"));
                        entitiesModel.add(entitiesModel1);
                    }
                    cardModel.setEntitiesModel(entitiesModel);
                }
            }



            //Check if the formatted_description is null or not
            if (object2.optJSONObject("formatted_description") != null) {
                cardModel.setFormatted_description(Objects.requireNonNull(object2.optJSONObject("formatted_description")).optString("text"));
            }


            //Handle the bg_image exception
            try {
                cardModel.setBg_image(Objects.requireNonNull(object2.optJSONObject("bg_image")).optString("image_url"));
            } catch (Exception e) {
                e.printStackTrace();
            }


            JSONObject object3 = object2.optJSONObject("icon");
            if (object3 != null) {
                cardModel.setIcon(object3.optString("image_url"));
            }


            //Cta Array
            JSONArray jsonArray2 = object2.optJSONArray("cta");
            if (jsonArray2 != null) {
                for (int k = 0; k < jsonArray2.length(); k++) {
                    CardModel.CtaModel ctaModel = new CardModel.CtaModel();
                    JSONObject object4 = jsonArray2.optJSONObject(k);
                    ctaModel.setText_color(object4.optString("text_color"));
                    ctaModel.setText(object4.optString("text"));
                    ctaModel.setBg_color(object4.optString("bg_color"));
                    ctaModel.setUrl(object4.optString("url"));
                    cardModel.setModel(ctaModel);
                }
            }
            //Add all the data in CardModelList
            cardModelList.add(cardModel);

        }

        //Return the list
        return  cardModelList;
    }
}
