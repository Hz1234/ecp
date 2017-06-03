package com.donglicms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.donglicms.common.StaticConstants;
import com.donglicms.entity.User;
import com.donglicms.service.ICustomerService;
import com.donglicms.service.IInvitationService;

/**
 * Class: InvTplController
 * 		邀请函模版Controller类
 * @author srd 
 * @version 1.0 $Date: 2017年3月7日 下午11:16:02
 */
@Controller
public class InvTplController {

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private IInvitationService iInvitationService;
	@Autowired
	private ICustomerService iCustomerService;

	/**
	 * 客户打开邀请函页面
	 * @param request
	 * @param response
	 * @param invitationId
	 * @param customerId
	 * @return
	 */
	@RequestMapping("/invitation-tpl/{invitationId}/{customerId}")
	public ModelAndView goInvitationPage(HttpServletRequest request, HttpServletResponse response, @PathVariable Long invitationId, @PathVariable Long customerId) {
		ModelAndView mav = new ModelAndView();
		
		//UserBean user = (UserBean)request.getSession().getAttribute(SessionConstants.USER);
		//if(user!=null){
			try {
				User invitation = iInvitationService.selectByPrimaryKey(invitationId);
				//String actDate=invitation.getActDate();
				String actDate="test";
				int index=actDate.indexOf("-");
				String end=actDate.substring(index+1, actDate.length());
				end=end.substring(5,end.length());
				actDate=actDate.substring(0, index+1)+end;
				//invitation.setActDate(actDate);
				User customer = iCustomerService.selectByPrimaryKey(customerId);
				log.info("邀请函："+invitation);
				log.info("客户："+customer);
				mav.addObject("invitation", invitation);
				mav.addObject("customer", customer);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		//request.getContextPath();
		mav.setViewName(StaticConstants.INVITATION_TPL_PAGE);
		return mav;
	}
	
}
