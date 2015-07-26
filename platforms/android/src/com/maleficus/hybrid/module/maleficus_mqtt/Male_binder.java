package com.maleficus.hybrid.module.maleficus_mqtt;

import android.app.Service;
import android.os.Binder;

import java.lang.ref.WeakReference;

/**
 * Created by shchoi on 2015-07-26.
 */
public class Male_binder<S extends Service> extends Binder {
    private WeakReference<S> mService;

    public Male_binder(S service){
        mService = new WeakReference<S>(service);
    }

    public S getService(){
        return mService.get();
    }

    public void close(){
        mService = null;
    }
}
