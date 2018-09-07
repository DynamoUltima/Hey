package com.example.joel.hey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignedClientActivity extends AppCompatActivity {


    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://dev.forhey.com/sales/api/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    UserClient userClient = retrofit.create(UserClient.class);
    private EditText clientField, emailField, locationField, phoneField, streetField, houseField, gpsField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_client);

        clientField = findViewById(R.id.clientField);
        emailField = findViewById(R.id.emailField);
        locationField = findViewById(R.id.locationField);
        phoneField = findViewById(R.id.phoneField);
        streetField = findViewById(R.id.street);
        houseField = findViewById(R.id.house);
        gpsField = findViewById(R.id.gps_coordinates);

        findViewById(R.id.signUpClientbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signedClient();
                Intent intent = new Intent(SignedClientActivity.this, RecyclerAtivity.class);
                startActivity(intent);
                finish();

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Details Activity");

    }


    private void signedClient() {

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.joel.basicauthretrofit", MODE_PRIVATE);
        int idNo = sharedPreferences.getInt("personelID", 0);

        final String clientName = clientField.getText().toString();
        final String clientEmail = emailField.getText().toString();
        final String clientLocation = locationField.getText().toString();
        final String clientPhone = phoneField.getText().toString();
        final String clientStreet = streetField.getText().toString();
        final String clientHouse = houseField.getText().toString();
        final String clientGps = gpsField.getText().toString();

        SignedClient signedClient = new SignedClient(idNo,
                clientName,
                clientEmail,
                clientLocation,
                clientPhone,
                clientStreet,
                clientHouse,
                "2.74,5.90");

        Call<SignedClient> call = userClient.signedClient(signedClient);

        call.enqueue(new Callback<SignedClient>() {
            @Override
            public void onResponse(Call<SignedClient> call, Response<SignedClient> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignedClientActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(SignedClientActivity.this, "Error Signing Client", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignedClient> call, Throwable t) {
                Toast.makeText(SignedClientActivity.this, "Error:( \n Unableto SignUp Client", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
