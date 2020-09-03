package com.gwh.studyplan;

/**
 * Created by Guo Wenhui
 * 2020/9/2
 **/
public class PushManager {
    public static void init(){
    new PushInitInterface() {
        @Override
        public void init() {

        }
    }.init();
    }
}
