package com.wrqzn.base.permission.user;

import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/2/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public interface UserBiz {

	List<Map<String,Object>>  allUser();
	Map<String,Object>  findByPassword(String password);



}
