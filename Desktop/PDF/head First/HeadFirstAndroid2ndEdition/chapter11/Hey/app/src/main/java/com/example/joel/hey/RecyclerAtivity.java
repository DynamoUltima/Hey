package com.example.joel.hey;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAtivity extends AppCompatActivity {

    TextView Disconnected;
    ProgressDialog pd;
    private RecyclerView recyclerView;
    private Item item;
    private SwipeRefreshLayout swipeContainer;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);

//        loadJSON();

        initViews();

        swipeContainer = findViewById(R.id.swipeContainer);

        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON();
                Toast.makeText(RecyclerAtivity.this, "Forhey Clients Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Clients...");
        pd.setCancelable(false);
        pd.show();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();
    }

    private void loadJSON() {

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.joel.basicauthretrofit", MODE_PRIVATE);
        int idNo = sharedPreferences.getInt("personelID", 0);


        Disconnected = findViewById(R.id.disconnected);
        try {
            Client Client = new Client();
            Service apiService = com.example.joel.hey.Client.getClient().create(Service.class);
            Call<ItemResponse> call = apiService.getItems(idNo);
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    List<Item> items = response.body().getItems();
                    Collections.reverse(items);
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(RecyclerAtivity.this, t.getMessage() + "Error sending Data!", Toast.LENGTH_LONG).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();

                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_action) {
            startActivity(new Intent(RecyclerAtivity.this, SignedClientActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }
}
