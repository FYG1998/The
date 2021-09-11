package com.example.demo.A_Test;

import HslCommunication.Core.Types.ActionOperateExTwo;
import HslCommunication.Core.Types.OperateResult;
import HslCommunication.Core.Types.OperateResultExTwo;
import HslCommunication.MQTT.MqttClient;
import HslCommunication.MQTT.MqttConnectionOptions;
import HslCommunication.MQTT.MqttSyncClient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.demo.base.BaseActivityTwo;
import com.example.demo.R;
import com.example.demo.model.spInfo;
import com.example.demo.utils.SPDataUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class HslClientMqtt extends BaseActivityTwo {

    private Context context;
    private MqttSyncClient mqttSyncClient = null;
    private EditText editTextipaddress, editTextport, editTextsendtext, editTexttopic;
    private Button buttonconnect, buttonclose, buttonsend, savesetting ,timetask;
    private TextView huichuan;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hslclientmqtt);
        context = this;

        initview();
        ininData();

    }

    public void initview() {
        setTitle("HSL mqtt 测试  Android 与 Winfrom 程式通讯");  // BaseActivityTwo 定义的 标题名称
        setBackBtn(); //继承BaseActivityTwo里定义的 返回事件

        editTextipaddress = findViewById(R.id.ipaddress);
        editTextport = findViewById(R.id.port);
        editTexttopic = findViewById(R.id.topic);
        editTextsendtext = findViewById(R.id.sendtext);
        buttonclose = findViewById(R.id.close);
        buttonsend = findViewById(R.id.nuttonsend);
        buttonconnect = findViewById(R.id.connect);
        huichuan = findViewById(R.id.huichuan);
        savesetting = findViewById(R.id.savesetting);
        timetask = findViewById(R.id.timetask);


    }

    private void ininData() {
        spInfo info = SPDataUtils.getspInfo(context);
        editTextipaddress.setText(info.getIpaddress());
        editTextport.setText(info.getPort());
        editTexttopic.setText(info.getTopic());
        editTextsendtext.setText(info.getSendtext());

        Connect();  //连接
        Disconnect(); //关闭连接
        send();  //通讯请求
        savedatatosp();


    }



    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {

            request();
        }
    };


    private void timetask() {
        timer.schedule(timerTask,1000);
    }

    public void request(){
        if (mqttSyncClient == null) return;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                MqttClient mqttClient = new MqttClient();//好像没有开发
                OperateResultExTwo<String, String> read = mqttSyncClient.ReadString(editTexttopic.getText().toString(), editTextsendtext.getText().toString(), new ActionOperateExTwo<Long, Long>() {
                            @Override
                            public void Action(Long m, Long n) {
                                Log.e("调试", "调试m1" + m.toString());
                                Log.e("调试", "调试n1" + n.toString());
                            }
                        }, null,
                        new ActionOperateExTwo<Long, Long>() {
                            @Override
                            public void Action(Long m, Long n) {
                                Log.e("调试", "调试m2" + m.toString());
                                Log.e("调试", "调试n2" + n.toString());

                            }
                        });
                if (read.IsSuccess) {
                    String str1 = read.Content1;
                    String str2 = read.Content2;
                    Log.e("调试", "read.Content1" + str1);
                    Log.e("调试", "read.Content2" + str2);

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    Date date = new Date(System.currentTimeMillis());
                    String sdata = simpleDateFormat.format(date);
                    Message msg = new Message();
                    msg.what = 2;
                    msg.obj = sdata + str2;
                    handler.sendMessage(msg);


                } else {
                    Log.e("调试", "no");
                }
            }
        });
        thread.start();
    }

    //连接
    private void Connect() {
        buttonconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mqttSyncClient != null) mqttSyncClient.ConnectClose();

                    MqttConnectionOptions options = new MqttConnectionOptions();
                    options.IpAddress = editTextipaddress.getText().toString();
                    options.Port = Integer.parseInt(editTextport.getText().toString());
                    options.ClientId = "";

                    mqttSyncClient = new MqttSyncClient(options);

                    //region网络连接不能放在主线程上，不然就会报错android.os.NetworkOnMainThreadException
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            OperateResult connect = mqttSyncClient.ConnectServer();
                            if (connect.IsSuccess) {
                                Log.e("调试", "连接成功");

                                Message msg = new Message();
                                msg.what = 1;
                                handler.sendMessage(msg);

                            } else {
                                Log.e("调试", "连接失败");
                                Log.e("调试", connect.ToMessageShowString());

                            }
                        }
                    }).start();

                    //endregion

                    timetask();


                } catch (Exception ex) {
                    Log.e("调试", ex.toString());
                }


            }
        });
    }

    //关闭连接
    private void Disconnect() {
        buttonclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mqttSyncClient != null) {
                    mqttSyncClient.ConnectClose();
                    showToast("关闭连接");
                    editTextipaddress.setText("");
                    editTextport.setText("");
                    editTextsendtext.setText("");

                }
            }
        });

    }

    //通讯请求
    private void send() {
        buttonsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mqttSyncClient == null) return;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        MqttClient mqttClient = new MqttClient();//好像没有开发
                        OperateResultExTwo<String, String> read = mqttSyncClient.ReadString(editTexttopic.getText().toString(), editTextsendtext.getText().toString(), new ActionOperateExTwo<Long, Long>() {
                                    @Override
                                    public void Action(Long m, Long n) {
                                        Log.e("调试", "调试m1" + m.toString());
                                        Log.e("调试", "调试n1" + n.toString());
                                    }
                                }, null,
                                new ActionOperateExTwo<Long, Long>() {
                                    @Override
                                    public void Action(Long m, Long n) {
                                        Log.e("调试", "调试m2" + m.toString());
                                        Log.e("调试", "调试n2" + n.toString());

                                    }
                                });
                        if (read.IsSuccess) {
                            String str1 = read.Content1;
                            String str2 = read.Content2;
                            Log.e("调试", "read.Content1" + str1);
                            Log.e("调试", "read.Content2" + str2);

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                            Date date = new Date(System.currentTimeMillis());
                            String sdata = simpleDateFormat.format(date);
                            Message msg = new Message();
                            msg.what = 2;
                            msg.obj = sdata + str2;
                            handler.sendMessage(msg);


                        } else {
                            Log.e("调试", "no");
                        }
                    }
                });
                thread.start();

            }
        });
    }

    // 保存
    private void savedatatosp() {
        savesetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean flag = SPDataUtils.saveHslClientMqtt(context, "savehslmqtt",
                        editTextipaddress.getText().toString().trim(), editTextport.getText().toString().trim(),
                        editTexttopic.getText().toString().trim(), editTextsendtext.getText().toString().trim());
                if (flag) {
                    showToast("SP保存成功");
                }


            }
        });
    }


    /**
     * 线程 ;安卓中的网络访问要在子线程中访问 UI只能在主线程程中更新
     */
    @SuppressLint("HandlerLeak") //线程
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    showToast("连接成功");
                    break;
                case 2:
                    huichuan.setText(msg.obj.toString());
                    break;

            }
        }
    };


}