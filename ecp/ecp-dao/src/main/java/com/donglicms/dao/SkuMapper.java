package com.donglicms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.donglicms.bean.SkuPriceBean;
import com.donglicms.entity.Sku;

import tk.mybatis.mapper.common.Mapper;

public interface SkuMapper extends Mapper<Sku> {
	
	List<SkuPriceBean> getSkuByIdAndType(@Param("itemId") Long itemId,@Param("skuType") int skuType);
	
	List<SkuPriceBean> getSkuByIdAndAttr(@Param("itemId") Long itemId,@Param("skuAttribute") String skuAttribute);
	
	public SkuPriceBean getSkuBySkuId(@Param("skuId") long skuId);
	
}