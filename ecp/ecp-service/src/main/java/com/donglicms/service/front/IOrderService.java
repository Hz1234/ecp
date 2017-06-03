package com.donglicms.service.front;

import java.util.List;

import com.donglicms.entity.Orders;
import com.donglicms.service.IBaseService;

public interface IOrderService extends IBaseService<Orders, Long> {
	/**
	 * @Description 生成新订单
	 * @param buyerId  买家ID
	 * @param buyerName 买家姓名
	 * @param orderId  订单号
	 */
	public void createNewOrder(long buyerId,String buyerName,String orderId);
	
	/**
	 * @Description 根据登录用户的ID获取订单
	 * @param buyerId
	 * @return
	 */
	public List<Orders>  selectOrderByUserId(long buyerId);
	
	
}
