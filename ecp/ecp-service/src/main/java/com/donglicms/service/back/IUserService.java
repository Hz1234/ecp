package com.donglicms.service.back;

import java.util.List;

import com.donglicms.entity.User;
import com.donglicms.service.IBaseService;

public interface IUserService extends IBaseService<User, Long> {
	
	/**
	 * @Description 用户登录
	 * @param loginName
	 * @param password
	 * @return
	 */
	public User login(String loginName, String password);  //用户登录
	
	/**
	 * @Description 用户注册
	 * @param loginName
	 * @param password
	 * @return 如果注册成功，则返回0，否则返回非0数
	 */
	public int register(String loginName,String password); //用户注册
	
	public List<User> getByLoginName(String loginName);
}
