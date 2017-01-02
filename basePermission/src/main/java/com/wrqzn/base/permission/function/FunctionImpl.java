package com.wrqzn.base.permission.function;

import com.wrqzn.base.db.bean.QueryParam;
import com.wrqzn.base.db.bean.QueryResult;
import com.wrqzn.base.db.biz.BaseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/2/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class FunctionImpl implements FunctionBiz {
	@Override
	public QueryResult findAll() {
		return null;
	}

	@Override
	public Object findOne(Integer id) {
		return null;
	}

	@Override
	public Integer save(Map<String, Object> param) {
		return null;
	}

	@Override
	public void save(List<Map<String, Object>> params) {

	}

	@Override
	public void delete(Map<String, Object> param) {

	}

	@Override
	public List<Integer> userFunctions(Long userId) {
		QueryParam queryParam = new QueryParam();
		queryParam.addSql(" select t1.function_id");
		queryParam.addSql(" from function_range t1");
		queryParam.addSql(" left join role t2");
		queryParam.addSql(" on t1.access_id = t2.id");
		queryParam.addSql(" and t1.access_type = 2");
		queryParam.addSql(" left join user_role t3");
		queryParam.addSql(" on t2.id = t3.role_id");
		queryParam.addSql(" where t3.user_id = ?");
		queryParam.addSql(" or (t1.access_type =1 and access_id = ?)");
		queryParam.addParam(userId);
		queryParam.addParam(userId);
		QueryResult result = BaseQuery.run(queryParam);
		if (result.notNull()) {
			List<Integer>  list = new ArrayList<>();
			for (int i = 0; i < result.size() ; i++) {
				list.add(Integer.valueOf(result.get(i,"function_id").toString()));
			}
			return list;
		}else {
			return null;
		}
	}

	@Override
	public boolean userAccess(Integer functionId, List<Integer> userFunctions) {
		if (null != functionId && userFunctions.contains(functionId) ) {
			return true;
		} else {
			return false;
		}

	}
}
