package com.wrqzn.api.controller;

import com.wrqzn.base.db.bean.QueryParam;
import com.wrqzn.base.db.bean.QueryResult;
import com.wrqzn.base.db.biz.BaseQuery;
import com.wrqzn.base.permission.function.FunctionBiz;
import com.wrqzn.base.permission.function.FunctionImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/2/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
@RestController
@RequestMapping(method = RequestMethod.GET)
public class GetControll {

	@RequestMapping(value = "/{p1}/{p2}")
	public String param2(@RequestParam Map<String,Object> param
			,@PathVariable("p1") String p1
			,@PathVariable("p2") String p2
						 ){
		if (null == param.get("userId")) {
			return "用户ID為空";
		}
		QueryParam queryParam = new QueryParam();
		queryParam.addSql("select ");
		queryParam.addSql(" id ");
		queryParam.addSql(",sql_content ");
		queryParam.addSql(" from function ");
		queryParam.addSql(" where url_path = ? ");
		queryParam.addSql(" and http_type = 'get' ");
		queryParam.addParam( "/"+ p1+ "/"+p2);
		QueryResult result = BaseQuery.run(queryParam);

		Long userId = Long.valueOf(param.get("userId").toString());
		Integer funcId = Integer.valueOf(result.getFirst("id").toString());
		FunctionBiz functionBiz = new FunctionImpl();
		if ( functionBiz.userAccess( funcId , functionBiz.userFunctions(userId) ) ) {
			String sql = result.getFirst().get("sql_content").toString();
			QueryResult data = BaseQuery.run(sql);
			return data.toString();
		} else {
			return "無權限";
		}


	}


}
