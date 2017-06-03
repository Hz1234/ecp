package com.donglicms.web.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.donglicms.common.SessionConstants;
import com.donglicms.entity.User;
import com.donglicms.service.back.IUserService;

/**
 * @ClassName UserEntryController
 * @Description 管理帐户-注册、登录 控制器
 * @author Administrator
 * @Date 2017年5月5日 下午4:34:20
 * @version 1.0.0
 */
@Controller
@RequestMapping("/user")
public class CanDel_UserEntryController {
	final String RESPONSE_THYMELEAF = "thymeleaf/";
	final String RESPONSE_JSP = "jsps/";

	//private final Logger log = Logger.getLogger(getClass());

	@Autowired
	IUserService userService;

	/**
	 * @Description 管理帐户-注册
	 * @param loginName  登录名称
	 * @param password   口令
	 * @return 如果成功注册，则导航到主页，如果输入信息错误则提示用户
	 */
	// TODO 在此处只处理成功注册 后面再处理异常情况
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(String loginName, String password) {
		String md5Password = genMD5Password(loginName, password);
		userService.register(loginName, md5Password);
		return RESPONSE_THYMELEAF + "home";
	}

	/**
	 * @Description 管理帐户-登录
	 * @param request
	 * @param loginName
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, String loginName, String password) {
		User user = verifyUser(loginName, password);
		if (user!=null) { // 如果校难正确
			//将用户信息加入到session
			HttpSession session = request.getSession();    		
    		session.setAttribute(SessionConstants.USER, user);
			return RESPONSE_THYMELEAF + "home"; // 导航到主页
		} else {
			return "error"; // TODO 此处暂时未处理
		}

	}

	/**
	 * @Description 验证用户名和密码
	 * @param loginName
	 * @param password
	 * @return User:如果验证成功  null:验证不成功
	 */
	private User verifyUser(String loginName, String password) {
		
		if (StringUtils.isNotBlank(loginName) && StringUtils.isNotBlank(password)) {
			String md5Password = genMD5Password(loginName, password);
			User user = userService.login(loginName, md5Password);
			if (user != null) {
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}
		
	}

	/**
	 * @Description 根据用户名及口令生成MD5加密口令
	 * @param loginName
	 * @param password
	 * @return 生成MD5的加密口令
	 */
	private String genMD5Password(String loginName, String password) {
		// user_pass加密规则：UPPER(MD5(CONCAT(user_name,":CNWELL:",user_pass)))
		String pass = loginName + ":CNWELL:" + password;
		// log.debug("md5 password upper : " +
		// DigestUtils.md5Hex(pass.getBytes()).toUpperCase());
		String md5Password = DigestUtils.md5Hex(pass.getBytes()).toUpperCase();
		return md5Password;
	}

}
