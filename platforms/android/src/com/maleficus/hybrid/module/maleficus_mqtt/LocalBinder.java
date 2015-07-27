package com.maleficus.hybrid.module.maleficus_mqtt;
import java.lang.ref.WeakReference;

import android.app.Service;
import android.os.Binder;
/**
 * Created by shchoi on 2015-07-27.
 */
public class LocalBinder<S extends Service> extends Binder
{
    private WeakReference<S> mService;

    public LocalBinder(S service)
    {
        mService = new WeakReference<S>(service);
    }
    public S getService()
    {
        return mService.get();
    }
    public void close()
    {
        mService = null;
    }
}
