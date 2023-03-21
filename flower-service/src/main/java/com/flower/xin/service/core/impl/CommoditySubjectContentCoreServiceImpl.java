package com.flower.xin.service.core.impl;

import com.flower.xin.common.dto.CommoditySubjectContentDto;
import com.flower.xin.common.query.CommoditySubjectContentQuery;
import com.flower.xin.orm.dao.CommoditySubjectContentDao;
import com.flower.xin.orm.impl.CommoditySubjectContentDaoService;
import com.flower.xin.service.core.CommoditySubjectContentCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommoditySubjectContentCoreServiceImpl implements CommoditySubjectContentCoreService {

    @Autowired
    private CommoditySubjectContentDaoService commoditySubjectContentDaoService;

    @Override
    public List<CommoditySubjectContentDto> getCommoditySubject(CommoditySubjectContentQuery query) {

        return commoditySubjectContentDaoService.getCommoditySubjectContentList(buildCommoditySubjectContentDao(query))
                .stream()
                .map(this::buildCommoditySubjectContentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createCommoditySubject(CommoditySubjectContentDto dto) {
        commoditySubjectContentDaoService.createCommoditySubjectContent(buildCommoditySubjectContentDao(dto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCommoditySubject(CommoditySubjectContentDto dto) {
        CommoditySubjectContentDao queryDao = new CommoditySubjectContentDao();
        queryDao.setCommodityId(dto.getCommodityId());
        List<Long> commoditySubjectIds = dto.getCommoditySubjectIds();
        if(commoditySubjectIds == null) {
            commoditySubjectIds = new ArrayList<>();
        }
        List<CommoditySubjectContentDao> commoditySubjectContentDaoList = commoditySubjectContentDaoService.getCommoditySubjectContentList(queryDao);

        // TODO: 2020/4/11 以后改成update模式，不删数据
        for (CommoditySubjectContentDao dao : commoditySubjectContentDaoList) {
                commoditySubjectContentDaoService.deleteCommoditySubjectContent(dao.getId());
        }

        for (Long id : commoditySubjectIds) {
            CommoditySubjectContentDao dao = new CommoditySubjectContentDao();
            dao.setCommodityId(dto.getCommodityId());
            dao.setCommoditySubjectId(id);
            commoditySubjectContentDaoService.createCommoditySubjectContent(dao);
        }
    }

    private CommoditySubjectContentDao buildCommoditySubjectContentDao(CommoditySubjectContentDto dto) {
        CommoditySubjectContentDao commoditySubjectContentDao = new CommoditySubjectContentDao();
        commoditySubjectContentDao.setId(dto.getId());
        commoditySubjectContentDao.setCommoditySubjectIds(dto.getCommoditySubjectIds());
        commoditySubjectContentDao.setCommoditySubjectId(dto.getCommoditySubjectId());
        commoditySubjectContentDao.setCommodityId(dto.getCommodityId());
        commoditySubjectContentDao.setCreatedAt(dto.getCreatedAt());
        commoditySubjectContentDao.setUpdatedAt(dto.getUpdatedAt());
        return commoditySubjectContentDao;
    }

    private CommoditySubjectContentDto buildCommoditySubjectContentDto(CommoditySubjectContentDao commoditySubjectContentDao) {
        CommoditySubjectContentDto commoditySubjectContentDto = new CommoditySubjectContentDto();
        commoditySubjectContentDto.setId(commoditySubjectContentDao.getId());
        commoditySubjectContentDto.setCommoditySubjectId(commoditySubjectContentDao.getCommoditySubjectId());
        commoditySubjectContentDto.setCommodityId(commoditySubjectContentDao.getCommodityId());
        commoditySubjectContentDto.setCreatedAt(commoditySubjectContentDao.getCreatedAt());
        commoditySubjectContentDto.setUpdatedAt(commoditySubjectContentDao.getUpdatedAt());
        return commoditySubjectContentDto;
    }

    private CommoditySubjectContentDao buildCommoditySubjectContentDao(CommoditySubjectContentQuery query) {
        CommoditySubjectContentDao commoditySubjectContentDao = new CommoditySubjectContentDao();
        commoditySubjectContentDao.setId(query.getId());
        commoditySubjectContentDao.setCommoditySubjectIds(query.getCommoditySubjectIds());
        commoditySubjectContentDao.setCommoditySubjectId(query.getCommoditySubjectId());
        commoditySubjectContentDao.setCommodityId(query.getCommodityId());
        return commoditySubjectContentDao;
    }
}
