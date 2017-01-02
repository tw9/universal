package com.wrqzn.api.controller;

import com.wrqzn.base.db.bean.QueryParam;
import com.wrqzn.base.db.biz.BaseQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/2/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
@RestController
public class UniversalControll {

	@RequestMapping(value = "/{p1}/{p2}",method = RequestMethod.GET)
	public String param2(@RequestParam Map<String,Object> param
			,@PathVariable("p1") String p1
			,@PathVariable("p2") String p2
						 ){
		QueryParam queryParam = new QueryParam();
		queryParam.addSql("select ");
		queryParam.addSql(" id ");
		queryParam.addSql(",sql_content ");
		queryParam.addSql(" from function ");
		queryParam.addSql(" where url_path = ? ");
		queryParam.addSql(" and http_type = 'get' ");
		queryParam.addParam( "/"+ p1+ "/"+p2);

		String sql = BaseQuery.run(queryParam).get(0).get("sql_content").toString();

		List<Map<String,Object>> data = BaseQuery.run(sql);

		return data.toString();



	}


}
