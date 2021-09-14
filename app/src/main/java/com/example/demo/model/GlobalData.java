package com.example.demo.model;


import android.app.AlertDialog;
import android.content.Context;

import com.example.demo.R;
import com.example.demo.utils.AlertDialogUtil;
import com.example.demo.utils.SoundUtils;

/**
 * Created by gpk on 2019/7/17.
 * 全局数据控制类
 */
public class GlobalData {
    /*
     * 单例模式
     * 静态内部类
     * 这种方式跟饿汉式方式采用的机制类似，但又有不同。两者都是采用了类装载的机制来保证初始化实例时只有一个线程。不同的地方在饿汉式方式是只要Singleton类被装载就会实例化，没有Lazy-Loading的作用，而静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance方法，才会装载SingletonInstance类，从而完成Singleton的实例化。
       类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM帮助我们保证了线程的安全性，在类进行初始化时，别的线程是无法进入的。
       优点：避免了线程不安全，延迟加载，效率高。
     * */
    private GlobalData() {}

    private static class SingleInstance{
        private static final GlobalData INSTANCE =new GlobalData();
    }
    public static GlobalData getInstance(){
        return SingleInstance.INSTANCE;
    }

    /**
     * 属性
     */
    private User user;





    /**
     * 显示异常信息
     */
    public void showExceptWithToast(Context context, String exMsg)
    {
//        ToastUtil.show(context,exMsg,13000);
        showComfirmDialog(context,exMsg);
        SoundUtils.playSound(R.raw.error);
    }

    /**
     * 显示未登录信息
     */
    public void showLoginWithToast(Context context)
    {
//        ToastUtil.show(context,"请先用户登录！",5000);
        showComfirmDialog(context,"请先用户登录！");
        SoundUtils.playSound(R.raw.error);
    }

    /**
     * 显示soap返回提示信息
     */
    public void showSoapInfoWithToast(Context context,String msg)
    {
//        ToastUtil.show(context,msg, 13000);
        showComfirmDialog(context,msg);
        SoundUtils.playSound(R.raw.error);
    }

    /**
     * 显示soap成功提示信息
     * @param context
     * @param msg msg为空时，不提示
     */
    public void showSuccessWithToast(Context context,String msg) {
        if (!msg.isEmpty()) {
//            ToastUtil.show(context, msg, 9000);
            showComfirmDialog(context,msg);
        }
        SoundUtils.playSound(R.raw.sucess);
    }

    /**
     * 处理操作返回结果，和声音提示
     * @param msg
     * @param soundId
     */
    public void showSoapResult(Context context,String msg,final int soundId){

        if (!msg.isEmpty()) {
            showComfirmDialog(context,msg);
        }
        SoundUtils.playSound(soundId);
    }
    /**
     * 显示提示弹框
     * @param context
     * @param msg
     */
    private void showComfirmDialog(final Context context, String msg)
    {
        AlertDialogUtil dialogUtil=AlertDialogUtil.getInstance();
        dialogUtil.showConfirmDialog(context,msg);
        dialogUtil.setOnButtonClickListener(new AlertDialogUtil.OnButtonClickListener() {
            @Override
            public void onPositiveButtonClick(AlertDialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void onNegativeButtonClick(AlertDialog dialog) {

            }
        });
    }


}
