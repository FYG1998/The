package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import devlight.io.library.ntb.NavigationTabBar;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.example.demo.mfragment.fiveFragment;
import com.example.demo.mfragment.fourFragment;
import com.example.demo.mfragment.oneFragment;
import com.example.demo.mfragment.threeFragment;
import com.example.demo.mfragment.twoFragment;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import static com.example.demo.tools.mFileTool.mFileCreate;

public class CoreFragment extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corefragment);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //禁止EditText 自动弹出软键盘

        CheckPermission(); //check 权限
        mFileCreate();//创建文件
        initUI(); //初始化

    }


    private void initUI() {

        final List<Fragment> list = new ArrayList<>();
        oneFragment fragment1=new oneFragment();
        twoFragment fragment2=new twoFragment();
        threeFragment fragment3=new threeFragment();
        fourFragment fragment4=new fourFragment();
        fiveFragment fragment5=new fiveFragment();

        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);
        list.add(fragment5);


        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(new NavigationTabBar.Model.Builder(
                ResourcesCompat.getDrawable(getResources(), R.drawable.ic_first,null), Color.parseColor(colors[0]))
                .selectedIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_sixth,null)).title("Heart").build() );
        models.add(new NavigationTabBar.Model.Builder(
                ResourcesCompat.getDrawable(getResources(),R.drawable.ic_second,null),
                Color.parseColor(colors[1]))//.selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                .title("Cup").build() );
        models.add(new NavigationTabBar.Model.Builder(
                ResourcesCompat.getDrawable(getResources(),R.drawable.ic_third,null), Color.parseColor(colors[2]))
                .selectedIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_seventh,null))
                .title("Diploma").build() );
        models.add(new NavigationTabBar.Model.Builder(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_fourth,null), Color.parseColor(colors[3]))
                .title("Flag").build() );
        models.add(new NavigationTabBar.Model.Builder(
                ResourcesCompat.getDrawable(getResources(),R.drawable.ic_fifth,null), Color.parseColor(colors[4]))
                .selectedIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_eighth,null)).title("Medal").build() );

        navigationTabBar.setModels(models); //加载地图bar
        navigationTabBar.setViewPager(viewPager, 2); //设置主页 index
    }

    //动态访问权限弹窗
    public  Boolean CheckPermission() {
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



    //region 点击两次退出程序 有时间间隔 间隔内点击则退出程序 否则 则提示
    // fragment 层 加入的 退出APP

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK&& event.getAction() == KeyEvent.ACTION_DOWN) {
            onAppExit();
            return true;
        }
        return false;
    }

    private long firstClick;
    public void onAppExit() {
        if (System.currentTimeMillis() - this.firstClick > 2000L) {
            this.firstClick = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_LONG).show();
            return;
        }
        finish();
    }
    //endregion

}