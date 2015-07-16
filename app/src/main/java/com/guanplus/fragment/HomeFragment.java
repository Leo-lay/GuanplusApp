package com.guanplus.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.guanplus.MainActivity;
import com.guanplus.R;
import com.guanplus.adapter.CommonAdapter;
import com.guanplus.adapter.ViewHolder;
import com.guanplus.manager.BaseApplication;
import com.guanplus.utils.PromptManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Scleo on 2015/7/16.
 */
public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
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
        mDatas.add("报销");
        mDatas.add("借还款");
        mDatas.add("买卖");
        return mDatas;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = View.inflate(getActivity(),R.layout.home_frag,null);
        /*ListView lv = (ListView) view.findViewById(R.id.lvHome);
        lv.setAdapter(new HomeAdapter(getActivity().getApplicationContext(),mDatas,R.layout.layout_lv_home));
        lv.setOnItemClickListener(this);*/
        TextView tv = (TextView) view.findViewById(R.id.tvTitleName);
        tv.setText("管+");

        ImageView ivLeft = (ImageView) view.findViewById(R.id.ivTitleLeft);
        ivLeft.setOnClickListener(this);
        ImageView ivFind = (ImageView) view.findViewById(R.id.ivTitleFind);
        ImageView ivNotice = (ImageView) view.findViewById(R.id.ivTitleNotice);

        ListView lv = (ListView) view.findViewById(R.id.lvHome);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.layout_lv_home, R.id.tvName, initData());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        //view = inflater.inflate(R.layout.list_view, null);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                PromptManager.showToast(getActivity(),"报销");
                //fragment = new HomeFragment();
                break;
            case 1:
                PromptManager.showToast(getActivity(),"借还款");
                //fragment = new HomeFragment();
                break;
            case 2:
                PromptManager.showToast(getActivity(),"买卖");
                //fragment = new HomeFragment();
                break;
        }
        //switchFragment(fragment);
    }

    private void switchFragment(Fragment fragment) {
        if(fragment!=null&&getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).switchFrag(fragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivTitleLeft:
                //switchFragment(new LeftMenuFragment());
                break;
            default:
                break;
        }
    }

    class HomeAdapter extends CommonAdapter<String>{

        public HomeAdapter(Context context, List<String> datas, int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        public void doView(ViewHolder holder, String s) {
            holder.setText(R.id.tvName,s);
        }
    }
}
