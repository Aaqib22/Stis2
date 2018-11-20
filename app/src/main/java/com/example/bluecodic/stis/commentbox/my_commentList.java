package com.example.bluecodic.stis.commentbox;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bluecodic.stis.R;

import java.util.List;



/**
 * Created by Marry Jin on 1/20/2018.
 */

public class my_commentList extends ArrayAdapter<my_comment> {
    private Activity context;
    private List<my_comment> mysearchList;

    public my_commentList(Activity context, List<my_comment> artistList)
    {
        super(context, R.layout.item_all_comment,artistList);
        this.context=context;
        this.mysearchList =artistList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.item_all_comment,null,true);

        TextView comment=(TextView) listViewItem.findViewById(R.id.commentcomment);
        TextView name=(TextView) listViewItem.findViewById(R.id.commentusername);
       ImageView profile=(ImageView) listViewItem.findViewById(R.id.commentdp);

       // RelativeLayout searchviewprofile=(RelativeLayout) listViewItem.findViewById(R.id.searchviewprofile);

       /* my_comment art= mysearchList.get(position);
        comment.setText(art.getComment());
        name.setText(art.getCommentername());*/


       /* searchviewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        */
        return listViewItem;
    }
}
