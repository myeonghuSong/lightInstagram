package com.example.myeonghusong.lightinstagram;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.myeonghusong.lightinstagram.db.dao.Feed;

import java.util.Date;
import java.util.List;

/**
 * Created by myeonghusong on 2018. 11. 25..
 */

public class FeedViewModel extends AndroidViewModel {
    private FeedRepository mRepository;
    private LiveData<List<Feed>> mAllFeeds;

    public FeedViewModel (Application application)
    {
        super(application);
        mRepository = new FeedRepository(application);
        mAllFeeds = mRepository.getAllFeeds();
    }

    LiveData<List<Feed>> getAllFeeds()
    {
        return mAllFeeds;
    }

    public void insert(Feed feed)
    {

        mRepository.insert(feed);
    }
}
