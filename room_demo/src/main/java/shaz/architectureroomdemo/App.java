package shaz.architectureroomdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by ${Shahbaz} on 07-11-2017
 */

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
