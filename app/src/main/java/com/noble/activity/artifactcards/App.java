package com.noble.activity.artifactcards;

import android.content.Context;
import android.support.multidex.MultiDex;
import com.blankj.utilcode.util.Utils;

public class App extends android.app.Application {

    private static App INSTANCE;

    public static App get() {
        return INSTANCE;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        Utils.init(App.get());
        com.ruzhan.lion.App.setApp(this);

    }
}