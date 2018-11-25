package com.example.myeonghusong.lightinstagram;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.myeonghusong.lightinstagram.db.dao.Feed;
import com.example.myeonghusong.lightinstagram.db.dao.FeedDao;

import java.util.Date;
import java.util.List;

/**
 * Created by myeonghusong on 2018. 11. 25..
 */

public class FeedRepository {
    private FeedDao mFeedDao;
    private LiveData<List<Feed>> mAllFeeds;

    FeedRepository(Application application) //////??????
    {
        FeedRoomDatabase db = FeedRoomDatabase.getDatabase(application); //////?????
        mFeedDao = db.feedDao();
        mAllFeeds = mFeedDao.getAllFeeds();
    }

    LiveData<List<Feed>> getAllFeeds() {
        return mAllFeeds;
    }

    public void insert (Feed feed)
    {
        new insertAsyncTask(mFeedDao).execute(feed);
    }

    private static class insertAsyncTask extends AsyncTask<Feed, Void, Void>
    {
        private FeedDao mAsyncTaskDao;

        insertAsyncTask(FeedDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Feed... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
