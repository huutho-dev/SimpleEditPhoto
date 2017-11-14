package com.huutho.photo;

import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.huutho.photo.di.DaggerEditorComponent;
import com.huutho.photo.di.EditorComponent;
import com.huutho.photo.di.module.AdjustModule;
import com.huutho.photo.di.module.CropModule;
import com.huutho.photo.di.module.EditorModule;
import com.huutho.photo.di.module.FilterModule;
import com.huutho.photo.di.module.FrameModule;
import com.huutho.photo.di.module.OverlayModule;
import com.huutho.photo.di.module.PaintModule;
import com.huutho.photo.di.module.RotateModule;
import com.huutho.photo.di.module.StickerModule;
import com.huutho.photo.di.module.ToolsModule;

/**
 * Created by ThoNh on 10/30/2017.
 */

public class App extends Application {

    private static App instance;
    public static EditorComponent editorComponent;

    public static App getInstance() {
        return instance;
    }


    private LruCache<String, Bitmap> mMemoryCache;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initCacheForBitmap();

        editorComponent = DaggerEditorComponent
                .builder()
                .editorModule(new EditorModule())
                .adjustModule(new AdjustModule())
                .cropModule(new CropModule())
                .filterModule(new FilterModule())
                .frameModule(new FrameModule())
                .overlayModule(new OverlayModule())
                .paintModule(new PaintModule())
                .rotateModule(new RotateModule())
                .stickerModule(new StickerModule())
                .toolsModule(new ToolsModule())
                .build();
    }


    // ========================================= CACHE =============================================

    private void initCacheForBitmap() {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };

    }

    public void removeBitmapMemoryCache(String key) {
        mMemoryCache.remove(key);
    }

    public void saveBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }
}
