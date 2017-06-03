package com.donglicms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.donglicms.bean.FavouriteBean;
import com.donglicms.bean.FavouriteStatisticBean;
import com.donglicms.entity.UserFavorite;

import tk.mybatis.mapper.common.Mapper;

public interface UserFavoriteMapper extends Mapper<UserFavorite> {

	public List<FavouriteBean> getFavouritesByUserId(@Param("userId") long userId);

	public List<FavouriteStatisticBean> getFavouriteStatistic(@Param("userId") long userId);

}