package com.example.bluecodic.stis;

/**
 * Created by BlueCodic on 5/5/2018.
 */

public class mypostmodal {

    String postid;
    String email;
    String mypost;
    String name;

    public String getPostid() {
        return postid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMypost() {
        return mypost;
    }

    public void setMypost(String mypost) {
        this.mypost = mypost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public mypostmodal(String email, String mypost, String name,String postid) {
        this.email = email;
        this.mypost = mypost;
        this.name = name;
        this.postid=postid;
    }


}
