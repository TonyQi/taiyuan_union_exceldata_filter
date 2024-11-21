package indi.nonoas.crm.service.impl;

import indi.nonoas.crm.dao.EnterpriseMapper;
import indi.nonoas.crm.pojo.dto.EnterpriseDto;
import indi.nonoas.crm.pojo.vo.EnterpriseVO;
import indi.nonoas.crm.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    EnterpriseMapper enterpriseMapper;

    @Override
    public List<EnterpriseDto> listEnterprises(EnterpriseVO vo) {
        return enterpriseMapper.selectEnterprise(vo);
    }



    @Override
    public int deleteEnterprise(int id) {
        return enterpriseMapper.deleteEnterprise(id);
    }

    @Override
    public int deleteAllEnterprises() {
        return enterpriseMapper.deleteAllEnterprises();
    }

    @Override
    public int insertEnterprise(EnterpriseDto e) {
        return enterpriseMapper.insertEnterprise(e);
    }

    @Override
    public int insertEnterpriseBatch(List<EnterpriseDto> dtos) {
        for (int i = 0; i < dtos.size(); i=i+10) {
            if(i+10<=dtos.size()){
                enterpriseMapper.insertEnterpriseBatch(dtos.subList(i,i+10));
            } else {
                enterpriseMapper.insertEnterpriseBatch(dtos.subList(i,dtos.size()));
            }
        }
        return dtos.size();
    }
    @Override
    public int selectEnterpriseCount(EnterpriseVO vo){
        return enterpriseMapper.selectEnterpriseCount(vo);
    }

    @Override
    public List<EnterpriseDto> listEnterprisesByPage(EnterpriseVO vo) {
        return enterpriseMapper.selectEnterpriseByPage(vo);
    }
}
