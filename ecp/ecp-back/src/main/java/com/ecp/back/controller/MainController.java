package com.ecp.back.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecp.back.commons.StaticConstants;
import com.ecp.service.IUserService;

@Controller
public class MainController {

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private IUserService userService;

	
	/**
	 * 登录成功进入SCRM系统首页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/back/main")
	public String goScrmMain(HttpServletRequest request, HttpServletResponse response, String error) {
		log.info("进入系统首页："+StaticConstants.MAIN);
		return StaticConstants.MAIN;
	}
	
}