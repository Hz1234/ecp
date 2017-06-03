package com.donglicms.service.impl;

import java.util.List;

import com.donglicms.service.IBaseService;

import tk.mybatis.mapper.common.Mapper;

/**
 * Created by srd on 2017/02/21.
 * @ClassName AbstractBaseService
 * @Description 数据库存取公共泛型类
 * @author Administrator
 * @Date 2017年5月5日 下午3:07:36
 * @version 1.0.0
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractBaseService<T,ID> implements IBaseService<T,ID> {

    protected Mapper<T> mapper;

    public void setMapper(Mapper<T> mapper){
        this.mapper=mapper;
    }

    /**
     * @author: srd $Date: 2017年2月21日
     * @see com.donglicms.service.IBaseService#insertSelective(java.lang.Object)
     * 添加实体
     */
    @Override
    public int insertSelective(T entity) {
       return mapper.insertSelective(entity);
    }
    
    /**
     * @author: srd $Date: 2017年2月21日
     * @see com.donglicms.service.IBaseService#selectAll()
     * 查询所有
     */
    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }
    
    /**
     * @author: srd $Date: 2017年2月21日
     * @see com.donglicms.service.IBaseService#selectByPrimaryKey(java.lang.Object)
     * 根据主键查询实体
     */
    @Override
    public T selectByPrimaryKey(ID id) {
        return mapper.selectByPrimaryKey(id);
    }
    
    /**
     * @author: srd $Date: 2017年2月21日
     * @see com.donglicms.service.IBaseService#updateByPrimaryKeySelective(java.lang.Object)
     * 根据主键修改
     */
    @Override
    public int updateByPrimaryKeySelective(T entity) {
      return mapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * @author: srd $Date: 2017年2月21日
     * @see com.donglicms.service.IBaseService#deleteByPrimaryKey(java.lang.Object)
     * 根据主键删除
     */
    @Override
    public int deleteByPrimaryKey(ID id) {
        return mapper.deleteByPrimaryKey(id);
    }
    
    /**
     * @author: srd $Date: 2017年2月21日
     * @see com.donglicms.service.IBaseService#delete(java.lang.Object)
     * 根据实体类删除
     */
    @Override
    public int delete(T entity) {
        return mapper.delete(entity);
    }
    
}
