package com.donglicms.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.donglicms.bean.UserBean;
import com.donglicms.common.SessionConstants;
import com.donglicms.common.StaticConstants;
import com.donglicms.common.util.RequestResultUtil;
import com.donglicms.entity.User;
import com.donglicms.service.IInvitationService;

/**
 * Class: InvitationController
 * 		邀请函Controller类
 * @author srd 
 * @version 1.0 $Date: 2017年3月6日 下午2:16:02
 */
@Controller
@RequestMapping("/old/invitation")
public class InvitationController {

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private IInvitationService iInvitationService;

	/**
	 * 方法功能：查询列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/selectItem")
	public ModelAndView selectLinkItem(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		UserBean user = (UserBean)request.getSession().getAttribute(SessionConstants.USER);
		List<User> invitationList = iInvitationService.selectByLoginid(user.getLoginId());
		mav.addObject("invitationList", invitationList);
		
		mav.setViewName(StaticConstants.INVITATION);
		return mav;
	}
	
	/**
	 * 方法功能：查询要修改的信息
	 * @param request
	 * @param response
	 * @param invitationId
	 * @return
	 */
	@RequestMapping("/selectUpdateById")
	@ResponseBody
	public Map<String, Object> selectUpdateById(HttpServletRequest request, HttpServletResponse response, Long invitationId) {
		try {
			User invitation = iInvitationService.selectByPrimaryKey(invitationId);
			Map<String, Object> respM = RequestResultUtil.getResultSelectSuccess();
			respM.put("invitation", invitation);
			return respM;
		} catch (Exception e) {
			log.error("查询异常", e);
			return RequestResultUtil.getResultSelectWarn();
		}
	}
	
	/**
	 * 方法功能：添加
	 * @param request
	 * @param response
	 * @param invitation
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, Object> insertContent(HttpServletRequest request, HttpServletResponse response, User invitation) {
		
		UserBean user = (UserBean)request.getSession().getAttribute(SessionConstants.USER);
		if(user!=null){
			/*invitation.setLoginId(user.getLoginId());
			invitation.setInvType(2);//邀请函模板类型 1=图片 2=WebH5
			invitation.setCreateTime(new Date());*/
			//invitation.setAddress("test");
			invitation.setStatus(1);
			int rows = iInvitationService.insertSelective(invitation);
			if(rows>0){
				return RequestResultUtil.getResultAddSuccess();
			}
		}
		return RequestResultUtil.getResultAddWarn();
	}
	
	/**
	 * 方法功能：修改
	 * @param request
	 * @param response
	 * @param invitation
	 * @return
	 */
	@RequestMapping("/updateById")
	@ResponseBody
	public Map<String, Object> updateById(HttpServletRequest request, HttpServletResponse response, User invitation) {
		
		try {
			int rows = iInvitationService.updateByPrimaryKeySelective(invitation);
			if(rows>0){
				return RequestResultUtil.getResultUpdateSuccess();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return RequestResultUtil.getResultUpdateWarn();
	}
	
	/**
	 * 方法功能：删除
	 * @param request
	 * @param response
	 * @param invitationId
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public Map<String, Object> deleteById(HttpServletRequest request, HttpServletResponse response, Long invitationId) {
		int rows = iInvitationService.deleteByPrimaryKey(invitationId);
		if(rows>0){
			return RequestResultUtil.getResultDeleteSuccess();
		}
		return RequestResultUtil.getResultDeleteWarn();
	}
	
	/**
	 * 创建邀请函URL（生成邀请函）
	 * @param request
	 * @param response
	 * @param invitationId
	 * @return
	 */
	public Map<String, Object> createInvitationUrl(HttpServletRequest request, HttpServletResponse response, Long invitationId) {
		int rows = iInvitationService.deleteByPrimaryKey(invitationId);
		if(rows>0){
			return RequestResultUtil.getResultDeleteSuccess();
		}
		return RequestResultUtil.getResultDeleteWarn();
	}
	
}
