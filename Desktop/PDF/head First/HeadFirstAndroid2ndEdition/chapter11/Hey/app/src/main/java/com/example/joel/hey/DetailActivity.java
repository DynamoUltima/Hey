package com.example.joel.hey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class DetailActivity extends AppCompatActivity {
    // ImageView laundry;
    Toolbar mActionBarToolbar;
    private TextView Username, Email, phone_number, location, street_name, house_number, gps_coordinates,Created_at;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Details Activity");

        // laundry = findViewById(R.id.laundry);
        Username = findViewById(R.id.username);
        Email = findViewById(R.id.email);
        phone_number = findViewById(R.id.phone_number);
        location = findViewById(R.id.location);
        street_name = findViewById(R.id.Street_Name);
        house_number = findViewById(R.id.house_number);
        gps_coordinates = findViewById(R.id.gps_coordinates);
        Created_at = findViewById(R.id.created_at);

        String username = getIntent().getExtras().getString("name");
        String email = getIntent().getExtras().getString("email");
        String ClientPhone = getIntent().getExtras().getString("phone");
        String ClientLocation = getIntent().getExtras().getString("location");
        String ClientStreet = getIntent().getExtras().getString("street_name");
        String ClientHouse = getIntent().getExtras().getString("house_number");
        String ClientGps = getIntent().getExtras().getString("gps_coordinates");
        String ClientTime = getIntent().getExtras().getString("created_at");


//        SharedPreferences sharedPreferences = getSharedPreferences("com.example.joel.basicauthretrofit", MODE_PRIVATE);
//        String ClientTime  = sharedPreferences.getString("created_at", "");

//        String dateStringer= getIntent().getExtras().getString("created_at");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
//        Date convertedDate = new Date();
//        try {
//            convertedDate = dateFormat.parse(dateStringer);
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        PrettyTime p  = new PrettyTime();
//        String datetime= p.format(convertedDate);



        Email.setText( email);
        Username.setText(username);
        phone_number.setText(ClientPhone);
        location.setText(ClientLocation);
        street_name.setText( ClientStreet);
        house_number.setText(ClientHouse);
        gps_coordinates.setText( ClientGps);
        Created_at.setText(ClientTime);

        Toast.makeText(this, ClientPhone, Toast.LENGTH_SHORT).show();


    }




}
