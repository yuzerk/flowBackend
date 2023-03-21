package com.flower.xin.orm.impl;

import com.flower.xin.orm.dao.CommoditySubjectContentDao;
import com.flower.xin.orm.mapper.CommoditySubjectContentDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommoditySubjectContentDaoService {

    @Autowired
    private CommoditySubjectContentDaoMapper commoditySubjectContentDaoMapper;

    public List<CommoditySubjectContentDao> getCommoditySubjectContentList(CommoditySubjectContentDao dao) {
        return commoditySubjectContentDaoMapper.selectBySelective(dao);
    }

    public CommoditySubjectContentDao getCommoditySubjectContent(Long id) {
        return commoditySubjectContentDaoMapper.selectByPrimaryKey(id);
    }

    public Integer createCommoditySubjectContent(CommoditySubjectContentDao dao) {
        return commoditySubjectContentDaoMapper.insertSelective(dao);
    }

    public Integer updateCommoditySubjectContent(CommoditySubjectContentDao dao) {
        return commoditySubjectContentDaoMapper.updateByPrimaryKeySelective(dao);
    }

    public Integer deleteCommoditySubjectContent(Long id) {
        return commoditySubjectContentDaoMapper.deleteByPrimaryKey(id);
    }
}
