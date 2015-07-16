package com.guanplus.utils;


/**
 * @author Scleo
 * 
 */
public abstract class ContentValue
{
	public static final int			SOCKET_TIMEOUT						= 10000;
	public static final int			ERROR_SOCKET_TIMEOUT				= 10000;

	/****** 24小时毫秒? ********/
	public static final long		DAYTIME								= 86400000;
	// Sp文件中记录是否已登录
	public static final String		SPFILE_TOKEN						= "auth_token";
	public static final String		SPFILE_PIN							= "pincode";
	public static final String		SPFILE_PIN_ERRCOUNT					= "pinErrCount";
	public static final String		SPFILE_PIN_TOGGLE					= "pinToggle";
	public static final String		SPFILE_USERNAME						= "username";
	public static final String		SPFILE_PIN_SERVICE_START			= "pinServiceStart";
	/****** SP文件用户参数 *********/
	public static final String		USER_CONFIG_ID						= "user.id";
	public static final String		USER_CONFIG_ISLOGIN					= "user.isLogin";
	public static final String		USER_CONFIG_PASSWORD				= "user.password";
	public static final String		USER_CONFIG_ROLES					= "user.roles";
	public static final String		USER_CONFIG_TOKEN					= "user.token";
	public static final String		USER_CONFIG_NAME					= "user.displayname";
	public static final String		USER_CONFIG_PHONE					= "user.phone";
	public static final String		USER_CONFIG_EMAIL					= "user.email";
	public static final String		USER_CONFIG_LASTLOGINTIME			= "user.logintime";
	public static final String		USER_CONFIG_SECURITY				= "user.security";
	public static final String		USER_CONFIG_ISFIRSTLOGIN			= "user.isFirstLogin";
	public static final String		USER_CONFIG_AVATAR					= "user.avatar";
	/****** 通用Intent参数 *******/
	public static final String		INTENT_ENTITY_TYPE					= "entityType";
	public static final String		INTENT_ID							= "id";
	public static final String		INTENT_ACTION						= "action";

	/****** 全局UTF-8编码 ********/
	public static String			ENCODING							= "utf-8";

	/***************** http参数 ***********************/
	public static final String		AUTHORIZATION						= "authorization";
	public static final String		CONTENT_TYPE						= "Content-Type";
	public static final String		ACCEPT_TYPE							= "Accept";

	/*********** http头参数 ******************/
	public static final String		APPLICATION_JSON					= "application/json";
	public static final String		APPLICATION_FORM					= "application/x-www-form-urlencoded";
	public static final String		APPLICATION_XML						= "application/xml";
	/*********** 服务器返回状态 *****************/
	public static final String		ERROR_MSG							= "error";
	/*************** 网络状态 ***********/
	public static int				NO_NETWORK							= 0;
	public static int				WIFI_STATE							= 1;
	public static int				CNWAP_STATE							= 2;
	public static int				CMNET_STATE							= 0;

	/** ---采购单 */
	public static final int			TYPE_BILL							= 0x000;
	/** ---销售单 */
	public static final int			TYPE_INVOICE						= 0x001;
	/** ---报销单 */
	public static final int			TYPE_REIMBURSEMENT					= 0x002;
	/** ---备用金 */
	public static final int			TYPE_PETTYCASH						= 0x003;
	/** ---预付�? */
	public static final int			TYPE_PAYMENT						= 0x004;
	/** ---type凭证 **/
	public static final int			TYPE_VOUCHER						= 0x005;

	/******* 业务单据状�?? *********************/
	public static final int			STATUS_DRAFT						= 0;
	public static final int			STATUS_SUBMITED						= 1;
	public static final int			STATUS_CLASSIED						= 2;
	public static final int			STATUS_APPROVED						= 3;
	public static final int			STATUS_DEFENIED						= 4;
	public static final int			STATUS_PAID							= 5;

	/******* 角色权限 ************************/
	public static final int			ROLES_NORMAL						= 0;
	public static final int			ROLES_ACCONTANT						= 1;
	public static final int			ROLES_CASHIER						= 2;
	public static final int			ROLES_MANAGER						= 3;
	public static final int			ROLES_CEO							= 4;

	/******* TodoListTypes ************************/
	/** TodoListTypes--�?般情�? */
	public static final int			TODOLISTTYPES_GENERAL				= 0;
	/** TodoListTypes--待提�? */
	public static final int			TODOLISTTYPES_INDRAF				= 1;
	/** TodoListTypes--待分�? */
	public static final int			TODOLISTTYPES_INCATEGORY			= 2;
	/** TodoListTypes--待确�? */
	public static final int			TODOLISTTYPES_INCONFIRMED			= 3;
	/** TodoListTypes--待支�? */
	public static final int			TODOLISTTYPES_INPAY					= 4;
	/** TodoListTypes--待审�? */
	public static final int			TODOLISTTYPES_INAPPORVAL			= 5;
	/** TodoListTypes--审批拒绝 */
	public static final int			TODOLISTTYPES_INDENIED				= 6;




}
