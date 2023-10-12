package com.samples.phoneverification.utilities;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ActivityLifeCycle implements Application.ActivityLifecycleCallbacks {
    /**
     * @param activity
     * @param bundle
     */
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    /**
     * @param activity
     */
    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    /**
     * @param activity
     */
    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    /**
     * @param activity
     */
    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    /**
     * @param activity
     */
    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    /**
     * @param activity
     * @param bundle
     */
    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    /**
     * @param activity
     */
    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
