package com.wrqzn.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

	@RequestMapping(value = "/path",method = RequestMethod.GET)
	public String filepath(){
		File file = new File("universal.cfg");
		if (!file.exists()){
			try {
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				fw.write("hello");
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "hello:"+ file.getAbsolutePath();
	}

}
