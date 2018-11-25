package com.example.myeonghusong.lightinstagram;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import com.example.myeonghusong.lightinstagram.db.dao.Feed;
import com.example.myeonghusong.lightinstagram.util.FileUtil;

import java.io.File;
import java.util.List;

/**
 * Created by myeonghusong on 2018. 11. 25..
 */

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.FeedViewHolder> {

    protected final String TAG = getClass().getSimpleName();

    protected Context mContext;


    private File imgFile;

    class FeedViewHolder extends RecyclerView.ViewHolder {
         ImageView feedImageView;
         TextView feedTextView;

        private FeedViewHolder(View itemView)
        {
            super(itemView);
            feedImageView = itemView.findViewById(R.id.iv_picture);
            feedTextView = itemView.findViewById(R.id.tv_writing);
        }
    }

    private final LayoutInflater mInflater;
    private List<Feed> mFeeds;

    FeedListAdapter(Context context)
    {
        mContext = context;
       // mFeeds = feeds;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = mInflater.inflate(R.layout.recycler_view_row, parent, false);

        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position)
    {
        TextView textView = holder.feedTextView;
        ImageView imageView = holder.feedImageView;
        if(mFeeds != null)
        {
            Feed current = mFeeds.get(position);
            textView.setText(current.getText());

            String uri = current.getImageUrl();
//            imgFile = new File(uri);
//            if(imgFile.exists())
//            {
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                ImageView myImage = imageView;
//                myImage.setImageBitmap(myBitmap);
//            }
//            Log.d(TAG, uri);
            Uri imgUri = Uri.parse(uri);

            Glide.with(mContext)
                    .load(FileUtil.getPath(mContext, imgUri))
                    .into(imageView);
            holder.feedImageView.setImageURI(imgUri);
        }
        else
        {
            Log.d(TAG, "onBindViewHolder: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    void setFeeds(List<Feed> feeds)
    {
        mFeeds = feeds;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        if(mFeeds != null)
            return mFeeds.size();
        else return 0;
    }
}
