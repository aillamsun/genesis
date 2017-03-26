package com.flame.core.dao.mybatis;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.io.Serializable;

/**
 * 继承自己的 BaseMapper
 */
public interface BaseMapper<T extends Serializable> extends Mapper<T>,MySqlMapper,ConditionMapper, IdsMapper {

}
