package indi.nonoas.crm.dao;

import indi.nonoas.crm.pojo.dto.FundsDto;
import indi.nonoas.crm.pojo.vo.FundsVO;

import java.util.List;

public interface FundsMapper {
    public List<FundsDto> selectFunds(FundsVO vo);

    public List<FundsDto> selectFundsByPage(FundsVO vo);

    public int selectFundsCount(FundsVO vo);

    public int insertFunds(FundsDto dto);

    public int insertFundsBatch(List<FundsDto> dtos);


    int deleteAllFunds();
}
