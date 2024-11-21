package indi.nonoas.crm.service;

import indi.nonoas.crm.pojo.dto.FundsDto;
import indi.nonoas.crm.pojo.vo.FundsVO;

import java.util.List;

public interface FundsService {

    public List<FundsDto> listFundsByPage(FundsVO vo,int page, int size);

    public int selectFundsCount(FundsVO vo);

    public List<FundsDto> listAllFunds(FundsVO vo);

    public int deleteAllFunds();

    public int insertFunds(FundsDto dto);

    public int insertFundsBatch(List<FundsDto> dtos);


}
