package com.wrqzn.taskflow.db.entity;

/**
 * Created by WANG, RUIQING on 2/21/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class TaskEntity {

	private Integer id;
	private String name;
	private String classPath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
}
