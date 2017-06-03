package com.donglicms.service.impl.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donglicms.dao.UserMapper;
import com.donglicms.entity.User;
import com.donglicms.service.back.IUserService;
import com.donglicms.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl extends AbstractBaseService<User, Long> implements IUserService {

	//private UserMapper userMapper;  //已经自抽象类继承了变更此处不必再声明一个变量

	/**
	 * @param userMapper the userMapper to set
	 * set方式注入
	 */
	@Autowired
	public void setMapper(UserMapper userMapper) {
		//this.userMapper = userMapper;
		super.setMapper(userMapper);
	}

	/** 
	 * @author: srd
	 * @see com.donglicms.service.IUserService#login(java.lang.String, java.lang.String)
	 * 登录验证
	 */	
	@Override
	public User login(String loginName, String password) {
		User user = new User();
		user.setUsername(loginName);
		user.setPassword(password);
		List<User> userList = mapper.select(user);
		if(userList!=null && userList.size()>0){
			return userList.get(0);
		}
		return null;
	}
	

	@Override
	public List<User> getByLoginName(String loginName) {
		Example example=new Example(User.class);
		example.createCriteria().andEqualTo("loginName", loginName);
		List<User> list=mapper.selectByExample(example);
		return list;
	}

	@Override
	public int register(String loginName, String password) {
		User regUser=new User();
		regUser.setUsername(loginName);
		regUser.setPassword(password);
		mapper.insert(regUser);
		return 0;
	}

}
