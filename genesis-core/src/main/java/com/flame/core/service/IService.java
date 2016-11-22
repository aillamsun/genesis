package com.flame.core.service;

import com.flame.core.model.BaseModel;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by sungang on 2016/10/21.
 */
@Service
public interface IService<T extends BaseModel> {

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    T selectOne(T entity);

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param key
     * @return
     */
    T selectByKey(Object key);

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param entity
     * @return
     */
    List<T> select(T entity);

    /**
     * 查询全部结果，select(null)方法能达到同样的效果
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 根据主键字符串进行查询，类中只有存在一个带有@Id注解的字段
     * ids 如 "1,2,3" 这种形式的字符串
     *
     * @param ids
     * @return
     */
    List<T> selectByIds(String ids);

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param t
     * @return
     */
    int selectCount(T t);

    /**
     * 根据Example条件进行查询
     *
     * @param example
     * @return
     */
    List<T> selectByExample(Example example);

    /**
     * 根据Example条件进行查询总数
     *
     * @param example
     * @return
     */
    int selectCountByExample(Example example);


    /**
     * 根据Condition条件进行查询
     *
     * @param condition
     * @return
     */
    List<T> selectByCondition(Condition condition);

    /**
     * 根据Condition条件进行查询总数
     *
     * @return
     */
    int selectCountByCondition(Condition condition);


    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    int insertSelective(T entity);

    /**
     * MySQL专用
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     * @param entityList
     * @return
     */
    int insertList(List<T> entityList);

    /**
     * MySQL专用
     * 插入数据，限制为实体包含id属性并且必须为自增列，实体配置的主键策略无效
     * @param entity
     * @return
     */
    int insertUseGeneratedKeys(T entity);


    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    int updateByPrimaryKey(T entity);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    int updateByPrimaryKeySelective(T entity);

    /**
     * 根据Example条件更新实体entity包含的全部属性，null值会被更新
     *
     * @param entity
     * @param example
     * @return
     */
    int updateByExample(T entity, Example example);

    /**
     * 根据Example条件更新实体record包含的不是null的属性值
     *
     * @param entity
     * @param example
     * @return
     */
    int updateByExampleSelective(T entity, Example example);

    /**
     * 根据Condition条件更新实体entity包含的全部属性，null值会被更新
     *
     * @param entity
     * @param condition
     * @return
     */
    int updateByCondition(T entity, Condition condition);

    /**
     * 根据Condition条件更新实体entity包含的不是null的属性值
     *
     * @param entity
     * @param condition
     * @return
     */
    int updateByConditionSelective(T entity, Condition condition);

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     *
     * @param entity
     * @return
     */
    int delete(T entity);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param key
     * @return
     */
    int deleteByPrimaryKey(Object key);

    /**
     * 根据主键字符串进行删除，类中只有存在一个带有@Id注解的字段
     * ids 如 "1,2,3" 这种形式的字符串
     *
     * @param ids
     * @return
     */
    int deleteByIds(String ids);

    /**
     * 根据Example条件删除数据
     *
     * @param example
     * @return
     */
    int deleteByExample(Example example);

    /**
     * 根据Condition条件
     *
     * @param condition
     * @return
     */
    int deleteByCondition(Condition condition);
}
