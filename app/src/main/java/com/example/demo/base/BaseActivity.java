package com.example.demo.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.demo.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//Activity与AppCompatActivity
public class BaseActivity extends AppCompatActivity {

    public static Context mContext;
    private static final String TAG = "BaseActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this; //Context=this就是指当前的


        setTranslucent(this);
        setAndroidNativeLightStatusBar(this,true);

    }

////////////////////////////////////////////////////////////////////////////
    /** 获取主题色 */
    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }
    /** 子类可以重写改变状态栏颜色 */
    protected int setStatusBarColor() {
        return getColorPrimary();
    }

    /** 子类可以重写决定是否使用透明状态栏 */
    protected boolean translucentStatusBar() {
        return false;
    }

    /** 设置状态栏颜色 */
    protected void initSystemBarTint() {
        Window window = getWindow();
        if (translucentStatusBar()) {
            // 设置状态栏全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            return;
        }
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上使用原生方法
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(setStatusBarColor());
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4-5.0使用三方工具类，有些4.4的手机有问题，这里为演示方便，不使用沉浸式
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
    }
//////////////////////////////////////////////////////////////////////////////////////

    //状态栏沉浸
     public  void setTranslucent(Activity mContext) {
         Log.d(TAG, "setTranslucent");

         //去除标题栏
         if (getSupportActionBar() != null){
             getSupportActionBar().hide();
         }

         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
             // 设置状态栏透明
             mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//应用全屏

             mContext.getWindow().getDecorView().setSystemUiVisibility(
                       View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                     | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
         } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
             mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
         }
     }

    /**
     * 谷歌原生方式修改 状态栏颜色
     * /设置状态栏字体颜色，true:代表黑色，false代表白色
     * @param mContext
     * @param dark
     */
    private static void setAndroidNativeLightStatusBar(Activity mContext, boolean dark) {
        View decor = mContext.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }


    //封装一个Toast类//继承BaseActivity 直接调用 showTost(string)
    public void showToast(String msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }

    //封装一个Intent
     public void mIntent(Class cls){
         Intent intent=new Intent(mContext,cls);
         startActivity(intent);
     }

     //动态访问权限弹窗
     public  Boolean checkPermission() {
         boolean isGranted = true;
         if (android.os.Build.VERSION.SDK_INT >= 23) {
             if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                 isGranted = false;
             }
             if (this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) !=PackageManager.PERMISSION_GRANTED) {
                 isGranted = false;
             }
             Log.i("读写权限获取"," ： "+isGranted);
             if (!isGranted) {
                 this.requestPermissions(
                         new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission
                                 .ACCESS_FINE_LOCATION,
                                 Manifest.permission.READ_EXTERNAL_STORAGE,
                                 Manifest.permission.WRITE_EXTERNAL_STORAGE},
                         102);
             }
         }
         return isGranted;
     }

     //创建文件
     public void FileC(){
         //获取外部存储路径
         String storage = Environment.getExternalStorageDirectory().getPath() + "/A_Test";
         File dirFile = new File(storage);
         Log.d("dirFile", "" + dirFile);
         if (!dirFile.exists()) {
             boolean mkdirs = dirFile.mkdirs();
             if (!mkdirs) {
                 Log.e("TAG", "文件夹创建失败");
             } else {
                 Log.e("TAG", "文件夹创建成功");
             }
         }
     }



     // 传入路径  判断文件是否存在
     public boolean fileIsExists(String strFile)
     {
         try
         {
             File f=new File(strFile);
             if(!f.exists())
             {
                 return false;
             }

         }
         catch (Exception e)
         {
             return false;
         }

         return true;
     }

     //把流转化为Bitmap图片
     public  Bitmap getLoacalBitmap(String url) {
         try {
             FileInputStream fis = new FileInputStream(url);
             return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

         } catch (FileNotFoundException e) {
             e.printStackTrace();
             return null;
         }
     }


     public void saveBit(InputStream inStream) throws IOException{

         ByteArrayOutputStream outStream = new ByteArrayOutputStream();//创建一个Buffer字符串

         byte[] buffer = new byte[1024];//每次读取的字符串长度，如果为-1，代表全部读取完毕

         int len = 0;//使用一个输入流从buffer里把数据读取出来

         while( (len=inStream.read(buffer)) != -1 ){

             outStream.write(buffer, 0, len);//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
         }

         inStream.close(); // 关闭输入流


              //把outStream里的数据写入内存
         byte[] data = outStream.toByteArray();//得到图片的二进制数据，以二进制封装得到数据，具有通用性

         File imageFile = new File(Environment.getExternalStorageDirectory().getPath() + "/A_Test/b");//new一个文件对象用来保存图片，默认保存当前工程根目录

         FileOutputStream fileOutStream = new FileOutputStream(imageFile);//创建输出流

         fileOutStream .write(data);//写入数据

     }



     //从服务器取图片


     public  Bitmap getHttpBitmap(String url) {
         URL myFileUrl = null;
         Bitmap bitmap = null;
         try {
             myFileUrl = new URL(url);
         } catch (MalformedURLException e) {
             e.printStackTrace();
         }
         try {
             HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
             conn.setConnectTimeout(0);
             conn.setDoInput(true);
             conn.connect();
             InputStream is = conn.getInputStream();
             bitmap = BitmapFactory.decodeStream(is);
             is.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return bitmap;
     }







 }
