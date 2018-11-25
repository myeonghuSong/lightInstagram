package com.example.myeonghusong.lightinstagram;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myeonghusong.lightinstagram.db.dao.Feed;

import java.util.List;

/**
 * Created by myeonghusong on 2018. 11. 18..
 */

public class HomeFragment extends Fragment {

    protected final String TAG = getClass().getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FeedViewModel mFeedViewModel;

    private BottomNavigationView bottomNavigationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.homefragment, null);


        Context context = view.getContext();



        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        final FeedListAdapter adapter = new FeedListAdapter(context);
        mRecyclerView.setAdapter(adapter);

        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mFeedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);



        mFeedViewModel.getAllFeeds().observe(this, new Observer<List<Feed>>() {
            @Override
            public void onChanged(@Nullable List<Feed> feeds) {
                adapter.setFeeds(feeds);
            }
        });

        Feed feed = new Feed("https://avatars1.githubusercontent.com/u/4494892?s=400&u=14d2eeb859aaa512c81748948585400b70f57471&v=4","aasfsf");
        mFeedViewModel.insert(feed);

        return view;
    }





}
