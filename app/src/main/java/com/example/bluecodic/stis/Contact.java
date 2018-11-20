package com.example.bluecodic.stis;

/**
 * Created by BlueCodic on 4/7/2018.
 */

public class Contact {

    private String Name;
    private  String Phon;
    private int photo;


    public Contact() {
    }


    public Contact(String name, String phon, int photo) {
        Name = name;
        Phon = phon;
        this.photo = photo;
    }

    //geter


    public String getName() {
        return Name;
    }

    public String getPhon() {
        return Phon;
    }

    public int getPhoto() {
        return photo;
    }

    //setter


    public void setName(String name) {
        Name = name;
    }

    public void setPhon(String phon) {
        Phon = phon;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}

