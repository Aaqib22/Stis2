package com.example.bluecodic.stis;

/**
 * Created by BlueCodic on 5/7/2018.
 */

public class Upload
{

    public String filename;
    public String url;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String filename, String url) {
        this.filename = filename;
        this.url = url;
    }

    public String getName() {
        return filename;
    }

    public String getUrl() {
        return url;
    }
}
