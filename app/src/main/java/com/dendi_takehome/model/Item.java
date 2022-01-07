package com.dendi_takehome.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Item implements Parcelable {
    @SerializedName("id")
    private Integer item_id;
    @SerializedName("login")
    private String username;
    @SerializedName("avatar_url")
    private String avatar_url;


    protected Item(Parcel in) {
        item_id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        username = ((String) in.readValue((String.class.getClassLoader())));
        avatar_url = ((String) in.readValue((String.class.getClassLoader())));
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer id) {
        this.item_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(item_id);
        dest.writeValue(username);
        dest.writeValue(avatar_url);
    }

}