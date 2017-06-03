package com.donglicms.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.donglicms.bean.UserBean;
import com.donglicms.entity.User;
import com.donglicms.service.ICustomerService;
import com.donglicms.service.back.IUserService;

@Controller
@RequestMapping("/old")
public class MainController {

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IUserService userService;

	/**
	 * @param request
	 * @param view
	 * 客户资料主界面
	 */
	@RequestMapping(value="/customer/manager")
	public ModelAndView customerManager(HttpServletRequest request,ModelAndView view){
		HttpSession session=request.getSession();
	    UserBean userbean=(UserBean) session.getAttribute("user");
	    if(userbean!=null){
	    	Long loginId=userbean.getLoginId();
	    	List<User> list=customerService.getCustomerPageInfo(loginId);
	    	view.addObject("results",list);
	    }
	   /* PageBean bean=new PageBean();
    	bean.setPageNum(1);
    	bean.setPageSize(10);
	    List<Customer> list=customerService.selectAll();
    	view.addObject("results",list);*/
	    view.setViewName("/front/customer/customerManager");
		return view;
	}
	
	/**
	 * @param customerId
	 * 客户资料详情
	 */
	@RequestMapping(value="/customer/detail")
	@ResponseBody
	public User customerDetail(Long customerId){
		return customerService.selectByPrimaryKey(customerId);
	}
	
	/**
	 * @param customerId
	 * 客户删除
	 */
	@RequestMapping(value="/customer/delete")
	@ResponseBody
	public boolean customerDelete(Long customerId){
		boolean bool=false;
		customerService.deleteByPrimaryKey(customerId);
		bool=true;
		return bool;
	}
	
	/**
	 * @param customerId
	 * 一键删除
	 */
	@RequestMapping(value="/customer/deleteAll")
	@ResponseBody
	public boolean customerDelete(HttpServletRequest request){
		HttpSession session=request.getSession();
	    UserBean userbean=(UserBean) session.getAttribute("user");
	    boolean bool=false;
	    if(userbean!=null){
	    	Long loginId=userbean.getLoginId();
	    	List<User> list=customerService.getCustomerPageInfo(loginId);
	    	for (int i = 0; i < list.size(); i++) {
	    		customerService.deleteByPrimaryKey((long)list.get(i).getId());
			}
	    	bool=true;
	    }
		return bool;
	}
	
	/**
	 * @param request
	 * @param session
	 * @param customer
	 * 客户资料的新建与修改
	 */
	@RequestMapping(value="/customer/saveOrUpdate")
	@ResponseBody
	public boolean customerSaveOrUpdate(HttpServletRequest request,HttpSession session,User customer){
		boolean bool=false;
		UserBean bean=(UserBean) session.getAttribute("user");
		if(customer.getId()!=null){
			//customer.setUpdateTime(new Date());
			customerService.updateByPrimaryKeySelective(customer);
		}else{
			//customer.setCreateTime(new Date());
			//customer.setLoginId(bean.getLoginId());
			customerService.insertSelective(customer);
		}
		bool=true;
		return bool;
	}
	
	/**
	 * @param request
	 * @param view
	 * 跳转到客户帐号管理页
	 */
	@RequestMapping(value="/user/manager")
	public ModelAndView userManager(HttpServletRequest request,ModelAndView view){
		HttpSession session=request.getSession();
	    UserBean userbean=(UserBean) session.getAttribute("user");
	    if(userbean!=null){
	    	Long loginId=userbean.getLoginId();
	    	List<User> list=customerService.getCustomerPageInfo(loginId);
	    	view.addObject("results",list);
	    }
	    view.setViewName("/front/loginUser/loginUser");
		return view;
	}
	
	/**
	 * @param loginName
	 * 根据登录名的唯一性 查询登录账号信息
	 */
	@RequestMapping(value="/user/search")
	@ResponseBody
	public Map<String,Object> searchByName(String loginName){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("success", false);
		List<User> users=userService.getByLoginName(loginName);
		if(users.size()==1){
			map.put("success", true);
			map.put("loginUser", users.get(0));
		}
		return map;
	}
	
