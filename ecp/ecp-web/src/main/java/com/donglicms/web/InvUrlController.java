package com.donglicms.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.donglicms.bean.RequestServerUrlBean;
import com.donglicms.bean.UserBean;
import com.donglicms.common.SessionConstants;
import com.donglicms.common.StaticConstants;
import com.donglicms.common.util.FileUploadUtil;
import com.donglicms.entity.User;
import com.donglicms.service.ICustomerService;
import com.donglicms.service.IInvitationService;

/**
 * Class: InvUrlController
 * 		邀请函URLController类
 * @author srd 
 * @version 1.0 $Date: 2017年3月6日 下午2:16:02
 */
@Controller
@RequestMapping("/old/invUrl")
public class InvUrlController {

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private IInvitationService iInvitationService;
	@Autowired
	private ICustomerService iCustomerService;

	/**
	 * 进入生成邀请函页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goPage")
	public ModelAndView goPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		UserBean user = (UserBean)request.getSession().getAttribute(SessionConstants.USER);
		List<User> invitationList = iInvitationService.selectByLoginid(user.getLoginId());
		mav.addObject("invitationList", invitationList);
		
		mav.setViewName(StaticConstants.INVITATION_URL_PAGE);
		return mav;
	}
	
	/**
	 * 创建邀请函URL（生成邀请函链接）
	 * @param request
	 * @param response
	 * @param invitationId
	 * @return
	 */
	@RequestMapping("/createInvitationUrl")
	public ModelAndView createInvitationUrl(HttpServletRequest request, HttpServletResponse response, Long invitationId) {
		ModelAndView mav = new ModelAndView();
		UserBean user = (UserBean)request.getSession().getAttribute(SessionConstants.USER);
		if(user!=null){
			List<RequestServerUrlBean> urlBeanList = new ArrayList<RequestServerUrlBean>();
			try {
				String serverUrl = FileUploadUtil.getReqServerURL(request);
				//String serverUrl = "http:";
				log.info("URL:"+serverUrl);
				//String relativeUrl = File.separator + "invitation-tpl" + File.separator + invitationId;
				String relativeUrl = "/invitation-tpl/" + invitationId;
				log.info("relativeUrl:"+relativeUrl);
				List<User> customerList = iCustomerService.getCustomerPageInfo(user.getLoginId());
				for(User customer : customerList){
					//String invitationUrl = serverUrl + relativeUrl + File.separator + customer.getCustomerId();
					String info="尊敬的"+customer.getNickname()+"：  品味新中国第一株赤霞珠积累的芬芳，体会亚洲最大地下酒窖沉淀的时光，2017春季成都糖酒会恭候您的莅临。邀请函请点击";
					String invitationUrl = info+serverUrl + relativeUrl + "/" + customer.getId();
					urlBeanList.add(new RequestServerUrlBean(getUrlId(), user.getLoginId(),customer.getUsername(), invitationUrl));
				}
				mav.addObject("urlBeanList", urlBeanList);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("创建客户邀请函异常", e);
			}
		}
		
		mav.setViewName(StaticConstants.INVITATION_URL_PAGE_TABLE);
		return mav;
	}
	
	/**
	 * 获取urlId
	 * @return
	 */
	private Long getUrlId(){
		return System.currentTimeMillis() + (new Random().nextInt(89999) + 10000);
	}
	
}
