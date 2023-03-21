package com.flower.xin.orm.impl;

import com.flower.xin.orm.dao.ShoppingCartDao;
import com.flower.xin.orm.mapper.ShoppingCartDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author eiven
 */
@Service
public class ShoppingCartDaoService {
    @Autowired
    private ShoppingCartDaoMapper shoppingCartDaoMapper;


    public List<ShoppingCartDao> getShoppingCartList(ShoppingCartDao dao) {
        return shoppingCartDaoMapper.selectBySelective(dao);
    }

    public ShoppingCartDao getShoppingCart(Long userCouponId) {
        return shoppingCartDaoMapper.selectByPrimaryKey(userCouponId);
    }

    public Long createShoppingCart(ShoppingCartDao dao) {
        return shoppingCartDaoMapper.insertSelective(dao);
    }

    public Integer updateShoppingCart(ShoppingCartDao dao) {
        return shoppingCartDaoMapper.updateByPrimaryKeySelective(dao);
    }

    public Integer batchUpdateShoppingCart(ShoppingCartDao dao) {
        return shoppingCartDaoMapper.batchUpdateByPrimaryKeySelective(dao);
    }

    public Long countByUid(Long uid) {
        return shoppingCartDaoMapper.countByUid(uid);
    }

}
