package com.donglicms.service.front;

import java.util.List;

import com.donglicms.entity.ItemPicture;
import com.donglicms.service.IBaseService;

public interface IItemPictureService extends IBaseService<ItemPicture, Long> {	
	
	/**
	 * @Description 通过SPU  id  获取SPU图片列表
	 * @param itemId
	 * @return
	 */
	public List<ItemPicture> getItemPictureByItemId(Long itemId);
	
}
