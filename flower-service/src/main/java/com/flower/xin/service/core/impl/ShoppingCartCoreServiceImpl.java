package com.flower.xin.service.core.impl;

import com.flower.xin.common.dto.CommodityDto;
import com.flower.xin.common.dto.ShoppingCartDto;
import com.flower.xin.common.dto.UserCouponDto;
import com.flower.xin.common.enums.ValidateEnum;
import com.flower.xin.common.query.CommodityQuery;
import com.flower.xin.orm.dao.ShoppingCartDao;
import com.flower.xin.orm.impl.ShoppingCartDaoService;
import com.flower.xin.service.core.CommodityCoreService;
import com.flower.xin.service.core.ShoppingCartCoreService;
import com.flower.xin.service.core.UserCouponCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author eiven
 */
@Service
public class ShoppingCartCoreServiceImpl implements ShoppingCartCoreService {

    @Autowired
    private ShoppingCartDaoService shoppingCartDaoService;

    @Override
    public void createShoppingCart(ShoppingCartDto dto) {
        shoppingCartDaoService.createShoppingCart(buildShoppingCartDao(dto));
    }

    @Override
    public void deleteShoppingCart(ShoppingCartDto dto) {
        shoppingCartDaoService.batchUpdateShoppingCart(buildShoppingCartDao(dto));
    }

    @Override
    public List<ShoppingCartDto> getShoppingCartList(ShoppingCartDto dto) {
        List<ShoppingCartDao> shoppingCartDaos = shoppingCartDaoService.getShoppingCartList(buildShoppingCartDao(dto));
        if (CollectionUtils.isEmpty(shoppingCartDaos)) {
            return Collections.EMPTY_LIST;
        }
        return shoppingCartDaos.stream().map(this::buildShoppingCartDto).collect(Collectors.toList());
    }

    @Override
    public void invalidateShoppingCart(ShoppingCartDto dto) {
        dto.setIsValidate(ValidateEnum.INVALIDATE.getCode());
        shoppingCartDaoService.batchUpdateShoppingCart(buildShoppingCartDao(dto));
    }

    @Override
    public void validateShoppingCart(ShoppingCartDto dto) {
        dto.setIsValidate(ValidateEnum.VALIDATE.getCode());
        shoppingCartDaoService.batchUpdateShoppingCart(buildShoppingCartDao(dto));
    }

    @Override
    public Long countCommodity(Long uid) {
       return shoppingCartDaoService.countByUid(uid);
    }

    private ShoppingCartDto buildShoppingCartDto(ShoppingCartDao shoppingCartDao) {
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(shoppingCartDao.getId());
        shoppingCartDto.setIds(shoppingCartDao.getIds());
        shoppingCartDto.setUserId(shoppingCartDao.getUserId());
        shoppingCartDto.setCommodityId(shoppingCartDao.getCommodityId());
        shoppingCartDto.setCommodityCount(shoppingCartDao.getCommodityCount());
        shoppingCartDto.setIsValidate(shoppingCartDao.getIsValidate());
        shoppingCartDto.setCreatedAt(shoppingCartDao.getCreatedAt());
        shoppingCartDto.setUpdatedAt(shoppingCartDao.getUpdatedAt());
        return shoppingCartDto;
    }

    private ShoppingCartDao buildShoppingCartDao(ShoppingCartDto dto) {
        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
        shoppingCartDao.setId(dto.getId());
        shoppingCartDao.setIds(dto.getIds());
        shoppingCartDao.setUserId(dto.getUserId());
        shoppingCartDao.setCommodityId(dto.getCommodityId());
        shoppingCartDao.setCommodityCount(dto.getCommodityCount());
        shoppingCartDao.setIsValidate(dto.getIsValidate());
        return shoppingCartDao;
    }
}
