package com.guanplus.bean;

import java.io.Serializable;

/**
 * 收到的推送内容
 * 
 * @author Toby
 * 
 */
public class PushBean implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8898203875574877724L;

	/*
	 * {
	 * "EntityType": 0,
	 * "Id": "4b56ef6b-a191-4e75-8fc3-a98527a69dc4",
	 * "Number": "B00000060",
	 * "PushType": 1
	 * }
	 */
	private String				id;											// 数据的guid
	private int					entityType;									// 数据类型[bill、invoice...]
	private String				number;										// 审批者的guid
	private int					pushType;									// 1、审批；...今后有再加别的
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public int getEntityType()
	{
		return entityType;
	}
	public void setEntityType(int entityType)
	{
		this.entityType = entityType;
	}
	public String getNumber()
	{
		return number;
	}
	public void setNumber(String number)
	{
		this.number = number;
	}
	public int getPushType()
	{
		return pushType;
	}
	public void setPushType(int pushType)
	{
		this.pushType = pushType;
	}
	@Override
	public String toString()
	{
		return "PushBean [id=" + id + ", entityType=" + entityType + ", number=" + number + ", pushType=" + pushType + "]";
	}
	
}
