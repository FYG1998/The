package com.example.demo.activity;


 import android.app.Activity;
 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.AdapterView;
 import android.widget.GridView;
 import android.widget.SimpleAdapter;

 import com.example.demo.R;

 import java.util.ArrayList;
 import java.util.HashMap;


public class PublicBrowse extends Activity {


    private Context mContext = null;
    private SimpleAdapter gridAdapter;
    private GridView gridView;
    private ArrayList<HashMap<String, Object>> items;
    private static String[] titles = null;

    private static boolean main_initialized = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicbrowse);

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
                public void onItemClick(AdapterView<?> gridView, View view,
                                        int position, long id) {



                }
            });

        }
        main_initialized = true;

    }


}
