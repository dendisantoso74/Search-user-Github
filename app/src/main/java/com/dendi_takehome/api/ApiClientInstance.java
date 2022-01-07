package com.dendi_takehome.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.github.com";
    private static final String API_KEY = "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";

    public static Retrofit getNetwork() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}