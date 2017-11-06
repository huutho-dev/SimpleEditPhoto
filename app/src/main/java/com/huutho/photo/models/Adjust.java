package com.huutho.photo.models;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class Adjust {

    public String name;
    public int icon;
    public String tag;

    public int indexConfig;
    public float mMinValue;
    public float mMaxValue;
    public float mOriginValue;
    public float savePositionSeekBar = 0.5f;


    public Adjust(String name, int icon, String tag, int indexConfig, float minValue, float maxValue, float originValue) {
        this.indexConfig = indexConfig;
        this.name = name;
        this.icon = icon;
        this.tag = tag;
        mMinValue = minValue;
        mMaxValue = maxValue;
        mOriginValue = originValue;
    }


    /**
     *
     * @param _intensity value in range [0.0f .. 1.0f]
     * @return value of config for string config
     */
    public float calcIntensity(float _intensity) {
        savePositionSeekBar = _intensity;
        float result;
        if (_intensity <= 0.0f) {
            result = mMinValue;
        } else if (mMinValue >= 1.0f) {
            result = mMaxValue;
        } else if (_intensity <= 0.5f) {
            result = mMinValue + (mOriginValue - mMinValue) * _intensity * 2.0f;
        } else {
            result = mMaxValue + (mOriginValue - mMaxValue) * (1.0f - _intensity) * 2.0f;
        }
        return result;
    }
}
