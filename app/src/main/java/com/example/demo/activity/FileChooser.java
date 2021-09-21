package com.example.demo.activity;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo.base.BaseActivityTwo;
import com.example.demo.R;
import com.example.demo.tencent_tbs.X5WebView;
import com.tencent.smtt.sdk.ValueCallback;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileChooser extends BaseActivityTwo {

    /**
     * X5内核 用于展示文件选择器
     */
    private Activity activity;
    private X5WebView webView;
    private Button button;
    private TextView textView;
    private ValueCallback<Uri> uploadFile;


    private void initView() {
        setTitle("FileChoose");  // BaseActivityTwo 定义的 标题名称
        setBackBtn(); //继承BaseActivityTwo里定义的 返回事件
        button = findViewById(R.id.bttest);
        textView = findViewById(R.id.pathfile);
        webView = findViewById(R.id.web_filechooser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filechooser);
        activity = this;
        initView();
        initData();


    }

    private void initData() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooseProcess();
            }
        });
    }


    private void openFileChooseProcess() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE); //ACTION_OPEN_DOCUMENT 项来显示系统的picker UI让用户进行浏览
        intent.setType("*/*"); //过滤要搜索的文档类型
        //-------常用类型
        //图片
//intent.setType(“image/*”);
        //音频
//intent.setType(“audio/*”);
        //视频
//intent.setType(“video/*”);
//intent.setType(“video/*;image/*”);
        //从A页面使用startActivityForResult（）跳转到B页面，B页面点击返回时将新写入的值传回到A页面。
        startActivityForResult(Intent.createChooser(intent, "file"), 1);//调起文件管理器



    }

    /**
     * @param requestCode Intent { dat=content://com.android.externalstorage.documents/document/primary:A_Test/filetest.pdf flg=0x1 }
     * @param resultCode  1
     * @param data        -1
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            textView.setText(data.toString());
            String mPath = getImageAbsolutePath(this, data.getData());
            Log.e("路径", "onActivityResult: " + mPath);

            Intent intent = new Intent(FileChooser.this, OpenFile.class);
            intent.putExtra("path", mPath);
            startActivity(intent);

        }
        if (requestCode == 1) {
            Uri uri = data != null ? data.getData() : null;
            Log.d("调试", "uri====>" + uri);
            try {
                final ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
                final FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                Log.d("调试", fileDescriptor.toString());
                parcelFileDescriptor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //获取到Uri后，可以通过它查询文档的元数据。以上图为例，获取图片的名称，大小。
            Cursor cursor = getContentResolver().query(uri, null, null, null, null, null);
            if (cursor == null || !cursor.moveToFirst()) {
                return;
            }
            final String displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            final int indexOfSize = cursor.getColumnIndex(OpenableColumns.SIZE);

            final String size = !cursor.isNull(indexOfSize) ? cursor.getString(indexOfSize) : "Unknown";
            Log.d("调试", "displayName=" + displayName + ", size=" + size);

            cursor.close();


        }

    }


    /**
     * 根据Uri获取图片绝对路径.
     *
     * @param context  上下文
     * @param imageUri 图片的Uri
     * @return 图片绝对路径
     */
    public static String getImageAbsolutePath(Activity context, Uri imageUri) {
        if (context == null || imageUri == null)
            return null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri))
                return imageUri.getLastPathSegment();
            return getDataColumn(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }

    /**
     * 从本地设备数据库查询数据.
     *
     * @param context       上下文
     * @param uri           内容提供者的标识
     * @param selection     设置条件，相当于SQL语句中的where
     * @param selectionArgs 条件值
     * @return 查询结果
     */
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};  //告诉Provider要返回的内容（列Column）
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());

    }

    /**
     * 确保注销配置能够被释放
     * onDestroy() android 生命周期
     * 当Activity(用户调用finish()或系统由于内存不足)被系统销毁杀掉时系统调用，（整个生命周期只调用1次）用来释放onCreate()方法中创建的资源，如结束线程等。
     */
    @Override
    protected void onDestroy() {
        if (this.webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }


}