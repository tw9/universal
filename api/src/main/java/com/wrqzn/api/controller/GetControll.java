package com.wrqzn.api.controller;

import com.google.gson.Gson;
import com.wrqzn.api.bean.HttpMethod;
import com.wrqzn.api.bean.SysFunction;
import com.wrqzn.dbrecord.op.QueryResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/2/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
@RestController
@RequestMapping(value = "/u" , method = RequestMethod.GET)
public class GetControll {

	private Gson gson = new Gson();

	@RequestMapping(value = "/{p1}/{p2}")
	public String param2(
			@RequestParam Map<String,Object> param
			,@PathVariable("p1") String p1
			,@PathVariable("p2") String p2
						 ){
		SysFunction function = new SysFunction().findByPath( new String[]{p1,p2}, HttpMethod.get);
		QueryResult result = null;
		if (null != function) {
			result = function.exeSql(param);
		}
		return gson.toJson(result);
	}


}
