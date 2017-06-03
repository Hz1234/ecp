package com.donglicms.service.impl.front;

import java.util.List;

import org.springframework.stereotype.Service;

import com.donglicms.dao.ItemPictureMapper;
import com.donglicms.entity.ItemPicture;
import com.donglicms.service.front.IItemPictureService;
import com.donglicms.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ItemPictureServiceImpl extends AbstractBaseService<ItemPicture, Long> implements IItemPictureService {
	
	ItemPictureMapper itemPictureMapper;
	/**
	 * @param mapper
	 * the mapper to set set方式注入
	 */	
	public void setItemPictureMapper(ItemPictureMapper itemPictureMapper) {
		this.itemPictureMapper=itemPictureMapper;
		this.setMapper(itemPictureMapper);
	}
	@Override
	public List<ItemPicture> getItemPictureByItemId(Long itemId){
		//通过一个对象查询
		ItemPicture itemPict=new ItemPicture();
		itemPict.setItemId(itemId);
		return itemPictureMapper.select(itemPict);			
	}		
	

}
