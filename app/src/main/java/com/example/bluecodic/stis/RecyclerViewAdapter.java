package com.example.bluecodic.stis;

/**
 * Created by BlueCodic on 4/7/2018.
 */

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by BlueCodic on 4/7/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    Context mContext;
    List<Contact> mData;
    Dialog myDialog;

    public RecyclerViewAdapter(Context mContext, List<Contact> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_contect,parent,false);
        final MyViewHolder mHolder = new MyViewHolder(v);

        // dialog ini

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dilog_contact);



        mHolder.item_contect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView dialog_name_tv = (TextView) myDialog.findViewById(R.id.dilog_name);
                TextView dialog_phon_tv = (TextView) myDialog.findViewById(R.id.dilog_phon);
                ImageView dialog_img_iv = (ImageView) myDialog.findViewById(R.id.dialog_img);

                dialog_name_tv.setText(mData.get(mHolder.getAdapterPosition()).getName());
                dialog_phon_tv.setText(mData.get(mHolder.getAdapterPosition()).getPhon());
                dialog_img_iv.setImageResource(mData.get(mHolder.getAdapterPosition()).getPhoto());

                myDialog.show();
            }
        });

        return mHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_phon.setText(mData.get(position).getPhon());
        holder.img.setImageResource(mData.get(position).getPhoto());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name;
        private  TextView tv_phon;
        private ImageView img;
        private LinearLayout item_contect;


        public MyViewHolder(View itemView) {
            super(itemView);

            item_contect = (LinearLayout) itemView.findViewById(R.id.contect_item_id);

            tv_name = (TextView) itemView.findViewById(R.id.name_contact);
            tv_phon = (TextView) itemView.findViewById(R.id.Phone_contact);
            img = (ImageView) itemView.findViewById(R.id.image_contect);

        }
    }
}

