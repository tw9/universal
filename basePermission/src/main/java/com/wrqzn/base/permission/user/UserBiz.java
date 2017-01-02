package com.wrqzn.base.permission.user;


import com.wrqzn.base.permission.base.BaseBiz;

import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/2/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public interface UserBiz extends BaseBiz<Long> {

	List<Map<String,Object>>  allUser();
	Map<String,Object>  findByPassword(String password);

	String login(String loginName,String password);





}
