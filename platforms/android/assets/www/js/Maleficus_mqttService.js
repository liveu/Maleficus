/**
 * Created by shchoi on 2015-07-28.
 */
// /Maleficus_mqttService/ 모듈
    //  mqtt서버에서 특정 토픽으로 푸싱을 받기위해, native에 액션, 데이터를 제공하는 기능
    //  mqtt서버에서 특정 토픽과 메세지를 푸싱하기 위해, native에 액션, 데이터를 제공하는 기능


var
    Maleficus_mqttService =  (function($){
        //모듈 스코프 변수 선언
        var
            //mqttArg = { action  : "", qos : 0, topic : ""};
            mqttArg = [];


        //그 외 나머지 모듈 스코프 변수 선언
        var
           signIned = true;


        //MQTT 커넥트 메서드 /mqttConnect/
        mqttConnect = function(){
            mqttArg[0] = "shchoi";
            mqttArg[1] = "maleficus_mqttConnect";
            if(signIned){
                MaleficusJSBridgePlugin.bridgeAction(mqttArg[1], mqttArg);
                location.href = "tabIndex.html";
            }
        };

        //MQTT 구독 메서드 /mqttSubscribe/
        mqttSubscribe = function(){

        };

        //MQTT 푸싱 메서드 /mqttPush/
        mqttPush = function(){
            if(signIned){
                mqttArg[0] = "shchoi";
                mqttArg[1] = "maleficus_mqttPush";
                MaleficusJSBridgePlugin.bridgeAction(mqttArg[1], mqttArg);
                location.href = "tabIndex.html";
            }
        };

        //public 메소드 노출
        return {mqttConnect : mqttConnect,
            mqttSubscribe : mqttSubscribe,
            mqttPush : mqttPush
        }
    }());
