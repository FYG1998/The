package com.example.demo.activity;

 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.AdapterView;
 import android.widget.GridView;
 import android.widget.SimpleAdapter;

 import com.example.demo.base.BaseActivityTwo;
 import com.example.demo.R;
 import java.util.ArrayList;
 import java.util.HashMap;


public class MainTbs extends BaseActivityTwo {


    private Context mContext = null;
    private SimpleAdapter gridAdapter;
    private GridView gridView;
    private ArrayList<HashMap<String, Object>> items;
    private static String[] titles = null;

    // add constant here
    private static final int TBS_WEB = 0;
    private static final int FULL_SCREEN_VIDEO = 1;
    private static final int FILE_CHOOSER = 2;

    private static boolean main_initialized = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintbs);
        setTitle("Tbs_tools");
        setBackBtn();


        mContext = this;
        if (!main_initialized) {
            this.new_init();
        }
    }
    private void new_init() {
        items = new ArrayList<HashMap<String, Object>>();
        this.gridView = (GridView) this.findViewById(R.id.item_grid);

        if (gridView == null)
            throw new IllegalArgumentException("the gridView is null");

        titles = getResources().getStringArray(R.array.index_titles);
        int[] iconResourse = { R.drawable.tbsweb, R.drawable.fullscreen, R.drawable.filechooser };

        HashMap<String, Object> item = null;
        // HashMap<String, ImageView> block = null;
        for (int i = 0; i < titles.length; i++) {
            item = new HashMap<String, Object>();
            item.put("title", titles[i]);
            item.put("icon", iconResourse[i]);

            items.add(item);
        }
        this.gridAdapter = new SimpleAdapter(this, items,
                R.layout.function_block, new String[] { "title", "icon" },
                new int[] { R.id.Item_text, R.id.Item_bt });
        if (null != this.gridView) {
            this.gridView.setAdapter(gridAdapter);
            this.gridAdapter.notifyDataSetChanged();
            this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> gridView, View view, int position, long id) {
                    Intent intent = null;

                    switch (position) {
                        case TBS_WEB: {
                            intent = new Intent(MainTbs.this, Browser.class);
                            MainTbs.this.startActivity(intent);

                        }
                        break;
                        case FULL_SCREEN_VIDEO: {
                            intent = new Intent(MainTbs.this, FileChooser.class);
                            MainTbs.this.startActivity(intent);
                        }
                        break;

                        case FILE_CHOOSER: {
                            intent = new Intent(MainTbs.this, OpenFile.class);
                            MainTbs.this.startActivity(intent);

                        }
                        break;

                    }



                }
            });

        }
        main_initialized = true;

    }

    @Override
    protected void onResume() {
        this.new_init();

        // this.gridView.setAdapter(gridAdapter);
        super.onResume();
    }




}
