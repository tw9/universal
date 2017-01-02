package com.wrqzn.base.permission.user;

import com.wrqzn.base.db.bean.QueryParam;
import com.wrqzn.base.db.biz.BaseQuery;

import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/2/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class UserImpl implements UserBiz {
	@Override
	public List<Map<String, Object>> allUser() {
		return null;
	}

	@Override
	public Map<String, Object> findByPassword(String password) {
		QueryParam sql = new QueryParam( "select id,password from user_password where password = ?" );
		sql.addParam(password);
		return BaseQuery.run(sql).get(0);
	}
}
