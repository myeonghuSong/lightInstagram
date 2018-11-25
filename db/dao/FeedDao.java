package com.example.myeonghusong.lightinstagram.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface FeedDao {
    @Insert
    void insert(Feed feed);

    @Query("SELECT * from feed_table ORDER BY id DESC")
    LiveData<List<Feed>> getAllFeeds();

    @Query("DELETE FROM feed_table")
    void deleteAll();
}
