package com.donglicms.service.impl.front;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.donglicms.dao.OrdersMapper;
import com.donglicms.entity.Orders;
import com.donglicms.service.front.IOrderService;
import com.donglicms.service.impl.AbstractBaseService;

@Service
public class OrderServiceImpl extends AbstractBaseService<Orders, Long> implements IOrderService {
	
	OrdersMapper ordersMapper; 
	
	/**
	 * @param mapper
	 * the mapper to set set方式注入
	 */	
	public void setOrdersMapper(OrdersMapper ordersMapper) { 
		this.ordersMapper=ordersMapper;
		this.setMapper(ordersMapper);
	}

	@Override
	public void createNewOrder(long buyerId, String buyerName, String orderId) {
		Orders record=new Orders();
		record.setBuyerId(buyerId);
		record.setName(buyerName);
		record.setOrderId(orderId);
		record.setCreateTime(new Date());
		
		ordersMapper.insert(record);		
	}

	@Override
	public List<Orders> selectOrderByUserId(long buyerId) {
		Orders record=new Orders();
		record.setBuyerId(buyerId);
		
		return ordersMapper.select(record);
		
	}
	
	
	

	
	
	

}
