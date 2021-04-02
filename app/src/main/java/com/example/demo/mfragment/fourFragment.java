package com.example.demo.mfragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.demo.R;
import com.example.demo.adapter.FruitAdapter;
import com.example.demo.tools.Fruit;

import java.util.ArrayList;
import java.util.List;


//https://www.jianshu.com/p/b4bb52cdbeb7    RecyclerView

public class fourFragment extends Fragment {

    private RecyclerView recyclerView ;
    private List<Fruit> fruitList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_four, container, false);



        initFruits();
        recyclerView  = view.findViewById(R.id.rec_mz);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(getActivity(),fruitList);
        recyclerView.setAdapter(adapter);


        return view;
    }


    private void initFruits() {
        for (int i = 0; i < 5; i++) {
            Fruit apple = new Fruit  ("http://ww4.sinaimg.cn/large/610dc034jw1f6ipaai7wgj20dw0kugp4.jpg");
            fruitList.add(apple);
            Fruit banana = new Fruit("http://ww3.sinaimg.cn/large/610dc034jw1f6gcxc1t7vj20hs0hsgo1.jpg");
            fruitList.add(banana);
            Fruit orange = new Fruit("http://ww4.sinaimg.cn/large/610dc034jw1f6f5ktcyk0j20u011hacg.jpg");
            fruitList.add(orange);
            Fruit watermelon = new Fruit("http://ww1.sinaimg.cn/large/610dc034jw1f6e1f1qmg3j20u00u0djp.jpg");
            fruitList.add(watermelon);
            Fruit pear = new Fruit("http://ww3.sinaimg.cn/large/610dc034jw1f6aipo68yvj20qo0qoaee.jpg");
            fruitList.add(pear);
            Fruit grape = new Fruit ("http://ww3.sinaimg.cn/large/610dc034jw1f69c9e22xjj20u011hjuu.jpg");
            fruitList.add(grape);
            Fruit pineapple = new Fruit ("http://ww3.sinaimg.cn/large/610dc034jw1f689lmaf7qj20u00u00v7.jpg");
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("http://ww3.sinaimg.cn/large/c85e4a5cjw1f671i8gt1rj20vy0vydsz.jpg");
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("http://ww2.sinaimg.cn/large/610dc034jw1f65f0oqodoj20qo0hntc9.jpg");
            fruitList.add(cherry);
            Fruit mango = new Fruit("http://ww2.sinaimg.cn/large/c85e4a5cgw1f62hzfvzwwj20hs0qogpo.jpg");
            fruitList.add(mango);

        }
    }





}