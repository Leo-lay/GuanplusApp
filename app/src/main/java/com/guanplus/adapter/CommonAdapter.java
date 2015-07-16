package com.guanplus.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 通用的Adapter类
 * 
 * @author scleo@concordya.com
 * @param <T>
 */
public abstract class CommonAdapter<T> extends BaseAdapter
{
	protected List<T>			mDatas;
	protected Context			mContext;
	protected LayoutInflater	mInflater;
	private int					mLayoutId;
	
	public CommonAdapter(Context context, List<T> datas, int layoutId)
	{
		this.mContext = context;
		this.mDatas = datas;
		this.mLayoutId = layoutId;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mDatas == null ? 0 : mDatas.size();
	}

	@Override
	public T getItem(int position)
	{
		return mDatas == null ? null : mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = ViewHolder.get(mContext, mLayoutId, convertView, parent, position);
		doView(holder, mDatas.get(position));
		return holder.getConvertView();
	}

	public abstract void doView(ViewHolder holder, T t);

}
