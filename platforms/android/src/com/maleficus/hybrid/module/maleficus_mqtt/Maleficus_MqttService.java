package com.maleficus.hybrid.module.maleficus_mqtt;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Log;
import com.maleficus.hybrid.MainActivity;
import com.maleficus.hybrid.R;
import org.sidibe.mqtt.android.lib.*;

import java.util.List;

/**
 * Created by shchoi on 2015-07-26.
 */
public class Maleficus_MqttService extends Service implements MqttPushEventListener {

    private static String TAG_CON = "sh_con";
    private static final int NOTIFICATION_ID = 0X212;
    public static MqttAndroidClient mqttClient;
    public static final String ACTION_PUSH_ARRIVED = "action_push_arrived";
    public static final String ACTION_STATE_CHANGED = "action_state_changed";
    public static final String ACTION_CLIEND_ID = "action_client_id";
    public static final String KEY_CLIENT_ID = "key_client_id";
    public static final String ACTION_PUSH_SEND = "action_push_send";
    public static final String KEY_STATE = "key_state";
    public static final String KEY_DATA = "key_data";
    static final String HOST = "175.126.105.105";//Mqtt Broker Server IP
    static final String TOPIC_KEY = "key_topic";


    private boolean connected;

//    //컴포넌트에 반환되는 IBinder
//    private final IBinder mBinder = new LocalBinder();
//
//    //컴포넌트에 반환해줄 IBinder 클래스
//    public class LocalBinder extends Binder{
//        public Maleficus_MqttConnector getService(){
//            return Maleficus_MqttConnector.this;
//        }
//    }

    //call from Bridgeplugin certification method
    public void Maleficus_MqttConnector_test(){
        Log.v(TAG_CON, "Maleficus_MqttConnector_test333333333333333333");
    }


    //    @Override
//    public IBinder onBind(Intent intent) {
//        Log.v(TAG_CON, "onBindd333333333333333333");
//        return mBinder;
//    }

//    public void mqttConnect(){
//        Log.v(TAG_CON, "mqttConnect33333333333333");
//        mqttClient = new MqttAndroidClient(this, HOST);
//        mqttClient.addPushListener(this);
//        mqttClient.setCleanOnStart(true);
//        mqttClient.bind(new MqttTopic("kalana", 2));
//        mqttClient.start();
//        connected = true;
//        Log.v(TAG_CON, "mqttConnect33333333333333");
//    }

    /**MQTT CONNECTION METHOD*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG_CON, "Maleficus_MqttConnector_onStartCommand333333333333333333");
        if(connected == false) {
            mqttClient = new MqttAndroidClient(this, HOST);
            mqttClient.addPushListener(this);
            mqttClient.setCleanOnStart(true);
            mqttClient.bind(new MqttTopic("kalana", 2));
            mqttClient.start();
            connected = true;
        }else if(connected){

        }
        return START_REDELIVER_INTENT;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return new Male_binder<Service>(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mqttClient.disconnect();
    }

    @Override
    public void onPushMessageReceived(MqttMessage mqttMessage) {
        Log.v(TAG_CON, "onPushMessageReceived333333333333333333");
        Intent broacastIntent = new Intent(ACTION_PUSH_ARRIVED);
        broacastIntent.putExtra(KEY_DATA, (Parcelable)mqttMessage);
        sendBroadcast(broacastIntent);
        showMessageAsNotification(mqttMessage);
    }

    private void showMessageAsNotification(MqttMessage mqttMessage) {
        Log.v(TAG_CON, "showMessageAsNotificationd333333333333333333");
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Mqtt Message")
                .setContentText(mqttMessage.getContentText())
                .setSmallIcon(R.drawable.ic_launcher).build();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.putExtra(KEY_DATA, (Parcelable) mqttMessage);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.contentIntent = contentIntent;
        nm.notify(NOTIFICATION_ID + 1, notification);
    }

    private void showClientStateAsNotification(MqttClientState clientState) {
        if(connected == false) {
            Log.v(TAG_CON, "showClientStateAsNotification333333333333333333");
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Mqtt Status")
                    .setContentText(clientState.name())
                    .setSmallIcon(R.drawable.ic_launcher).build();
            Intent notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.putExtra(KEY_STATE, clientState);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.contentIntent = contentIntent;
            nm.notify(NOTIFICATION_ID, notification);
        }else if(connected){

        }
    }



    @Override
    public void onConnectionStateChanged(MqttClientState connectionState) {
        if(connected == false) {
            Log.v(TAG_CON, "onConnectionStateChanged333333333333333333");
            connected = connectionState == MqttClientState.CONNECTED;
            Intent broadcastIntent = new Intent(ACTION_STATE_CHANGED);
            broadcastIntent.putExtra(KEY_STATE, connectionState);
            sendBroadcast(broadcastIntent);
            showClientStateAsNotification(connectionState);
        }else if(connected){

        }
    }


    private void sendGeneratedClientId(List<String> strings) {
        Log.v(TAG_CON, "sendGeneratedClientId333333333333333333");
        Log.i("Clien ID", strings.toString());
        Intent broadcastIntent = new Intent(ACTION_CLIEND_ID);
        broadcastIntent.putExtra(KEY_CLIENT_ID, strings.toString());
        sendBroadcast(broadcastIntent);
    }

    public static void subscribeToTopic(MqttTopic mqttTopic) {
        Log.v(TAG_CON, "subscribeToTopic333333333333333333");
        mqttClient.bind(mqttTopic);

    }

    public String getGeneratedClient() {
        Log.v(TAG_CON, "getGeneratedClient333333333333333333");
        return mqttClient.getIdClient();
    }

    public static void publish(MqttMessage mqttMessage, MqttTopic topic) {
        Log.v(TAG_CON, "publish333333333333333333");
        mqttClient.push(mqttMessage, topic);

    }

    public boolean isConnected() {
        Log.v(TAG_CON, "isConnected333333333333333333");
        return connected;
    }

    @Override
    public void onClientConnectedOnBroker(List<String> clienIds) {
        Log.v(TAG_CON, "onClientConnectedOnBroker333333333333333333");
        sendGeneratedClientId(clienIds);
    }


}
