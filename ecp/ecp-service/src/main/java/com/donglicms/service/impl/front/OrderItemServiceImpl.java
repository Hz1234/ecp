package com.donglicms.service.impl.front;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.donglicms.bean.AddSkuToOrderBean;
import com.donglicms.dao.OrderItemsMapper;
import com.donglicms.entity.OrderItems;
import com.donglicms.service.front.IOrderItemService;
import com.donglicms.service.impl.AbstractBaseService;

@Service
public class OrderItemServiceImpl extends AbstractBaseService<OrderItems, Long> implements IOrderItemService {
	
	OrderItemsMapper orderItemsMapper; 
	
	/**
	 * @param mapper
	 * the mapper to set set方式注入
	 */	
	public void setOrderItemsMapper(OrderItemsMapper orderItemsMapper) { 
		this.orderItemsMapper=orderItemsMapper;
		this.setMapper(orderItemsMapper);
	}

	@Override
	public void addItemIntoOrder(List<AddSkuToOrderBean> itemList, String orderId) {
		for(AddSkuToOrderBean item:itemList){
			OrderItems record=new OrderItems();
			record.setOrderId(orderId);
			record.setCid(item.getCid());
			record.setItemId(item.getItemId());
			record.setSkuId(item.getSkuId());
			record.setSkuName(item.getSkuName());
			record.setPayPrice(item.getSkuPrice());
			record.setNum(item.getSkuNum());
			record.setCreateTime(new Date());
			
			orderItemsMapper.insert(record);
			
		}
		
	}

	@Override
	public List<Map<String,String>> selectItemsByOrderId(String orderId) {
		
		return orderItemsMapper.selectItemsByOrderId(orderId);
	
	}
	
	

	

	
	
	

}
