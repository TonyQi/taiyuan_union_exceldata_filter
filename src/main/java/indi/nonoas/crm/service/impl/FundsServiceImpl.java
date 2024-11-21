package indi.nonoas.crm.service.impl;

import indi.nonoas.crm.dao.FundsMapper;
import indi.nonoas.crm.pojo.dto.FundsDto;
import indi.nonoas.crm.pojo.vo.FundsVO;
import indi.nonoas.crm.service.FundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FundsServiceImpl implements FundsService {

    @Autowired
    FundsMapper fundsMapper;

    @Override
    public List<FundsDto> listFundsByPage(FundsVO vo, int page, int size) {
        vo.setOffset((page - 1) * size);
        vo.setSize(size);
        return fundsMapper.selectFundsByPage(vo);
    }

    @Override
    public int selectFundsCount(FundsVO vo) {
        return fundsMapper.selectFundsCount(vo);
    }

    @Override
    public List<FundsDto> listAllFunds(FundsVO vo) {
        return fundsMapper.selectFunds(vo);
    }

    @Override
    public int deleteAllFunds() {
        return fundsMapper.deleteAllFunds();
    }

    @Override
    public int insertFunds(FundsDto dto) {
        return fundsMapper.insertFunds(dto);
    }

    @Override
    public int insertFundsBatch(List<FundsDto> dtos) {
        for (int i = 0; i < dtos.size(); i=i+10) {
            if(i+10<=dtos.size()){
                fundsMapper.insertFundsBatch(dtos.subList(i,i+10));
            } else {
                fundsMapper.insertFundsBatch(dtos.subList(i,dtos.size()));
            }
        }
        return dtos.size();
    }
}
