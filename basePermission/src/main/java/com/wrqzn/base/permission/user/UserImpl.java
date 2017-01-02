package com.wrqzn.base.permission.user;

import com.wrqzn.base.db.bean.QueryParam;
import com.wrqzn.base.db.bean.QueryResult;
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

	@Override
	public String  login(String loginName, String password) {
		QueryParam sql = new QueryParam( "select id,password from user_password where password = ?" );
		sql.addParam(password);
		QueryResult passwd = BaseQuery.run(sql);

		if ( null != passwd  && passwd.getCount() > 0  ) {
			Object passwdId = passwd.get(0).get("id");
			QueryResult loginNames = BaseQuery.run(" select login_name from user_login_name where password_id = " + passwdId);
			boolean loginSuccess = false;

			for (int i = 0; i < loginNames.size(); i++) {
				if ( loginName.equals( loginNames.get(i,"login_name").toString() ) ){
					loginSuccess = true;
					break;
				}
			}

			if (loginSuccess) {
				QueryResult infos =BaseQuery.run(" select user_info from user_info where user_id = " + passwdId );
				if (null != infos && infos.size() >  0 ) {
					return infos.getFirst("user_info").toString();
				} else {
					return "        ";
				}
			}

		}


		return null;
	}

	@Override
	public QueryResult findAll() {
		return BaseQuery.findAll("user_info");
	}

	@Override
	public Object findOne(Long id) {
		return BaseQuery.run( "select user_info from from user_info where user_id = " + id ).getFirst("user_info");
	}

	@Override
	public Long save(Map<String, Object> param) {
		return null;
	}

	@Override
	public void save(List<Map<String, Object>> params) {

	}

	@Override
	public void delete(Map<String, Object> param) {

	}
}
