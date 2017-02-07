package com.wrqzn.web;

import com.hzbuvi.util.basic.ValueUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by WANG, RUIQING on 2/6/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
@Controller
public class controller {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {


		model.put("title", "jfkdf");
		model.put("msg", ValueUtil.toJson("6yfi"));

		return "index";
	}
}
