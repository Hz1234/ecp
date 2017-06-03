package com.donglicms.service.impl.front;

import org.springframework.stereotype.Service;

import com.donglicms.dao.AttributeValueMapper;
import com.donglicms.entity.AttributeValue;
import com.donglicms.service.front.IAttrValueService;
import com.donglicms.service.impl.AbstractBaseService;

@Service
public class AttrValueServiceImpl extends AbstractBaseService<AttributeValue, Long> implements IAttrValueService {
	
	AttributeValueMapper attributeValueMapper;
	
	/**
	 * @param mapper
	 * the mapper to set set方式注入
	 */	
	public void setAttributeValueMapper(AttributeValueMapper attributeValueMapper) { 
		this.attributeValueMapper=attributeValueMapper;
		this.setMapper(attributeValueMapper);
	}

	@Override
	public AttributeValue getAttributeValueById(long attrId, long valueId) {
		AttributeValue record=new AttributeValue();
		record.setAttrId(attrId);
		record.setValueId(valueId);
		return attributeValueMapper.selectOne(record);		
	}	


	
	

}
