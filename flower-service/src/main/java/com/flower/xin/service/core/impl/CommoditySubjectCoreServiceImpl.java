package com.flower.xin.service.core.impl;

import com.flower.xin.common.dto.CommoditySubjectDto;
import com.flower.xin.common.enums.CommodityTypeEnum;
import com.flower.xin.common.enums.SubjectTypeEnum;
import com.flower.xin.common.query.CommoditySubjectQuery;
import com.flower.xin.orm.dao.CommoditySubjectDao;
import com.flower.xin.orm.impl.CommoditySubjectDaoService;
import com.flower.xin.service.core.CommoditySubjectCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eiven
 */
@Service
public class CommoditySubjectCoreServiceImpl implements CommoditySubjectCoreService {
    @Autowired
    private CommoditySubjectDaoService commoditySubjectDaoService;


    @Override
    public List<CommoditySubjectDto> getCommoditySubject(CommoditySubjectQuery query) {
        return commoditySubjectDaoService.getCommoditySubjectList(buildCommoditySubjectDao(query))
                .stream()
                .map(this::buildCommoditySubjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createCommoditySubject(CommoditySubjectDto dto) {
        commoditySubjectDaoService.createCommoditySubject(buildCommoditySubjectDao(dto));
    }

    private CommoditySubjectDao buildCommoditySubjectDao(CommoditySubjectDto dto) {
        CommoditySubjectDao commoditySubjectDao = new CommoditySubjectDao();
        commoditySubjectDao.setId(dto.getId());
        commoditySubjectDao.setName(dto.getName());
        commoditySubjectDao.setType(dto.getType());
        commoditySubjectDao.setScore(dto.getScore());
        commoditySubjectDao.setPicList(dto.getPicList());
        commoditySubjectDao.setIsValidate(dto.getIsValidate());
        return commoditySubjectDao;
    }

    @Override
    public void updateCommoditySubject(CommoditySubjectDto dto) {
        commoditySubjectDaoService.updateCommoditySubject(buildCommoditySubjectDao(dto));
    }

    private CommoditySubjectDto buildCommoditySubjectDto(CommoditySubjectDao commoditySubjectDao) {
        CommoditySubjectDto commoditySubjectDto = new CommoditySubjectDto();
        commoditySubjectDto.setId(commoditySubjectDao.getId());
        commoditySubjectDto.setName(commoditySubjectDao.getName());
        commoditySubjectDto.setType(commoditySubjectDao.getType());
        commoditySubjectDto.setScore(commoditySubjectDao.getScore());
        commoditySubjectDto.setPicList(commoditySubjectDao.getPicList());
        commoditySubjectDto.setIsValidate(commoditySubjectDao.getIsValidate());
        commoditySubjectDto.setCreatedAt(commoditySubjectDao.getCreatedAt());
        commoditySubjectDto.setUpdatedAt(commoditySubjectDao.getUpdatedAt());
        return commoditySubjectDto;
    }

    private CommoditySubjectDao buildCommoditySubjectDao(CommoditySubjectQuery query) {
        CommoditySubjectDao commoditySubjectDao = new CommoditySubjectDao();
        commoditySubjectDao.setId(query.getId());
        commoditySubjectDao.setName(query.getName());
        if(query.getType() != null) {
            commoditySubjectDao.setType(SubjectTypeEnum.valueOf(query.getType()).getCode());
        }
        commoditySubjectDao.setScoreSortDesc(true);
        commoditySubjectDao.setIsValidate(query.getIsvalidate());
        return commoditySubjectDao;
    }

}
