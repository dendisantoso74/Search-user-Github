package com.dendi_takehome.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dendi_takehome.R;
import com.dendi_takehome.adapter.UsersAdapter;
import com.dendi_takehome.model.Item;
import com.dendi_takehome.model.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyViewModel myViewModel;
    private UsersAdapter mAdapter;
    private ArrayList<Item> itemArray = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        mAdapter = new UsersAdapter(getApplicationContext(), itemArray);

        RecyclerView recyclerView = findViewById(R.id.rv_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        myViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);
        setSearchView();
        progressBar.setVisibility(View.GONE);
        setMyViewModelObserver();
//
    }

    private void setMyViewModelObserver() {

        myViewModel.getAllUsers().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(final List<Item> items) {

                if (items.isEmpty()) {
                    isNotFound();
                } else {
                    mAdapter.setUserData(items);
                }
            }
        });
    }

    //Search User
    private void setSearchView() {
        final SearchView searchView = findViewById(R.id.search_user);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                myViewModel.setAllUsers(query);
                Toast.makeText(getApplicationContext(), "Searching...", Toast.LENGTH_SHORT).show();

                Log.d("Submit", "DATA" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    //user not found
    private void isNotFound() {
        Toast.makeText(getApplicationContext(), "User Not Found", Toast.LENGTH_LONG).show();
    }
}
