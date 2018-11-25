package com.example.myeonghusong.lightinstagram;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myeonghusong.lightinstagram.BaseActivity;
import com.example.myeonghusong.lightinstagram.FeedRepository;
import com.example.myeonghusong.lightinstagram.FeedViewModel;
import com.example.myeonghusong.lightinstagram.R;
import com.example.myeonghusong.lightinstagram.db.dao.Feed;
import com.example.myeonghusong.lightinstagram.db.dao.FeedDao;
import com.example.myeonghusong.lightinstagram.util.FileUtil;

import java.util.List;

public class UploadActivity extends BaseActivity {

    private final int CODE_GALLERY = 1;

    private FrameLayout boxImage;
    private ImageView imgUpload;
    private TextView txtImageGuide;
    private TextInputEditText inputText;
    private String imagePath;

    private FeedViewModel feedViewModel;
    private FeedRepository feedRepository;
    private FeedDao feedDao;
    //private FeedLocalSource feedLocalSource = new FeedLocalSource();
    //private UserLocalSource userLocalSource = new UserLocalSource();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {
//
//            } else {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        1);
//            }
//        }
////읽기 권한 요청



        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);


        findView();
        setBoxImage();
    }

    @Override
    protected void initializeToolbar(Toolbar toolbar) {
        super.initializeToolbar(toolbar);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.icon_back));
        toolbar.inflateMenu(R.menu.menu_upload);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_upload:
                        upload();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODE_GALLERY:
                if (resultCode != Activity.RESULT_OK) break;
                Uri uri = data.getData();
                imagePath = FileUtil.getPath(context, uri);
                imgUpload.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                imgUpload.setVisibility(View.VISIBLE);
                txtImageGuide.setVisibility(View.GONE);
                break;
        }
    }

    private void findView() {
        boxImage = findViewById(R.id.box_image);
        imgUpload = findViewById(R.id.img_upload);
        txtImageGuide = findViewById(R.id.txt_image_guide);
        inputText = findViewById(R.id.input_text);
    }

    private void setBoxImage() {
        boxImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(FileUtil.MIME_IMAGE);
                startActivityForResult(intent, CODE_GALLERY);
            }
        });
    }

    private void upload() {
       // User user = userLocalSource.getMaster();

        String text = inputText.getText().toString();

        Log.d(TAG, "@@@@@@@@@@@@@@@@");
        Log.d(TAG, imagePath);
        Log.d(TAG, "@@@@@@@@@@@@@@@@@@@@@@2 ");
        if (!validate(text, imagePath))
        {
            Log.d(TAG, "upload: validate@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            return;
        }

        //
        //
        Feed feed = new Feed(imagePath, text);
        Log.d(TAG, imagePath);
        Log.d(TAG, inputText.getText().toString());
        feedViewModel.insert(feed);
//        Fragment fragment = new HomeFragment();
//        Bundle bundle = new Bundle(2);
//        bundle.putString("img", imagePath);
//        bundle.putString("txt", text);
//        fragment.setArguments(bundle);
        finish();
    }

    private boolean validate(String text, String imagePath) {
//        if (user == null) {
//            Toast.makeText(context, R.string.upload_empty_user, Toast.LENGTH_SHORT).show();
//            return false;
//        }

        if (TextUtils.isEmpty(text)) {
            Toast.makeText(context, R.string.upload_empty_text, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (imagePath == null) {
            Toast.makeText(context, R.string.upload_empty_image, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


}
