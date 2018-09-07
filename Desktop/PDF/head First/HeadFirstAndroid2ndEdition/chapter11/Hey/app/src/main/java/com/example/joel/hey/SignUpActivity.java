package com.example.joel.hey;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private static int id;
    SharedPreferences sp;
    private String personelName;
    private String personelEmail;


    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(" https://dev.forhey.com/sales/api/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);
    private TextInputLayout emailField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        emailField = findViewById(R.id.text_input_email);
        passwordField = findViewById(R.id.text_input_password);
        findViewById(R.id.createAccounts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.signBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();


            }
        });

        sp = getSharedPreferences("com.example.joel.basicauthretrofit", Context.MODE_PRIVATE);
    }

    private void signup() {

        final String email = emailField.getEditText().getText().toString();
        final String password = passwordField.getEditText().getText().toString();

        try {


            SignUp signup = new SignUp(email, password);
            Call<com.example.joel.hey.Response> call = userClient.signup(signup);

            call.enqueue(new Callback<com.example.joel.hey.Response>() {
                @Override
                public void onResponse(Call<com.example.joel.hey.Response> call, Response<com.example.joel.hey.Response> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(SignUpActivity.this, RecyclerAtivity.class);
                        startActivity(intent);
                        Toast.makeText(SignUpActivity.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                        // token = response.body().getToken();
                        id = response.body().getUser().getId();
                        personelName = response.body().getUser().getName();
                        personelEmail = response.body().getUser().getEmail();


                        /**Storing Client Name and email for future use */
                        /** store response in shared preferences or use intent to display in another activity */

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("personelID", id);
                        editor.putString("personelName", personelName);
                        editor.putString("personelEmail", personelEmail);
                        editor.commit();


                    } else {
                        Toast.makeText(SignUpActivity.this, "login not correct", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<com.example.joel.hey.Response> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, "error:(", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
