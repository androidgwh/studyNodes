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
        initPush();
    }

    private void initPush(){
        try {
            //第一种方式，写在资源文件中
            String classPath = getString(R.string.init_clazz);
            //第二种方式，写在buildConfig中
            //String clazzPath = BuildConfig.INIT_CLASS;
            Class<? extends PushInitInterface> initClazz = (Class<? extends PushInitInterface>) getClassLoader().loadClass(classPath);
            PushInitInterface platformInitializer = initClazz.newInstance();
            platformInitializer.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
