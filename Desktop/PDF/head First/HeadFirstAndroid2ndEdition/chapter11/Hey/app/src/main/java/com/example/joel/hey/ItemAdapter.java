package com.example.joel.hey;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> items;
    private Context context;
 public class TextCustom extends android.support.v7.widget.AppCompatTextView{
     public TextCustom(Context context, AttributeSet attrs) {
         super(context, attrs);
     }

     @Override
     public void setText(CharSequence text, android.widget.TextView.BufferType type) {
         if (text.length() > 0) {
             text = String.valueOf(text.charAt(0)).toUpperCase() + text.subSequence(1, text.length());
         }
         super.setText(text, type);
     }
 }
    String ClientName;

    public ItemAdapter(Context applicationContext, List<Item> itemArrayList) {
        this.context = applicationContext;
        this.items = itemArrayList;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText( items.get(i).getLogin());
        viewHolder.githublink1.setText(items.get(i).getHtmlUrl());

        String dateString=items.get(i).getCreated_at();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date convertedDate = new Date();

        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        PrettyTime p  = new PrettyTime();

        String datetime= p.format(convertedDate);

        viewHolder.Time.setText(datetime);


        /** going to add time to the viewHolder */


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, githublink1,Time;
        SharedPreferences sp;



        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            githublink1 = view.findViewById(R.id.githublink1);
            Time = view.findViewById(R.id.Timer);
            //imageView = view.findViewById(R.id.cover);







          //  sp = context.getSharedPreferences("com.example.joel.basicauthretrofit", Context.MODE_PRIVATE);


            //on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {



                    final int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Item clickedDataItem = items.get(pos);
//                        clientId =  items.get(pos).getClientId();


                        Client Client = new Client();
                        Service apiService = com.example.joel.hey.Client.getClient().create(Service.class);
                        Call<ClientResponse> call = apiService.getClientDetails(clickedDataItem.getClientId());
                        call.enqueue(new Callback<ClientResponse>() {
                            @Override
                            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {

                                Toast.makeText(v.getContext(), response.body().getDetails().getEmail(), Toast.LENGTH_SHORT).show();

                                String dateString= items.get(pos).getCreated_at();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                                Date convertedDate = new Date();
                                try {
                                    convertedDate = dateFormat.parse(dateString);
                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                PrettyTime p  = new PrettyTime();
                                String datetime= p.format(convertedDate);



//                                SharedPreferences.Editor editor = sp.edit();
//                                editor.putString("created_at",datetime );



                                Intent intent = new Intent(context, DetailActivity.class);
                                intent.putExtra("name", response.body().getDetails().getName());
                                intent.putExtra("email", response.body().getDetails().getEmail());
                                intent.putExtra("phone_number", response.body().getDetails().getPhone_number());
                                intent.putExtra("house_number", response.body().getDetails().getHouse_number());
                                intent.putExtra("location", response.body().getDetails().getLocation());
                                intent.putExtra("street_name", response.body().getDetails().getStreet_name());
                                intent.putExtra("gps_coordinates", response.body().getDetails().getGps_coordinates());
                                intent.putExtra("created_at",datetime);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);


                            }

                            @Override
                            public void onFailure(Call<ClientResponse> call, Throwable t) {
                                Toast.makeText(v.getContext(), "Error", Toast.LENGTH_SHORT).show();

                            }
                        });



                        Toast.makeText(v.getContext(), "You clicked " + String.valueOf(clickedDataItem.getClientId()), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }
}
