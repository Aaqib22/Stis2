package com.example.bluecodic.stis;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

/**
 * Created by BlueCodic on 4/9/2018.
 */

public class RecyclerHomeAdapter extends RecyclerView.Adapter<RecyclerHomeAdapter.HomeViewHolder> {

    List<Home> listitem;
    Context mmContext;
    Dialog mydialog;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    HashMap hashMap;

    public RecyclerHomeAdapter(Context mmContext, List<Home> listitem ) {
        this.listitem = listitem;
        this.mmContext = mmContext;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(v);

        return homeViewHolder;

    }
     Home itemList;
    @Override
    public void onBindViewHolder(final HomeViewHolder holder, final int position) {

        itemList = listitem.get(position);
        holder.tv2_name.setText(itemList.getName());
        holder.tv2_status.setText(itemList.getStatus());
        holder.tv_like_counter.setText(itemList.getLike_count());
        holder.img2.setImageResource(itemList.getPhoto());


        holder.btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(mmContext,Comments.class);
                i.putExtra("postid",itemList.getPostid());
                mmContext.startActivity(i);

            }
        });

        holder.btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mylike=(Integer.parseInt(itemList.getLike_count())+1);
                holder.tv_like_counter.setText(mylike+"");

                itemList.setLike_count(mylike+"");
                //putlike();
            }
        });

        holder.txt_option_digit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final PopupMenu popupMenu = new PopupMenu(mmContext,holder.tv_like_counter);
                popupMenu.inflate(R.menu.option_home);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mnu_save:
                                Toast.makeText(mmContext, "Saved", Toast.LENGTH_SHORT).show();
                                break;
                            case  R.id.mnu_del:
                                listitem.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(mmContext, "Deleted", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    private void putlike() {
       // ref=FirebaseDatabase.getInstance().getReference().child("posts").child("email").getParent();

        //hashMap.put("like", itemList.getLike_count().toString());



        //ref.child("posts").push().setValue(hashMap);
    }


    @Override
    public int getItemCount() {
        return listitem.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {




        public TextView tv2_name;
        public TextView tv2_status;
        public TextView tv_like_counter;
        public TextView txt_option_digit;
        public ImageView img2;
        public Button btn_comment;
        public  Button btn_like;

        public LinearLayout item_home;

        public HomeViewHolder(View itemView) {
            super(itemView);


            item_home =(LinearLayout)itemView.findViewById(R.id.home_item_id);

            tv2_name = (TextView)itemView.findViewById(R.id.name_home);
            tv2_status = (TextView)itemView.findViewById(R.id.status_home);
            txt_option_digit= (TextView)itemView.findViewById(R.id.option_digit);
            img2 = (ImageView) itemView.findViewById(R.id.img_home);
            btn_comment =(Button)itemView.findViewById(R.id.comment_btn_home);
            btn_like = (Button) itemView.findViewById(R.id.like_btn_home);
            tv_like_counter = (TextView) itemView.findViewById(R.id.like_home);
        }
    }



}