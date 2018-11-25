package com.example.myeonghusong.lightinstagram;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.myeonghusong.lightinstagram.db.dao.Feed;
import com.example.myeonghusong.lightinstagram.db.dao.FeedDao;

/**
 * Created by myeonghusong on 2018. 11. 25..
 */

@Database(entities = {Feed.class}, version = 2)
public abstract class FeedRoomDatabase extends RoomDatabase {
    public abstract FeedDao feedDao();    ///////////////////////?????????????

    private static volatile FeedRoomDatabase INSTANCE;

    static FeedRoomDatabase getDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (FeedRoomDatabase.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FeedRoomDatabase.class, "feed_database").addCallback(sRoomDatabaseCallback).fallbackToDestructiveMigration().build();
                }
            }
        }

        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final FeedDao mDao;

        PopulateDbAsync(FeedRoomDatabase db) {
            mDao = db.feedDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            Feed feed = new Feed("https://avatars1.githubusercontent.com/u/4494892?s=400&u=14d2eeb859aaa512c81748948585400b70f57471&v=4", "sad");
            mDao.insert(feed);
            feed = new Feed("https://avatars1.githubusercontent.com/u/4494892?s=400&u=14d2eeb859aaa512c81748948585400b70f57471&v=4", "Sad2");
            mDao.insert(feed);
            return null;
        }
    }

}
