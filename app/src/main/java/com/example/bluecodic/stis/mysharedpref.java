package com.example.bluecodic.stis;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marry Jin on 4/7/2018.
 */

public class mysharedpref {

    public final static String DEFAULT="N/A";

    Context context;

    public mysharedpref(Context context)
    {
        this.context=context;
    }


    public void setshared(String email,String name,String type)
    {

        SharedPreferences sharedPreferences=context.getSharedPreferences("userdetail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("name",name);
        editor.putString("type",type);
        editor.commit();
    }

    public Map<String, String> getshared()
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("userdetail", Context.MODE_PRIVATE);
        String email=sharedPreferences.getString("email",DEFAULT);
         String name=sharedPreferences.getString("name",DEFAULT);
        String type=sharedPreferences.getString("type",DEFAULT);

        if(email.equals(DEFAULT)||name.equals(DEFAULT)||type.equals(DEFAULT))
        {
            return null;
        }
        else
        {

            Map<String, String> map = new HashMap<String, String>();
            map.put("email", email);
            map.put("name", name);
            map.put("type", type);

            return map;
        }

    }

    public void logout(){

        SharedPreferences preferences =context.getSharedPreferences("userdetail",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();


    }

}
