package com.donglicms.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.donglicms.bean.UserBean;
import com.donglicms.common.StaticConstants;
import com.donglicms.common.util.FileUploadUtil;
import com.donglicms.entity.User;
import com.donglicms.service.ICustomerService;
import com.donglicms.service.excelutil.Common;

@Controller
@RequestMapping("/old/import")
public class ImportController {

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ICustomerService customerService;

	/**
	 * 加载导入客户信息页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/goImportPage")
    public ModelAndView goImportPage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(StaticConstants.IMPORT_EXCEL_PAGE);
		return mav;

    }
	
	/**
	 * 导入excel（上传excel文件）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/excel/importExcel")
	@ResponseBody
    public Map<String, Object> uploadCheckinInfo(HttpServletRequest request, HttpServletResponse response){
		UserBean user=(UserBean) request.getSession().getAttribute("user");
		Map<String, Object> respMap = new HashMap<String, Object>();
		
		Map<String, Object> reqMap = new HashMap<String, Object>();
		//上传excel文件
		String uploadPath = customerService.uploadExcelFile(request);
		
		String realPath = FileUploadUtil.getRealPath(request);
		if(StringUtils.isBlank(uploadPath)){
			respMap.put("result_code", "fail");
			respMap.put("result_err_msg", "上传excel文件异常！");
			return respMap;
		}
		reqMap.put(Common.EXCEL_PATH, realPath + uploadPath);
		
    	try {
			return this.parseExcel(user.getLoginId(), reqMap);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("解析excel异常", e);
		}

    	respMap.put("result_code", "fail");
		respMap.put("result_err_msg", "导入excel文件异常！");
		return respMap;
    }
	
	/**
	 * 导入excel（解析excel并保存excel信息到数据库）
	 * @param loginId
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> parseExcel(Long loginId, Map<String, Object> reqMap) throws Exception{
		Map<String, Object> respMap = new HashMap<String, Object>();
		//解析excel文件
		Map<String, Object> resMap = customerService.parseUploadExcelFile(reqMap);
		if(resMap!=null && !resMap.get("result_code").equals("success")){
			return resMap;
		}
		String customerListListJson = resMap.get("customerList").toString();
		
		if(StringUtils.isNotBlank(customerListListJson)){
			//保存excel文件内容到数据库
			respMap = customerService.saveExcelInfoToDB(loginId, customerListListJson);
			String uploadExcelPath = reqMap.get(Common.EXCEL_PATH).toString();
			log.info("上传Excel文件的绝对路径："+uploadExcelPath);
			respMap.put("uploadExcelPath", uploadExcelPath);
			return respMap;
		}
		respMap.put("result_code", "fail");
		respMap.put("result_err_msg", "保存excel文件内容异常！");
		
		return respMap;
	}
	
	/**
	 * 加载客户信息列表页面（导入客户信息成功后加载导入客户信息列表页面）
	 * @param request
	 * @param response
	 * @param uploadExcelPath
	 * @return
	 */
	@RequestMapping(value = "/goImportExcelTable")
	public ModelAndView goImportExcelTable(HttpServletRequest request, HttpServletResponse response, String uploadExcelPath){
		UserBean user=(UserBean) request.getSession().getAttribute("user");
		
		ModelAndView mav = new ModelAndView();
		if(StringUtils.isNotBlank(uploadExcelPath)){
			Map<String, Object> reqMap = new HashMap<String, Object>();
			reqMap.put(Common.EXCEL_PATH, uploadExcelPath);
			
			//解析excel文件
			Map<String, Object> resMap = customerService.parseUploadExcelFile(reqMap);
			/*if(resMap!=null && !resMap.get("result_code").equals("success")){
				return resMap;
			}*/
			String customerListListJson = resMap.get("customerList").toString();
			List<User> customerList = JSON.parseArray(customerListListJson, User.class); 
			
			mav.addObject("customerList", customerList);
		}
		
		mav.setViewName(StaticConstants.IMPORT_EXCEL_ITEM_PAGE);
		return mav;
    }
}
