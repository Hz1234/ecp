package com.donglicms.service.impl.front;

import java.util.List;

import org.springframework.stereotype.Service;

import com.donglicms.bean.SkuPriceBean;
import com.donglicms.dao.SkuMapper;
import com.donglicms.entity.Sku;
import com.donglicms.service.front.ISkuService;
import com.donglicms.service.impl.AbstractBaseService;

@Service
public class SkuServiceImpl extends AbstractBaseService<Sku, Long> implements ISkuService {
	
	SkuMapper skuMapper;
	
	/**
	 * @param mapper
	 * the mapper to set set方式注入
	 */	
	public void setSkuMapper(SkuMapper skuMapper) { 
		this.skuMapper=skuMapper;
		this.setMapper(skuMapper);
	}

	@Override
	public List<SkuPriceBean> getSkuByIdAndType(Long itemId, int skuType) {
		
		return skuMapper.getSkuByIdAndType(itemId,skuType);
		
	}

	@Override
	public List<SkuPriceBean> getSkuByIdAndAttr(Long itemId, String skuAttribute) {
		return skuMapper.getSkuByIdAndAttr(itemId,skuAttribute);
	}

	@Override
	public SkuPriceBean getSkuBySkuId(long skuId) {			
		return skuMapper.getSkuBySkuId(skuId);
		
	}
	

		
	

}
