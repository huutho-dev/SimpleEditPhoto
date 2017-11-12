package com.huutho.photo.crop;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThoNh on 11/10/2017.
 */

public class StateBitmapManager {

    /**
     * List String Config
     * <p>
     * 0---------------- "@adjust brightness 1"
     * 1---------------- "@curve RGB(0,255)(255,0) @style cm mapping0.jpg 80 80 8 3"
     * 2---------------- "@beautify face 1 480 640"
     * 3---------------- "#unpack @blur lerp 0.75"
     * 4---------------- "@blur lerp 1"
     */
    private List<String> mConfigs;


    /**
     * Current index Config after Click Previous, or Next
     */
    private int mCurrentPositionConfig;


    /**
     * Current Config corresponding with mCurrentPositionConfig
     */
    private String mCurrentConfig;


    public StateBitmapManager() {
        mConfigs = new ArrayList<>();
    }

    public String getResultConfig() {
        StringBuilder result = new StringBuilder();
        for (String config : mConfigs) {
            result.append(config);
        }
        return result.toString();
    }

    public void removeConfig() {
        mConfigs.remove(mConfigs.size());
    }

    public void appendConfig(String config) {
        if (!TextUtils.isEmpty(config)) {
            mConfigs.add(config + " ");
        }
    }

















    private String getPreviousConfigAndSetCurrentPosition() {
        if (mCurrentPositionConfig > 0) {
            mCurrentPositionConfig--;
            return getConfig(mCurrentPositionConfig);
        }
        return getConfig(0);
    }

    private String getNextConfigAndSetCurrentPosition() {
        if (mCurrentPositionConfig < mConfigs.size()) {
            mCurrentPositionConfig++;
            return getConfig(mCurrentPositionConfig);
        }
        return getConfig(mCurrentPositionConfig);
    }


    private String getConfig(int index) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < index; i++) {
            String config = mConfigs.get(i);
            result.append(config);
        }
        return result.toString();
    }

}
