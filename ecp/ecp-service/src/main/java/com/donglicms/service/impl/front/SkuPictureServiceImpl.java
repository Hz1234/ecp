package com.donglicms.service.impl.front;

import java.util.List;

import org.springframework.stereotype.Service;

import com.donglicms.dao.SkuPictureMapper;
import com.donglicms.entity.SkuPicture;
import com.donglicms.service.front.ISkuPictureService;
import com.donglicms.service.impl.AbstractBaseService;

@Service
public class SkuPictureServiceImpl extends AbstractBaseService<SkuPicture, Long> implements ISkuPictureService {

	SkuPictureMapper skuPictureMapper;
	
	/**
	 * @param mapper
	 * the mapper to set set方式注入
	 */	
	public void setSkuPictureMapper(SkuPictureMapper skuPictureMapper) {
		this.skuPictureMapper=skuPictureMapper;
		this.setMapper(skuPictureMapper);
	}
	
	@Override
	public List<SkuPicture> getSkuPictureById(Long skuId) {
		SkuPicture record=new SkuPicture();
		record.setSkuId(skuId);
		return skuPictureMapper.select(record);		
	}

	

	

}
