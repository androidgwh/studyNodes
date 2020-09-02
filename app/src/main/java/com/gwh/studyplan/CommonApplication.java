package com.gwh.studyplan;

import android.app.Application;

/**
 * Created by Guo Wenhui
 * 2020/8/25
 **/
public class CommonApplication extends Application {
    public static CommonApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
}
