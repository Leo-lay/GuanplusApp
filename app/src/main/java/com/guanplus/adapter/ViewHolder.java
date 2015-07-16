package com.guanplus.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder
{
	private int					mPosition;
	private SparseArray<View>	mContainer;
	private View				mConvertView;

	public ViewHolder(Context context, int layoutId, ViewGroup parent, int position)
	{
		this.mPosition = position;
		mContainer = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		mConvertView.setTag(this);
	}

	/***
	 * 作为类入口
	 * 得到ViewHolder
	 * 
	 * @param context
	 * @param layoutId
	 * @param convertView
	 * @param parent
	 * @param position
	 * @return
	 */
	public static ViewHolder get(Context context, int layoutId, View convertView, ViewGroup parent, int position)
	{
		if (convertView == null)
		{
			return new ViewHolder(context, layoutId, parent, position);
		}
		else
		{
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}

	public View getConvertView()
	{
		return mConvertView;
	}

	/***
	 * 通过View 的Id得到想要的控件
	 * @param id
	 * @return
	 */
	public <T extends View> T getView(int id)
	{
		View view = mContainer.get(id);
		if (view == null)
		{// 容器中没有存储，则通过findViewById的方式去获得View
			view = mConvertView.findViewById(id);
			mContainer.put(id, view);// 存储到容器中
		}
		return (T) view;
	}
	/***
	 * 设置TextView
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setText(int viewId, String text)
	{
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}
	/***
	 * 设置TextView
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setText(int viewId, int resId)
	{
		TextView tv = getView(viewId);
		tv.setText(resId);
		return this;
	}
	/**
	 * 设置ImageView
	 * @param viewId
	 * @param resId
	 * @return
	 */
	public ViewHolder setImageResource(int viewId, int resId)
	{
		ImageView iv = getView(viewId);
		iv.setImageResource(resId);
		return this;
	}
	
	/**
	 * 设置ImageView
	 * @param viewId
	 * @param resId
	 * @return
	 */
	public ViewHolder setImageBitmap(int viewId, Bitmap bm)
	{
		ImageView iv = getView(viewId);
		iv.setImageBitmap(bm);
		return this;
	}
	
	/**
	 * 设置ImageView
	 * @param viewId 控件Id
	 * @param drawable 图片
	 * @return
	 */
	public ViewHolder setImageBitmap(int viewId, Drawable drawable)
	{
		ImageView iv = getView(viewId);
		iv.setImageDrawable(drawable);
		return this;
	}
	
	/**
	 * 设置ImageView
	 * @param viewId 控件Id 
	 * @param uri 资源地址
	 * @return
	 */
	public ViewHolder setImageURL(int viewId, String url)
	{
		ImageView iv = getView(viewId);
		//v.setImageURI(uri);
		//ImageLoader.getInstance().displayImage(url, iv);
		return this;
	}
	
	
	
}
