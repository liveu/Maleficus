package com.maleficus.hybrid;


import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import com.maleficus.hybrid.module.maleficus_mqtt.Maleficus_MqttService;
import com.maleficus.hybrid.module.maleficus_mqtt.Maleficus_MqttServiceConnector;
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
        Log.v(TAG_main, "onCreate()11111111111111111111");
        super.onCreate(savedInstanceState);
        Intent intent = new Intent("com.maleficus.hybrid.module.maleficus_mqtt.Maleficus_MqttService");
//        Intent intent = new Intent(this.getApplicationContext(),Maleficus_MqttConnector.class);
        Log.v(TAG_main, "mqttServiceStart111111111111111111111");
        startService(intent);
        loadUrl(launchUrl);
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
