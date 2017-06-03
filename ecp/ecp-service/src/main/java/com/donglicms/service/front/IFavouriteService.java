package com.donglicms.service.front;

import java.util.List;

import com.donglicms.bean.FavouriteBean;
import com.donglicms.bean.FavouriteStatisticBean;
import com.donglicms.entity.UserFavorite;
import com.donglicms.service.IBaseService;

public interface IFavouriteService extends IBaseService<UserFavorite, Long> {	
	
	
	/**
	 * @Description 加入到收藏
	 * @param itemId  SPU id 
	 * @param userId  user id
	 */
	public void addToFavourite(long favouriteId,long userId);
	
	/**
	 * @Description 获取指定用户收藏
	 * @param userId
	 * @return
	 */
	public List<FavouriteBean> getFavouritesByUserId(long userId);
	
	/**
	 * @Description 获取指定用户收藏统计信息
	 * @param userId
	 * @return
	 */
	public List<FavouriteStatisticBean> getFavouriteStatistic(long userId);
	
}
