package com.flower.xin.service.core.impl;

import com.flower.xin.common.dto.CommodityDto;
import com.flower.xin.common.enums.ValidateEnum;
import com.flower.xin.common.query.CommodityQuery;
import com.flower.xin.common.util.JsonUtil;
import com.flower.xin.common.util.MoneyUtil;
import com.flower.xin.orm.dao.CommodityDao;
import com.flower.xin.orm.dao.CommoditySubjectContentDao;
import com.flower.xin.orm.impl.CommodityDaoService;
import com.flower.xin.orm.impl.CommoditySubjectContentDaoService;
import com.flower.xin.service.core.CommodityCoreService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommodityCoreServiceImpl implements CommodityCoreService {
    @Autowired
    private CommodityDaoService commodityDaoService;

    @Autowired
    private CommoditySubjectContentDaoService commoditySubjectContentDaoService;

    @Override
    public PageInfo<CommodityDto> getCommodities(CommodityQuery query) {
        if (query.getSubjectId() != null) {
            CommoditySubjectContentDao dao = new CommoditySubjectContentDao();
            dao.setCommoditySubjectId(query.getSubjectId());
            List<Long> ids = commoditySubjectContentDaoService.getCommoditySubjectContentList(dao).stream().map(s -> s.getCommodityId()).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(ids)) {
                PageInfo pageInfo1 =  new PageInfo();
                pageInfo1.setList(new ArrayList());
                return pageInfo1;
            }
            query.setIds(ids);
        }
        PageInfo<CommodityDao> pageInfo = commodityDaoService.getCommodityListByPage(buildCommodityDao(query));
        if(CollectionUtils.isEmpty(pageInfo.getList())) {
            PageInfo pageInfo1 =  new PageInfo();
            pageInfo1.setList(new ArrayList());
        }
        return buildCommodityDtoPageInfo(pageInfo);
    }

    @Override
    public List<CommodityDto> getCommoditiesNotPage(CommodityQuery query) {

        List<CommodityDao> commodityList = commodityDaoService.getCommodityList(buildCommodityDao(query));
        if(CollectionUtils.isEmpty(commodityList)) {
            return Collections.EMPTY_LIST;
        }
        return commodityList.stream().map(this::buildCommodityDto).collect(Collectors.toList());
    }

    @Override
    public List<CommodityDto> getCommoditiesBySubject(CommodityQuery query) {
        List<CommoditySubjectContentDao> commoditySubjectContentDaoList = commoditySubjectContentDaoService.getCommoditySubjectContentList(buildCommoditySubjectContentDao(query));
        if(CollectionUtils.isEmpty(commoditySubjectContentDaoList)) {
            return Collections.EMPTY_LIST;
        }
        CommodityDao commodityDao = new CommodityDao();
        commodityDao.setIds(commoditySubjectContentDaoList.stream().map(CommoditySubjectContentDao::getCommodityId).collect(Collectors.toList()));
        commodityDao.setIsValidate(query.getIsValidate());
        List<CommodityDao> commodityDaoList = commodityDaoService.getCommodityList(commodityDao);
        if(CollectionUtils.isEmpty(commodityDaoList)) {
            return Collections.EMPTY_LIST;
        }
        return commodityDaoList.stream().map(this::buildCommodityDto).collect(Collectors.toList());
    }

    @Override
    public PageInfo<CommodityDto> searchCommodities(CommodityQuery query) {
        PageInfo<CommodityDao> pageInfo = commodityDaoService.getCommodityListByNameLike(buildCommodityDao(query));
        if(CollectionUtils.isEmpty(pageInfo.getList())) {
            return new PageInfo();
        }
        return buildCommodityDtoPageInfo(pageInfo);
    }

    @Override
    public void createCommodities(CommodityDto dto) {
        commodityDaoService.createCommodity(buildCommodityDao(dto));
    }

    @Override
    public void updateCommodity(CommodityDto dto) {
        commodityDaoService.updateCommodity(buildCommodityDao(dto));
    }

    private CommodityDao buildCommodityDao(CommodityDto dto) {
        CommodityDao commodityDao = new CommodityDao();
        commodityDao.setId(dto.getId());
        commodityDao.setIds(dto.getIds());
        commodityDao.setName(dto.getName());
        commodityDao.setCommodityDesc(dto.getCommodityDesc());
        commodityDao.setDetail(dto.getDetail());
        commodityDao.setPicList(dto.getPicList());
        commodityDao.setDetailsPicList(dto.getDetailsPicList());
        if(dto.getOriginalPrice() != null) {
            commodityDao.setOriginalPrice(MoneyUtil.yuanToFen(dto.getOriginalPrice()));
        }
        if(dto.getCurrentPrice() != null) {
            commodityDao.setCurrentPrice(MoneyUtil.yuanToFen(dto.getCurrentPrice()));
        }
        commodityDao.setClassifyId(dto.getClassifyId());
        commodityDao.setCommodityType(dto.getCommodityType());
        commodityDao.setBossId(dto.getBossId());
        commodityDao.setPayload(dto.getPayload());
        commodityDao.setIsValidate(dto.getIsValidate());
        return commodityDao;
    }

    private CommoditySubjectContentDao buildCommoditySubjectContentDao(CommodityQuery query) {
        CommoditySubjectContentDao commoditySubjectContentDao = new CommoditySubjectContentDao();
        commoditySubjectContentDao.setId(query.getSubjectId());
        return commoditySubjectContentDao;
    }

    private CommodityDto buildCommodityDto(CommodityDao commodityDao) {
        CommodityDto commodityDto = new CommodityDto();
        commodityDto.setId(commodityDao.getId());
        commodityDto.setName(commodityDao.getName());
        commodityDto.setCommodityDesc(commodityDao.getCommodityDesc());
        commodityDto.setDetail(commodityDao.getDetail());
        commodityDto.setPicList(commodityDao.getPicList());
        commodityDto.setDetailsPicList(commodityDao.getDetailsPicList());
        commodityDto.setOriginalPrice(MoneyUtil.fenToYuan(commodityDao.getOriginalPrice()));
        commodityDto.setCurrentPrice(MoneyUtil.fenToYuan(commodityDao.getCurrentPrice()));
        commodityDto.setClassifyId(commodityDao.getClassifyId());
        commodityDto.setCommodityType(commodityDao.getCommodityType());
        commodityDto.setBossId(commodityDao.getBossId());
        commodityDto.setPayload(commodityDao.getPayload());
        commodityDto.setIsValidate(commodityDao.getIsValidate());
        List<String> pics = JsonUtil.fromJson(commodityDao.getPicList(), List.class);
        commodityDto.setFirstPic(pics.size() == 0? "" : pics.get(0));
        return commodityDto;
    }

    private CommodityDao buildCommodityDao(CommodityQuery query) {
        CommodityDao commodityDao = new CommodityDao();
        commodityDao.setId(query.getId());
        commodityDao.setIds(query.getIds());
        commodityDao.setLimit(query.getLimit());
        commodityDao.setPage(query.getPage());
        commodityDao.setClassifyId(query.getClassifyId());
        commodityDao.setIsValidate(query.getIsValidate());
        commodityDao.setName(query.getNameLike());
        return commodityDao;
    }

    private PageInfo<CommodityDto> buildCommodityDtoPageInfo(PageInfo<CommodityDao> pageInfoDao) {
        PageInfo<CommodityDto> pageInfoDto = new PageInfo<CommodityDto>();
        pageInfoDto.setPageNum(pageInfoDao.getPageNum());
        pageInfoDto.setPageSize(pageInfoDao.getPageSize());
        pageInfoDto.setSize(pageInfoDao.getSize());
        pageInfoDto.setStartRow(pageInfoDao.getStartRow());
        pageInfoDto.setEndRow(pageInfoDao.getEndRow());
        pageInfoDto.setTotal(pageInfoDao.getTotal());
        pageInfoDto.setPages(pageInfoDao.getPages());
        pageInfoDto.setFirstPage(pageInfoDao.getFirstPage());
        pageInfoDto.setPrePage(pageInfoDao.getPrePage());
        pageInfoDto.setNextPage(pageInfoDao.getNextPage());
        pageInfoDto.setLastPage(pageInfoDao.getLastPage());
        pageInfoDto.setIsFirstPage(pageInfoDao.isIsFirstPage());
        pageInfoDto.setIsLastPage(pageInfoDao.isIsLastPage());
        pageInfoDto.setHasPreviousPage(pageInfoDao.isHasPreviousPage());
        pageInfoDto.setHasNextPage(pageInfoDao.isHasNextPage());
        pageInfoDto.setNavigatePages(pageInfoDao.getNavigatePages());
        pageInfoDto.setNavigatepageNums(pageInfoDao.getNavigatepageNums());
        pageInfoDto.setNavigateFirstPage(pageInfoDao.getNavigateFirstPage());
        pageInfoDto.setNavigateLastPage(pageInfoDao.getNavigateLastPage());
        pageInfoDto.setList(pageInfoDao.getList().stream()
                .map(this::buildCommodityDto)
                .collect(Collectors.toList()));
        return pageInfoDto;
    }
}
