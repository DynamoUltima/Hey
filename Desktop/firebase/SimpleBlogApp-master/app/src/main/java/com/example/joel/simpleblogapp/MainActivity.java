package com.example.joel.simpleblogapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");

        mBlogList =(RecyclerView)findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(),model.getImage());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent singleBlogIntent = new Intent(MainActivity.this,BlogSingleActivity.class);
                        startActivity(singleBlogIntent);

                    }
                });

            }

        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);

              mView = itemView;
        }



        public void setTitle(String title){

            TextView post_title =mView.findViewById(R.id.post_title);
            post_title.setText(title);


        }

        public void setDesc(String desc){
            TextView post_desc = mView.findViewById(R.id.post_text);
            post_desc.setText(desc);
        }
       public void setImage(Context ctx, String image){
            ImageView post_image =mView.findViewById(R.id.post_image);
           Picasso.with(ctx).load(image).into(post_image);
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.add_action){
            startActivity( new Intent(MainActivity.this,PostActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }
}