	/**
	 * @param request
	 * @param session
	 * @param user
	 * @param customerId
	 * 保存账户信息   上级修改时只能改账户的邮箱  添加时可以添加用户名跟密码
	 */
	@RequestMapping(value="/user/saveOrUpdate")
	@ResponseBody
	public boolean userSaveOrUpdate(HttpServletRequest request,HttpSession session,User user,Long customerId){
		boolean bool=false;
		
			if(user.getId()!=null){
				User oldUser=userService.selectByPrimaryKey((long)user.getId());
				//oldUser.setEmail(user.getEmail());
				/*String pass=user.getLoginName()+":CNWELL:"+user.getLoginPass();
				oldUser.setLoginPass(DigestUtils.md5Hex(pass.getBytes()).toUpperCase());*/
				userService.updateByPrimaryKeySelective(oldUser);
				bool=true;
			}else{
				String loginName=user.getUsername();
				List<User> list=userService.getByLoginName(loginName);
				if(list.size()>0){
					bool=false;
				}else{
				User cus=customerService.selectByPrimaryKey(customerId);
				String pass=loginName+":CNWELL:"+user.getPassword();
				/*user.setLoginPass(DigestUtils.md5Hex(pass.getBytes()).toUpperCase());
			    user.setCreateTime(new Date());
			    user.setFullName(cus.getCusName());
			    user.setNickName(cus.getCusName());
			    user.setMobile(cus.getMobile());*/
			    userService.insertSelective(user);
			    cus.setUsername(user.getUsername());
			    customerService.updateByPrimaryKeySelective(cus);
			    bool=true;
				}
			}
		
		return bool;
	}
	
	/**
	 * @param view
	 * @param request
	 * 修改客户信息
	 */
	@RequestMapping(value="/user/personInfo")
	public ModelAndView editCustomerInfo(ModelAndView view,HttpServletRequest request){
		HttpSession session=request.getSession();
	    UserBean userbean=(UserBean) session.getAttribute("user");
	    if(userbean!=null){
	    		view.addObject("userbean", userbean);
	    }
	    view.setViewName("/front/loginUser/personInfo");
		return view;
	}
	
	/**
	 * @param view
	 * @param request
	 * @介绍 跳转到修改密码页面
	 */
	@RequestMapping(value="/user/editPass")
	public ModelAndView editUserInfo(ModelAndView view,HttpServletRequest request){
		HttpSession session=request.getSession();
	    UserBean userbean=(UserBean) session.getAttribute("user");
	    if(userbean!=null){
	    		view.addObject("userbean", userbean);
	    }
	    view.setViewName("/front/loginUser/editInfo");
		return view;
	}
    /**
     * @param view
     * @param user
     * 保存修改用户信息
     */
    @RequestMapping(value="/user/editUser")
    @ResponseBody
	public boolean editUser(ModelAndView view,User user,HttpSession session){
    	boolean bool=false;
    	User oldUser=userService.selectByPrimaryKey((long)user.getId());
    	if(user.getPassword()!=null){
    		String pass=oldUser.getUsername()+":CNWELL:"+user.getPassword();
        	user.setPassword(DigestUtils.md5Hex(pass.getBytes()).toUpperCase());
    	}
    	userService.updateByPrimaryKeySelective(user);
    	User newUser=userService.selectByPrimaryKey((long)user.getId());
    	UserBean userBean = new UserBean((long)newUser.getId(), 
    									 newUser.getUsername(), 
    									 newUser.getPassword(), 
    									 newUser.getUsername(), 
    									 newUser.getNickname(), 
    									 newUser.getLinkPhoneNum(), 
    									 newUser.getLinkPhoneNum(), 
    									 new Date());
    	session.setAttribute("user", userBean);
    	bool=true;
		return bool;
	}
    
    /**
     * @return
     * 修改成功后跳转到信息页
     */
    @RequestMapping(value="/user/msg")
    public String successMsg(){
    	return "/front/loginUser/msg";
    }
}
