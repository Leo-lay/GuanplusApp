package com.guanplus.bean;

import java.io.Serializable;

public class User implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7736332846897090100L;

	private String				id;
	private String				displayName;
	private String				email;
	private boolean				emailConfirmed;
	private String				phoneNumber;
	private boolean				phoneNumberConfirmed;
	private boolean				twoFactorEnabled;
	private String				currentCompanyId;
	private boolean				disabled;
	private String				roles;
	private String				accessToken;
	private String				expires;
	private String 				accountPhotoUrl;
	// 本地使用数据，服务器不返回
	private String				password;
	private String				confirmPassword;

	
	
	
	public String getAccountPhotoUrl()
	{
		return accountPhotoUrl;
	}

	public void setAccountPhotoUrl(String accountPhotoUrl)
	{
		this.accountPhotoUrl = accountPhotoUrl;
	}

	/**
	 * 获取id
	 * 
	 * @return id id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * 设置id
	 * 
	 * @param id id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * 获取displayName
	 * 
	 * @return displayName displayName
	 */
	public String getDisplayName()
	{
		return displayName;
	}

	/**
	 * 设置displayName
	 * 
	 * @param displayName displayName
	 */
	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	/**
	 * 获取roles
	 * 
	 * @return roles roles
	 */
	public String getRoles()
	{
		return roles;
	}

	/**
	 * 设置roles
	 * 
	 * @param roles roles
	 */
	public void setRoles(String roles)
	{
		this.roles = roles;
	}

	/**
	 * 获取email
	 * 
	 * @return email email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * 设置email
	 * 
	 * @param email email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * 获取emailConfirmed
	 * 
	 * @return emailConfirmed emailConfirmed
	 */
	public boolean isEmailConfirmed()
	{
		return emailConfirmed;
	}

	/**
	 * 设置emailConfirmed
	 * 
	 * @param emailConfirmed emailConfirmed
	 */
	public void setEmailConfirmed(boolean emailConfirmed)
	{
		this.emailConfirmed = emailConfirmed;
	}

	/**
	 * 获取phoneNumber
	 * 
	 * @return phoneNumber phoneNumber
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	/**
	 * 设置phoneNumber
	 * 
	 * @param phoneNumber phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 获取phoneNumberConfirmed
	 * 
	 * @return phoneNumberConfirmed phoneNumberConfirmed
	 */
	public boolean isPhoneNumberConfirmed()
	{
		return phoneNumberConfirmed;
	}

	/**
	 * 设置phoneNumberConfirmed
	 * 
	 * @param phoneNumberConfirmed phoneNumberConfirmed
	 */
	public void setPhoneNumberConfirmed(boolean phoneNumberConfirmed)
	{
		this.phoneNumberConfirmed = phoneNumberConfirmed;
	}

	/**
	 * 获取twoFactorEnabled
	 * 
	 * @return twoFactorEnabled twoFactorEnabled
	 */
	public boolean isTwoFactorEnabled()
	{
		return twoFactorEnabled;
	}

	/**
	 * 设置twoFactorEnabled
	 * 
	 * @param twoFactorEnabled twoFactorEnabled
	 */
	public void setTwoFactorEnabled(boolean twoFactorEnabled)
	{
		this.twoFactorEnabled = twoFactorEnabled;
	}

	/**
	 * 获取currentCompanyId
	 * 
	 * @return currentCompanyId currentCompanyId
	 */
	public String getCurrentCompanyId()
	{
		return currentCompanyId;
	}

	/**
	 * 设置currentCompanyId
	 * 
	 * @param currentCompanyId currentCompanyId
	 */
	public void setCurrentCompanyId(String currentCompanyId)
	{
		this.currentCompanyId = currentCompanyId;
	}

	/**
	 * 获取disabled
	 * 
	 * @return disabled disabled
	 */
	public boolean isDisabled()
	{
		return disabled;
	}

	/**
	 * 设置disabled
	 * 
	 * @param disabled disabled
	 */
	public void setDisabled(boolean disabled)
	{
		this.disabled = disabled;
	}

	/**
	 * 获取accessToken
	 * 
	 * @return accessToken accessToken
	 */
	public String getAccessToken()
	{
		return accessToken;
	}

	/**
	 * 设置accessToken
	 * 
	 * @param accessToken accessToken
	 */
	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}

	/**
	 * 获取expires
	 * 
	 * @return expires expires
	 */
	public String getExpires()
	{
		return expires;
	}

	/**
	 * 设置expires
	 * 
	 * @param expires expires
	 */
	public void setExpires(String expires)
	{
		this.expires = expires;
	}

	/**
	 * 获取password
	 * 
	 * @return password password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * 设置password
	 * 
	 * @param password password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * 获取confirmPassword
	 * 
	 * @return confirmPassword confirmPassword
	 */
	public String getConfirmPassword()
	{
		return confirmPassword;
	}

	/**
	 * 设置confirmPassword
	 * 
	 * @param confirmPassword confirmPassword
	 */
	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", displayName=" + displayName + ", roles=" + roles + ", email=" + email + ", emailConfirmed=" + emailConfirmed
				+ ", phoneNumber=" + phoneNumber + ", phoneNumberConfirmed=" + phoneNumberConfirmed + ", twoFactorEnabled=" + twoFactorEnabled
				+ ", currentCompanyId=" + currentCompanyId + ", disabled=" + disabled + ", accessToken=" + accessToken + ", expires=" + expires
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
	}
	
}
