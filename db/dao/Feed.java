package com.example.myeonghusong.lightinstagram.db.dao;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by myeonghusong on 2018. 11. 25..
 */
@Entity(tableName = "feed_table")
public class Feed {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @ColumnInfo(name= "feed_text")
    private String text;

//    @ColumnInfo(name = "like_count")
//    private int likeCount;





    public Feed()
    {
        this.id=0;
        this.imageUrl ="https://avatars1.githubusercontent.com/u/4494892?s=400&u=14d2eeb859aaa512c81748948585400b70f57471&v=4";
        this.text ="......what";
    }
    public Feed( String imageUrl, String text) {
        //user id
        this.imageUrl = imageUrl;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NonNull String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public int getLikeCount() {
//        return likeCount;
//    }
//
//    public void setLikeCount(int likeCount) {
//        this.likeCount = likeCount;
//    }


}
