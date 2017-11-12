package com.huutho.photo;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import org.wysaid.common.Common;
import org.wysaid.nativePort.CGEImageHandler;
import org.wysaid.nativePort.CGENativeLibrary;
import org.wysaid.view.ImageGLSurfaceView;

import java.io.IOException;
import java.io.InputStream;

import dontcare.utils.BitmapUtils;

public class Main2Activity extends AppCompatActivity {

    private Bitmap mOriginBitmap;
    private ImageGLSurfaceView mImageView;
    private ImageView mImage;

    private SeekBar seek_bar ;

    String adjustConfig = "@adjust sharpen 100 @adjust lut filter/edgy_amber.png";


    private String ruleString = "@adjust sharpen 0";
    private String ruleString2 = "@adjust lut filter/edgy_amber.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // Load file from assets
        CGENativeLibrary.setLoadImageCallback(mLoadImageCallback, null);
        mOriginBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        mImage = findViewById(R.id.fuck);
        mImage.setImageBitmap(mOriginBitmap);

        mImageView = findViewById(R.id.image);
        mImageView.setDisplayMode(ImageGLSurfaceView.DisplayMode.DISPLAY_ASPECT_FIT);
        mImageView.setSurfaceCreatedCallback(new ImageGLSurfaceView.OnSurfaceCreatedCallback() {
            @Override
            public void surfaceCreated() {
                mImageView.setImageBitmap(mOriginBitmap);
                mImageView.setFilterWithConfig(adjustConfig);
            }
        });


        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView.setImageBitmap(mOriginBitmap);
            }
        });

        seek_bar = findViewById(R.id.seek_bar);
        Log.e("ThoNh","max" + seek_bar.getMax());
        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                mImageView.setFilterIntensityForIndex(progress/10f,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public CGENativeLibrary.LoadImageCallback mLoadImageCallback = new CGENativeLibrary.LoadImageCallback() {

        //Notice: the 'name' passed in is just what you write in the rule, e.g: 1.jpg
        //注意， 这里回传的name不包含任何路径名， 仅为具体的图片文件名如 1.jpg
        @Override
        public Bitmap loadImage(String name, Object arg) {

            Log.i(Common.LOG_TAG, "Loading file: " + name);
            AssetManager am = getAssets();
            InputStream is;
            try {
                is = am.open(name);
            } catch (IOException e) {
                Log.e(Common.LOG_TAG, "Can not open file " + name);
                return null;
            }

            return BitmapFactory.decodeStream(is);
        }

        @Override
        public void loadImageOK(Bitmap bmp, Object arg) {
            Log.i(Common.LOG_TAG, "Loading bitmap over, you can choose to recycle or cache");

            //The bitmap is which you returned at 'loadImage'.
            //You can call recycle when this function is called, or just keep it for further usage.
            //唯一不需要马上recycle的应用场景为 多个不同的滤镜都使用到相同的bitmap
            //那么可以选择缓存起来。
            bmp.recycle();
        }
    };
}
