package com.wrqzn.base.permission.function;

import com.wrqzn.base.permission.base.BaseBiz;

import java.util.List;

/**
 * Created by WANG, RUIQING on 1/2/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public interface FunctionBiz extends BaseBiz<Integer> {

	List<Integer>  userFunctions(Long userId);
	boolean userAccess(Integer functionId,List<Integer> userFunctions);

}
