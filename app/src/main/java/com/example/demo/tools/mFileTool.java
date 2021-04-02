package com.example.demo.tools;

import android.os.Environment;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

// File 文件工具类

public class mFileTool {

    // 传入路径 创建文件
    public static void mFileCreate(String CreateFilePath){
        //获取外部存储路径
        //String storage = Environment.getExternalStorageDirectory().getPath() + "/XXXXXXX";
        File dirFile = new File(CreateFilePath);
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

    //创建文件
    public static void mFileCreate(){
        //获取外部存储路径
        String storage = Environment.getExternalStorageDirectory().getPath() + "/A_Test";
        File dirFile = new File(storage);
        Log.d("dirFile", "" + dirFile);
        // exists 判断文件是否存在
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
    public static boolean fileIsExists(String strFile)
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


    //InputStream流写入
    public static void saveBit(InputStream inStream) throws IOException {

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




    //InputStream流写入保存
    public static void saveIO(InputStream inStream,String name) throws IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();//创建一个Buffer字符串

        byte[] buffer = new byte[1024];//每次读取的字符串长度，如果为-1，代表全部读取完毕

        int len = 0;//使用一个输入流从buffer里把数据读取出来

        while( (len=inStream.read(buffer)) != -1 ){

            outStream.write(buffer, 0, len);//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
        }

        inStream.close(); // 关闭输入流


        //把outStream里的数据写入内存
        byte[] data = outStream.toByteArray();//得到InputStream的二进制数据，以二进制封装得到数据，具有通用性

        File imageFile = new File(Environment.getExternalStorageDirectory().getPath() + "/A_Test/" + name +".mp4");//new一个文件对象用来保存图片，默认保存当前工程根目录

        FileOutputStream fileOutStream = new FileOutputStream(imageFile);//创建输出流

        fileOutStream .write(data);//写入数据

    }


}
