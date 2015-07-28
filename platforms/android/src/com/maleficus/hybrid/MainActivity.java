package com.maleficus.hybrid;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import com.maleficus.hybrid.module.maleficus_mqtt.Maleficus_MqttService;
import com.maleficus.hybrid.module.maleficus_mqtt.Maleficus_MqttServiceConnector;
import com.maleficus.hybrid.plugins.MaleficusJSBridgePlugin;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.sidibe.mqtt.android.lib.MqttMessage;
import org.sidibe.mqtt.android.lib.MqttTopic;

public class MainActivity extends CordovaActivity
{
    private final static String TAG_main = "sh_main";
    Maleficus_MqttService mService;//연결타입 서비스
    boolean mBound = false;//서비스 연결 여부
    public static Maleficus_MqttServiceConnector serviceConnector;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        MaleficusJSBridgePlugin mPlugin = new MaleficusJSBridgePlugin();
        mPlugin.show(this);
        Log.v(TAG_main, "onCreate()11111111111111111111");
        super.onCreate(savedInstanceState);
        Intent intent = new Intent("com.maleficus.hybrid.module.maleficus_mqtt.Maleficus_MqttService");
        loadUrl(launchUrl);
    }


    public void mqttConnect(){
        //인증 클래스를 외부로 빼기
        Boolean certified = false;


        if(certified){
            Log.v(TAG_main, "mqttConnect111111111111111111111");
            Intent intent = new Intent("com.maleficus.hybrid.module.maleficus_mqtt.Maleficus_MqttService");
            startService(intent);
        }else{
            Log.v(TAG_main, "not certified");
        }

    }


    public static void onTopicSubmitted(Context context, JSONArray args){
        Log.v(TAG_main, "onTopicSubmitted1");
        String topicName = "";//db쪽에서 토픽 읽어오기
        //test
        topicName = "shchoi";
        MqttTopic topic = new MqttTopic(topicName);
        Maleficus_MqttService.subscribeToTopic(topic);
    }


    public static void onPushMessage(Context context, JSONArray args){
        Log.v(TAG_main, "onPushMessage");
        MqttTopic topic = new MqttTopic("shchoi");
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setContent("shchoi8888888888888888888888888");
        mqttMessage.setRetains(true);
        mqttMessage.setQos(2);
        Maleficus_MqttService.publish(mqttMessage, topic);
    }

}
