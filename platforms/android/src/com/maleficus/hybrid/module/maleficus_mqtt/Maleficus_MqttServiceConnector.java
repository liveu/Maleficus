package com.maleficus.hybrid.module.maleficus_mqtt;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import org.sidibe.mqtt.android.lib.MqttMessage;
import org.sidibe.mqtt.android.lib.MqttTopic;

/**
 * Created by shchoi on 2015-07-27.
 */
public class Maleficus_MqttServiceConnector implements ServiceConnection{
    public static Maleficus_MqttService service;
    public static Boolean isBound = false;
    private static String TAG_CCN = "sh_ccn";

    //서비스 바인드
    public void bind(Context context){
        Intent intent = new Intent(context, Maleficus_MqttService.class);
        context.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }


    //서비스 언바인드
    public void unbind(Context context){
        context.unbindService(this);
    }

    //서비스 접속되었을때 callback
    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        @SuppressWarnings("unchecked")
        LocalBinder<Maleficus_MqttService> localBinder = (LocalBinder<Maleficus_MqttService>)binder;
        service = localBinder.getService();
        isBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        isBound = false;
    }


    public String getGeneratedCliendId(){
        if(isBound){
            return service.getGeneratedClient();
        }
        return null;
    }

    public static void subscribeToTopic(MqttTopic mqttTopic){
        if(isBound){
            Log.v(TAG_CCN, "onTopicSubmitted3");
            Maleficus_MqttService.subscribeToTopic(mqttTopic);
//            service.subscribeToTopic(mqttTopic);
        }
    }

    public void publish(MqttMessage mqttMessage, MqttTopic topic){
        if(isBound){
            service.publish(mqttMessage, topic);
        }
    }

    public boolean IsConnected(){
        return this.service.isConnected();
    }

}



