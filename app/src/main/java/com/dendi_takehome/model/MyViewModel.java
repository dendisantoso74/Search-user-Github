package com.dendi_takehome.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dendi_takehome.api.ApiClientInstance;
import com.dendi_takehome.api.ApiService;
import com.dendi_takehome.response.WrappedListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyViewModel extends androidx.lifecycle.ViewModel {
    private MutableLiveData<List<Item>> itemList = new MutableLiveData<>(); //for items

    //get items data
    public LiveData<List<Item>> getAllUsers() {
        return itemList;
    }

    //search user
    public void setAllUsers(String username) {
        ApiService apiService = ApiClientInstance.getNetwork().create(ApiService.class);

        Call<WrappedListResponse<Item>> call = apiService.getItemList("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", 100, 1, username);
        call.enqueue(new Callback<WrappedListResponse<Item>>() {
            @Override
            public void onResponse(Call<WrappedListResponse<Item>> call, Response<WrappedListResponse<Item>> response) {
                WrappedListResponse<Item> items;

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        items = response.body();
                        itemList.postValue(items.getItems());
                    }
                }

            }

            @Override
            public void onFailure(Call<WrappedListResponse<Item>> call, Throwable t) {
                Log.e("TAG", "gagal" + t);
            }
        });
    }




}