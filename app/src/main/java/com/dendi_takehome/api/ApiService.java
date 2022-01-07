package com.dendi_takehome.api;

import com.dendi_takehome.model.Item;
import com.dendi_takehome.response.WrappedListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/search/users")
    Call<WrappedListResponse<Item>> getItemList(@Header("Authorization") String token,
                                                @Query("per_page") int perPage,
                                                @Query("page") int page,
                                                @Query("q") String username);
}
