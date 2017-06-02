package com.ct.ctframe.utils;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * 项目名称：CTFrame.
 * 创建人： CT.
 *          用程序Activity管理类：用于Activity管理和应用程序退出
 * 创建时间: 2017/5/17.
 * GitHub:https://github.com/CNHTT
 */

public class AppManager {

    private  static Stack<Activity> activityStack;
    private static  AppManager instance;

    private AppManager(){};

    public static AppManager getAppManager(){
        if (instance == null)
            instance = new AppManager();
        return instance;
    }

    /**
     * add new activity
     * @param activity
     */
    public void addActivity(Activity activity){
        if ( activityStack == null)
            activityStack = new Stack<Activity>();
        activityStack.add(activity);
    }

    /**
     * Get the first add
     * @return
     */
    public Activity currentActivity(){
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * close activity
     * @param activity
     */
    public void finishActivity(Activity activity){
        if (activity!=null){
            activityStack.remove(activity);
            activity.finish();
            activity=null;
        }
    }


    /**
     * close Class<? extends Activity>
     * @param cls
     */
    public void finishActivity(Class<?> cls){
        for (Activity activity: activityStack) {
            if (activity.getClass().equals(cls))
                finishActivity(activity);
        }
    }


    /**
     * close all activity And  Exit
     * @param c
     */
    public void finishAllActivityAndExit(Context c){
        if (null != activityStack){
            for (Activity atv :activityStack){
                if (atv != null)
                    atv.finish();
            }
            activityStack.clear();
        }
    }
}
