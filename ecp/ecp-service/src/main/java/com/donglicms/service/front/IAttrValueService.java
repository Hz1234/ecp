package com.donglicms.service.front;

import com.donglicms.entity.AttributeValue;
import com.donglicms.service.IBaseService;

public interface IAttrValueService extends IBaseService<AttributeValue, Long> {	
	
	
	/**
	 * @Description 根据属性ID，与属性值ID读取
	 * @param attrId
	 * @param valueId
	 * @return
	 */
	public AttributeValue getAttributeValueById(long attrId,long valueId);
	
}
