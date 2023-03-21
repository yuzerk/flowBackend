package com.flower.xin.orm.impl;

import com.flower.xin.orm.dao.CommoditySubjectDao;
import com.flower.xin.orm.mapper.CommoditySubjectDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommoditySubjectDaoService {

    @Autowired
    private CommoditySubjectDaoMapper commoditySubjectDaoMapper;

    public List<CommoditySubjectDao> getCommoditySubjectList(CommoditySubjectDao dao) {
        return commoditySubjectDaoMapper.selectBySelective(dao);
    }

    public CommoditySubjectDao getCommoditySubject(Long id) {
        return commoditySubjectDaoMapper.selectByPrimaryKey(id);
    }

    public Integer createCommoditySubject(CommoditySubjectDao dao) {
        return commoditySubjectDaoMapper.insertSelective(dao);
    }

    public Integer updateCommoditySubject(CommoditySubjectDao dao) {
        return commoditySubjectDaoMapper.updateByPrimaryKeySelective(dao);
    }
}
