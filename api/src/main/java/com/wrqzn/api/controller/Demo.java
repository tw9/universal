package com.wrqzn.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WANG, RUIQING on 1/2/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
@RestController
@RequestMapping("/demo")
public class Demo {


	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public String test(){
		return "hello";
	}

}
