package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.ShoppingCartDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCartDaoMapper {
    int deleteByPrimaryKey(Long id);

    Long insertSelective(ShoppingCartDao record);

    ShoppingCartDao selectByPrimaryKey(Long id);

    List<ShoppingCartDao> selectBySelective(ShoppingCartDao record);

    int updateByPrimaryKeySelective(ShoppingCartDao record);

    int batchUpdateByPrimaryKeySelective(ShoppingCartDao record);

    Long countByUid(@Param("uid") Long uid);
}
