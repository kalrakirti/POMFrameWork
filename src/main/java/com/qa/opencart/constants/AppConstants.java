package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final String  LOGIN_PAGE_TITLE="Account Login";
	
	public static final String  LOGIN_PAGE_FRACTION_VALUE="route=account/login";

	public static final String  ACCOUNTS_PAGE_TITLE="My Account";

	public static final String  ACCOUNTS_PAGE_FRACTION_VALUE="route=account/account";

	public static final String CONFIG_FILE_PATH ="./src/test/resources/config/config.properties";
	public static final String CONFIG_QA_FILE_PATH ="./src/test/resources/config/config.qa.properties";
	public static final String CONFIG_STAGE_FILE_PATH ="./src/test/resources/config/config.stage.properties";
	public static final String CONFIG_UAT_FILE_PATH ="./src/test/resources/config/config.uat.properties";
	public static final String CONFIG_DEV_FILE_PATH ="./src/test/resources/config/config.dev.properties";

	
	
	
	public static final List<String> accountsPageHeaderList  = Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	
//	public final static

	public static final String  USER_REGISTER_SUCCESS_MSG="Your Account Has Been Created!";
}
