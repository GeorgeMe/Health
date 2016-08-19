package com.is.health;

import com.is.health.sugar.SugarDBHelper;
import com.orm.SugarApp;

/**
 * Created by George on 2016/5/22.
 */
public class SeeApplication extends SugarApp {
    @Override
    public void onCreate() {
        super.onCreate();
        SugarDBHelper.getInstance().initDB(this.getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
