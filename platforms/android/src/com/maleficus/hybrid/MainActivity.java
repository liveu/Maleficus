/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.maleficus.hybrid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.maleficus.hybrid.module.maleficus_mqtt.Maleficus_MqttConnector;
import org.apache.cordova.*;

public class MainActivity extends CordovaActivity
{
    private final static String TAG_main = "sh_main";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Log.v(TAG_main, "onCreate()11111111111111111111");
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(this.getBaseContext(),Maleficus_MqttConnector.class);
        Log.v(TAG_main, "mqttServiceStart111111111111111111111");
//        this.startService(intent);
        // Set by <content src="index.html" /> in config.xml
        Intent intent = new Intent("com.maleficus.hybrid.module.maleficus_mqtt.Maleficus_MqttConnector");
        startService(intent);
        loadUrl(launchUrl);

    }

    public void mqttServiceStart(){
        Log.v(TAG_main, "mqttServiceStart011111111111111111111");
//        Intent intent = new Intent("com.maleficus.hybrid.module.maleficus_mqtt.Maleficus_MqttConnector");
//        startService(intent);
//        Intent intent = new Intent(this.getBaseContext(),Maleficus_MqttConnector.class);
        Log.v(TAG_main, "mqttServiceStart111111111111111111111");
//        this.startService(intent);
        Log.v(TAG_main, "mqttServiceStart211111111111111111111");
    }
}
