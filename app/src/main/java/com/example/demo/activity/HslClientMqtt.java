package com.example.demo.activity;

import HslCommunication.Core.Types.ActionOperateExTwo;
import HslCommunication.Core.Types.OperateResult;
import HslCommunication.Core.Types.OperateResultExTwo;
import HslCommunication.MQTT.MqttConnectionOptions;
import HslCommunication.MQTT.MqttCredential;
import HslCommunication.MQTT.MqttSyncClient;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demo.BaseActivityTwo;
import com.example.demo.R;

public class HslClientMqtt extends BaseActivityTwo {

   static MqttSyncClient mqttSyncClient ;

   static EditText editTextipaddress,editTextport,editTextsendtext;
   static Button buttonclose,buttonsend;

    public void initview() {
        setTitle("HSL mqtt 测试");  // BaseActivityTwo 定义的 标题名称
        setBackBtn(); //继承BaseActivityTwo里定义的 返回事件

        editTextipaddress = findViewById(R.id.ipaddress);
        editTextport = findViewById(R.id.port);
        editTextsendtext = findViewById(R.id.sendtext);
        buttonclose = findViewById(R.id.close);
        buttonsend = findViewById(R.id.nuttonsend);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hslclientmqtt);

        initview();


        buttonclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            mqttSyncClient.ConnectClose();

            }
        });

        buttonsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                  //  if(mqttSyncClient != null) mqttSyncClient.ConnectClose();

                    MqttConnectionOptions options = new MqttConnectionOptions();
                    options.IpAddress = editTextipaddress.getText().toString();
                    options.Port = Integer.parseInt(editTextport.getText().toString());
                    options.ClientId = "";
                    options.Credentials = new MqttCredential("","");

                    mqttSyncClient = new MqttSyncClient(options);
                    OperateResult connect = mqttSyncClient.ConnectServer();
                    if(connect.IsSuccess){

                        Log.e("TAG", "192.168.43.97连接成功" );

                    }
                    else {
                        //连接失败
                    }
                }
                catch (Exception ex){
                    //异常
                }


                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        OperateResultExTwo<String, String> read = mqttSyncClient.ReadString( "AA", editTextsendtext.getText().toString(), new ActionOperateExTwo<Long, Long>(){
                                    @Override
                                    public void Action(Long m, Long n) {
                      /*  sendProgressBar.setMaximum(n.intValue());
                        sendProgressBar.setValue(m.intValue());*/
                                    }
                                }, null,
                                new ActionOperateExTwo<Long, Long>(){
                                    @Override
                                    public void Action(Long m, Long n) {
                       /* receiveProgressBar.setMaximum(n.intValue());
                        receiveProgressBar.setValue(m.intValue());*/
                                    }
                                });
                        if (read.IsSuccess)
                        {
                            String str1 =read.Content1;
                            String str2 =read.Content2;
                            Log.e("TAG", "read.Content1"+ str1);
                            Log.e("TAG", "read.Content2"+ str2 );
                        }
                        else
                        {
                            Log.e("TAG", "no");
                        }


                    }
                });
                thread.start();

            }
        });


    }









}