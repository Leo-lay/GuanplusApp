package com.guanplus.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.guanplus.R;
import com.guanplus.adapter.CommonAdapter;
import com.guanplus.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scleo on 2015/7/16.
 */
public class LeftMenuFragment  extends Fragment {
    private List<String> mDatas;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
    }

    private void initView() {

    }

    private List<String> initData() {
        mDatas = new ArrayList<String>();
        mDatas.add("fragment1");
        mDatas.add("fragment2");
        mDatas.add("fragment3");
        return mDatas;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_menu_frag, null);
        ListView lv  = (ListView) view.findViewById(R.id.lvLeftMenu);
        lv.setAdapter(new CommonAdapter<String>(getActivity(),mDatas,R.layout.layout_lv_home) {
            @Override
            public void doView(ViewHolder holder, String s) {
                holder.setText(R.id.tvName,s);
            }
        });
        return view;
    }

}
