package com.flower.xin.service.core.impl;

import com.flower.xin.common.dto.CommodityClassifyDto;
import com.flower.xin.common.query.CommodityClassifyQuery;
import com.flower.xin.orm.dao.CommodityClassifyDao;
import com.flower.xin.orm.impl.CommodityClassifyDaoService;
import com.flower.xin.service.core.CommodityClassifyCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eiven
 */
@Service
public class CommodityClassifyCoreServiceImpl implements CommodityClassifyCoreService {

    @Autowired
    private CommodityClassifyDaoService commodityClassifyDaoService;

    @Override
    public List<CommodityClassifyDto> getCommodityClassify(CommodityClassifyQuery query) {
        return commodityClassifyDaoService.getCommodityClassifyList(buildCommodityClassifyDao(query))
                .stream()
                .map(this::buildCommodityClassifyDto)
                .collect(Collectors.toList());    }

    @Override
    public void createCommodityClassify(CommodityClassifyDto dto) {
        commodityClassifyDaoService.createCommodityClassify(buildCommodityClassifyDao(dto));
    }

    @Override
    public void updateCommodityClassify(CommodityClassifyDto dto) {
        commodityClassifyDaoService.updateCommodityClassify(buildCommodityClassifyDao(dto));
    }

    private CommodityClassifyDao buildCommodityClassifyDao(CommodityClassifyDto dto) {
        CommodityClassifyDao commodityClassifyDao = new CommodityClassifyDao();
        commodityClassifyDao.setId(dto.getId());
        commodityClassifyDao.setName(dto.getName());
        commodityClassifyDao.setScore(dto.getScore());
        return commodityClassifyDao;
    }

    private CommodityClassifyDto buildCommodityClassifyDto(CommodityClassifyDao commodityClassifyDao) {
        CommodityClassifyDto commodityClassifyDto = new CommodityClassifyDto();
        commodityClassifyDto.setId(commodityClassifyDao.getId());
        commodityClassifyDto.setName(commodityClassifyDao.getName());
        commodityClassifyDto.setScore(commodityClassifyDao.getScore());
        return commodityClassifyDto;
    }

    private CommodityClassifyDao buildCommodityClassifyDao(CommodityClassifyQuery query) {
        CommodityClassifyDao commodityClassifyDao = new CommodityClassifyDao();
        commodityClassifyDao.setId(query.getId());
        commodityClassifyDao.setName(query.getName());
        commodityClassifyDao.setScore(query.getScore());
        commodityClassifyDao.setScoreSortDesc(true);
        return commodityClassifyDao;
    }
}
