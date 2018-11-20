package com.example.bluecodic.stis.commentbox;

/**
 * Created by Marry Jin on 1/20/2018.
 */

public class my_comment {

    String csid;
    String commenterid;
    String comment;
    String commentername;
    String commenterdp;

    public String getCsid() {
        return csid;
    }

    public String getCommenterid() {
        return commenterid;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentername() {
        return commentername;
    }

    public String getCommenterdp() {
        return commenterdp;
    }



    public my_comment(String csid, String commenterid, String comment, String commentername, String commenterdp) {
        this.csid = csid;
        this.commenterid = commenterid;
        this.comment = comment;
        this.commentername = commentername;
        this.commenterdp = commenterdp;
    }







    public my_comment(){}


}
