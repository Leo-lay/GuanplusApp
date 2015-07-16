package com.guanplus.bean;


/***
 * 接口地址
 * 
 * @author scleo@concordya.com
 */
public class URLs
{
	/** 服务器地址 **/
	public static String	HTTP					= "http://";
	public static String	HTTPS					= "https://";
	public static String	HOST					= "www.concordyaapi-test.chinacloudsites.cn";
	public static String	SERVER_URL				= "concordyaapi-test.chinacloudsites.cn";
	/**
	 * public static String HOST = "www.concordyaapi-test.chinacloudsites.cn";
	 * public static String SERVER_URL = "concordyaapi-test.chinacloudsites.cn";
	 * */
	public static String	SERVER_URL_API			= HTTP + SERVER_URL;
	/****** API地址 **********/
	/** ---采购单APi */
	public static String	BILL					= SERVER_URL_API + "/api/bill";
	/** ---采购单APi */
	public static String	CATEGORY				= SERVER_URL_API + "/api/category";
	/** ---供应商Api */
	public static String	VENDOR					= SERVER_URL_API + "/api/vendor";
	/** ---短信验证码验证APi */
	public static String	VERICAL_REQUEST			= SERVER_URL_API + "/api/verifycode";
	/** ---获取短信验证码 */
	public static String	VERICAL_PHONE			= SERVER_URL_API + "/api/phone";
	/** ---校验tokenAPi */
	public static String	TOKEN					= SERVER_URL_API + "/api/token";
	/** ---注册APi */
	public static String	REGIST					= SERVER_URL_API + "/api/account/register";
	/** ---登录APi */
	public static String	LOGIN					= SERVER_URL_API + "/api/account/login";
	/** ---获取用户信息APi */
	public static String	USER					= SERVER_URL_API + "/api/account/user";
	/** ---获取凭证APi */
	public static String	VOVHER					= SERVER_URL_API + "/api/accountvoucher/";
	/** ---确认凭证APi */
	public static String	VOVHER_CONFIRM			= SERVER_URL_API + "/api/accountvoucher/confirm";
	/** ---拒绝凭证APi */
	public static String	VOVHER_DENIED			= SERVER_URL_API + "/api/accountvoucher/denied";

	/** ---获取总经理APi */
	public static String	APPROVER_GET_CEO		= SERVER_URL_API + "/api/account/userlist/boss/nopaging";
	/** ---获取出纳APi */
	public static String	APPROVER_GET_CASHIER	= SERVER_URL_API + "/api/account/userlist/cashier/nopaging";
	/** ---获取会计APi */
	public static String	APPROVER_GET_ACCOUNTANT	= SERVER_URL_API + "/api/account/userlist/accountant/nopaging";

	/** ---审批通过APi */
	public static String	APPROVER_GET			= SERVER_URL_API + "/api/approve/batch/";
	public static String	APPROVER_POST			= SERVER_URL_API + "/api/approve/batch/";
	/** ---报销单APi */
	public static String	REIMBURSEMENT_GET		= SERVER_URL_API + "/api/reimbursement";
	/** ---获取报销单单号最大ID APi */
	public static String	REIMBURSEMENT_MAXID		= SERVER_URL_API + "/api/metamaxid?type=2";
	/** ---获取业务单据单号最大ID APi */
	public static String	GET_MAXID				= SERVER_URL_API + "/api/metamaxid";
	/** ---获取图表数据APi */
	public static String	DASHBOARD_CHART			= SERVER_URL_API + "/api/chart";
	/** ---获取待办事项APi */
	public static String	DASHBOARD_TODO			= SERVER_URL_API + "/api/todolist";
	/** ---销售单APi */
	public static String	INVOICE_DELETE			= SERVER_URL_API + "/api/invoice/";
	/** ---采购单APi */
	public static String	BILL_GET				= SERVER_URL_API + "/api/bill/";
	/** ---上传附件APi */
	public static String	UPLOAD_ATTACHMENT		= SERVER_URL_API + "/api/attachment/";
	/** ---修改密码APi */
	public static String	USER_PASSWORD_MOTIFY	= SERVER_URL_API + "/api/account/changepassword";
	/** ---提醒审批APi */
	public static String	APPROVER_NOTIFY			= SERVER_URL_API + "/api/approve/notify";
	/** ---预付款APi */
	public static String	ADVANCEPAYMENT			= SERVER_URL_API + "/api/advancePayment";
	/** ---备用金APi */
	public static String	PETTYCASH				= SERVER_URL_API + "/api/pettycash";
	/** ---？？？APi */
	public static String	BUDGET_GET				= SERVER_URL_API + "/api/todolist/list";
	/** ---获取供应商APi */
	public static String	CONTACT_NOPAGING		= SERVER_URL_API + "/api/contact/nopaging?contactType=1";		// 获取供应商(选择预付单位)
}
