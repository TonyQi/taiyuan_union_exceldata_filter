package indi.nonoas.crm.service.impl;

import indi.nonoas.crm.dao.ZhuxiaoqingzhangMapper;
import indi.nonoas.crm.pojo.dto.ZhuxiaoqingzhangDto;
import indi.nonoas.crm.pojo.vo.EnterpriseVO;
import indi.nonoas.crm.service.ZhuxiaoqingzhangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ZhuxiaoqingzhangServiceImpl implements ZhuxiaoqingzhangService {

    @Autowired
    ZhuxiaoqingzhangMapper zhuxiaoqingzhangMapper;

    @Override
    public List<ZhuxiaoqingzhangDto> getAllZhuxiaoqingzhangList() {
        List<ZhuxiaoqingzhangDto> list = zhuxiaoqingzhangMapper.selectZhuxiaoqingzhang(new EnterpriseVO());
        return list;
    }

    @Override
    public List<ZhuxiaoqingzhangDto> getZhuxiaoqingzhangList(EnterpriseVO vo) {
        return zhuxiaoqingzhangMapper.selectZhuxiaoqingzhang(vo);
    }


    @Override
    public List<ZhuxiaoqingzhangDto> getAllZhuxiaoqingzhangListByPage(EnterpriseVO vo,int pageNum, int pageSize) {
        vo.setOffset((pageNum-1)*pageSize);
        vo.setSize(pageSize);
        return zhuxiaoqingzhangMapper.selectZhuxiaoqingzhangByPage(vo);
    }

    @Override
    public int getZhuxiaoqingzhangCount(EnterpriseVO vo) {
        return zhuxiaoqingzhangMapper.selectZhuxiaoqingzhangCount(vo);
    }
}
