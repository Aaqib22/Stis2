package com.example.bluecodic.stis;


public class Home {

    private  String postid;
    private String name;
    private String status;
    private  int photo;
    private String comment;
    private String like;
    private String like_count;


    public Home() {
    }

    public Home(String name, String status, int photo, String comment, String like, String like_count,String postid) {
        this.name = name;
        this.status = status;
        this.photo = photo;
        this.comment = comment;
        this.like = like;
        this.like_count = like_count;
        this.postid=postid;
    }

    //geter
    public String getPostid() {
        return postid;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getPhoto() {
        return photo;
    }

    public String getComment() {
        return comment;
    }

    public String getLike() {
        return like;
    }

    public String getLike_count() {
        return like_count;
    }


    //seter


    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }
}

